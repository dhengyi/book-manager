package bookmanager.dao.rowmapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.*;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by dela on 12/5/17.
 */

// 在这里定义一个能在JdbcTemplate中通用的泛型JdbcRowMapper类
public class JdbcRowMapper<T> implements RowMapper {

    // 记录日志
    protected final Log log = LogFactory.getLog(getClass());

    // 需要映射到的具体类
    private Class<T> mappedClass;

    // 检查Filed是否完全填充
    private boolean checkFullPopulated = false;

    // 原始值默认为null
    private boolean primitivesDefaultedForNullValue = false;

    // 映射到的具体类的Filed
    // PropertyDescriptor类表示JavaBean类通过存储器导出一个属性
    private Map<String, PropertyDescriptor> mappedFileds;

    // JavaBean的一组属性
    private Set<String> mappedProperties;


    public JdbcRowMapper() { }

    public JdbcRowMapper(Class<T> mappedClass) {
        initialize(mappedClass);
    }

    public JdbcRowMapper(Class<T> mappedClass, boolean checkFullPopulated) {
        initialize(mappedClass);
        this.checkFullPopulated = checkFullPopulated;
    }

    // 为JdbcRowMapper注入mappedClass成员, 以便确定泛型的类型
    public void setMappedClass(Class<T> mappedClass) {
        // 如果mappedClass为空, 那就执行初始化操作
        if (null == this.mappedClass) {
            initialize(mappedClass);
        } else {
            // 如果mappedClass不为空, 则抛出异常:不能给已经映射的mappedClass重新分配mappedClass
            // 因为泛型的类型一旦确定, 就不能再发生改变.
            if (this.mappedClass != null) {
                throw new InvalidDataAccessApiUsageException("The mapped class can not be reassigned to map to " +
                        mappedClass + " since it is already providing mapping for " + this.mappedClass);
            }
        }
    }

    // 得到映射到泛型的类类型
    public final Class<T> getMappedClass() {
        return this.mappedClass;
    }

    public void setCheckFullPopulated(boolean checkFullPopulated) {
        this.checkFullPopulated = checkFullPopulated;
    }

    public boolean isCheckFullPopulated() {
        return this.checkFullPopulated;
    }

    public void setPrimitivesDefaultedForNullValue(boolean primitivesDefaultedForNullValue) {
        this.primitivesDefaultedForNullValue = primitivesDefaultedForNullValue;
    }

    public boolean isPrimitivesDefaultedForNullValue() {
        return this.primitivesDefaultedForNullValue;
    }

    // 初始化给定类的映射
    protected void initialize(Class<T> mappedClass) {
        this.mappedClass = mappedClass;
        this.mappedFileds = new HashMap<String, PropertyDescriptor>();
        this.mappedProperties = new HashSet<String>();

        // 通过BeanUtils.getPropertyDescriptor()得到当前JavaBean(mappedClass对应的POJO)的PropertyDescriptor数组
        // PropertyDescriptors类是Java内省类库的一个类.
        // Java内省: Java JDK中提供了一套API用来访问JavaBean的某个属性的getter/setter方法, 这就是内省.
        PropertyDescriptor[] propertyDescriptors
                = BeanUtils.getPropertyDescriptors(mappedClass);

        // propertyDescriptor.getWriteMethod(): 获得用于写入属性值的方法.
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            if (propertyDescriptor.getWriteMethod() != null) {
                String name = propertyDescriptor.getName();

                // annotation: 数据库字段与类属性对应关系
                try {
                    // 通过通过反射取得class里名为name的属性
                    Field field = mappedClass.getDeclaredField(name);
                    if (field != null) {
                        // filed.getAnnotation(): 得到该属性(field)上存在的所有注解值
                        // 有些field是通过Column来设置值的
                        Column column = field.getAnnotation(Column.class);

                        // 如果取得的column值不为null, 那就给name赋值column.name
                        if (column != null) {
                            name = column.name();
                        }
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }

                // TODO 这里的代码的顺序是否正确有待测试
                // 将<属性名字, 属性>对加入map中
                this.mappedFileds.put(lowerCaseName(name), propertyDescriptor);

                String underscoredName = underscoreName(name);
                if (!lowerCaseName(name).equals(underscoredName)) {
                    this.mappedFileds.put(underscoredName, propertyDescriptor);
                }
                this.mappedProperties.add(name);
            }
        }
    }

