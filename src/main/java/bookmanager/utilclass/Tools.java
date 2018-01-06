package bookmanager.utilclass;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: spider_hgyi
 * @Date: Created in 下午3:58 18-1-1.
 * @Modified By:
 * @Description: 工具类
 */
@Component
public class Tools {
    // 处理上传书籍所选分类
    public Set<String> getTypes(String string) {
        string = string.trim();
        String types[] = string.split(" ");
        Set<String> set = new HashSet<String>();

        set.addAll(Arrays.asList(types));

        return set;
    }
}
