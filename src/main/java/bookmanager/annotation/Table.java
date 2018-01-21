package bookmanager.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by dela on 12/20/17.
 */

// @Retention是JDK的元注解, 当RetentionPolicy取值为RUNTIME的时候,
// 意味着编译器将Annotation记录在class文件中, 当Java文件运行的时候,
// JVM也可以获取Annotation的信息, 程序可以通过反射获取该Annotation的信息.
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
// @Target也是JDK的一个元注解, 当ElementType取不同值的时候, 意味着这个注解的作用域也不同,
// 比如, 当ElementType取TYPE的时候, 说明这个注解用于类/接口/枚举定义

public @interface Table {
    // 数据库中表的名字
    String name();
}