    // lowerCaseName和underscoreName这两个方法的作用是将Java POJO中的属性名转换为对应的数据库中表的字段的名字
    // 比如将userName转换为user_name
    protected String lowerCaseName(String name) {
        return name.toLowerCase(Locale.US);
    }

    protected String underscoreName(String name) {
        if (!StringUtils.hasLength(name)) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        result.append(lowerCaseName(name.substring(0, 1)));
        for (int i = 1; i < name.length(); i++) {
            String s = name.substring(i, i + 1);
            String slc = lowerCaseName(s);
            if (!s.equals(slc)) {
                result.append("_").append(slc);
            }
            else {
                result.append(s);
            }
        }
        return result.toString();
    }

    public T mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        // Spring的断言表达式, 如果前面的布尔表达式为false, 那么后面的message将会以异常消息的形式抛出.
        Assert.state(this.mappedClass != null, "Mapped class was not specified");
        // 将mappedClass实例化为一个object
        T mappedObject = BeanUtils.instantiate(this.mappedClass);

//        System.out.println(mappedObject);

        // 使用BeanWrapper将obkect包装成Bean
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(mappedObject);
        initBeanWrapper(beanWrapper);

        // 从resultSet中拿到数据库执行之后的数据进行数据填充
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        Set<String> populatedProperties = (isCheckFullPopulated() ? new HashSet<String>() : null);

//        System.out.println(columnCount);

        for (int index = 1; index <= columnCount; index++) {
            String column = JdbcUtils.lookupColumnName(resultSetMetaData, index);
            // 数据库的字段名
            String field = lowerCaseName(column.replaceAll(" ", ""));
            // 根据数据库字段名拿到属性方法的描述
            PropertyDescriptor propertyDescriptor = this.mappedFileds.get(field);
            if (propertyDescriptor != null) {
                try {
                    // 得到该field所对应的数据库表中字段(column)所对应的值(value)
                    Object value = getColumnValue(resultSet, index, propertyDescriptor);
                    //
                    if (rowNumber == 0 && log.isDebugEnabled()) {
                        log.debug("Mapping column '" + column + "' to property '" + propertyDescriptor.getName()
                                + "' of type [" + ClassUtils.getQualifiedName(propertyDescriptor.getPropertyType()) + "]");
                    }

                    try {
                        beanWrapper.setPropertyValue(propertyDescriptor.getName(), value);
                    } catch (TypeMismatchException ex) {
                        if (value == null && this.primitivesDefaultedForNullValue) {
                            if (log.isDebugEnabled()) {
                                log.debug("Intercepted TypeMismatchException for row " + rowNumber +
                                        " and column '" + column + "' with null value when setting property '" +
                                        propertyDescriptor.getName() + "' of type [" +
                                        ClassUtils.getQualifiedName(propertyDescriptor.getPropertyType()) +
                                        "] on object: " + mappedObject, ex);
                            }
                        } else {
                            throw ex;
                        }
                    }
                    if (populatedProperties != null) {
                        populatedProperties.add(propertyDescriptor.getName());
                    }
                } catch (NotWritablePropertyException ex) {
                    throw new DataRetrievalFailureException(
                            "Unable to map column '" + column + "' to property '" + propertyDescriptor.getName() + "'", ex);
                }
            } else {
                // 没有发现PropertyDescriptor
                if (rowNumber == 0 && log.isDebugEnabled()) {
                    log.debug("No property found for column '" + column + "' mapped to field '" + field + "'");
                }
            }
        }

        if (populatedProperties != null && !populatedProperties.equals(this.mappedProperties)) {
            throw new InvalidDataAccessApiUsageException("Given ResultSet does not contain all fields " +
                    "necessary to populate object of class [" + this.mappedClass.getName() + "]: " +
                    this.mappedProperties);
        }

        return mappedObject;
    }

    protected void initBeanWrapper(BeanWrapper beanWrapper) {
    }

    // 得到数据库表中字段(column)对应的值(value)
    protected Object getColumnValue(ResultSet resultSet, int index,
                                    PropertyDescriptor propertyDescriptor) throws SQLException {
        return JdbcUtils.getResultSetValue(resultSet, index, propertyDescriptor.getPropertyType());
    }

    // 工厂模式中, 经常使用newInstance()来创建一个对象
    public static <T> JdbcRowMapper<T> newInstance (Class<T> mappedClass) {
        return new JdbcRowMapper<T>(mappedClass);
    }
}