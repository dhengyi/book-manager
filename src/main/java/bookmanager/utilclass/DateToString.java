package bookmanager.utilclass;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: spider_hgyi
 * @Date: Created in 下午10:59 17-11-23.
 * @Modified By:
 * @Description: 日期转换类
 * 数据库里面的datetime类型实质上是String类型, 所以我们需要在后台将所有的Date类型的时间按照
 * datetime的格式转换成字符串这个类就是将时间戳转换成datetime类型的字符串
 */

public class DateToString {
    public static String getStringDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(new Date());
    }
}