package bookmanager.config.cos;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ResourceBundle;

/**
 * @Author: spider_hgyi
 * @Date: Created in 下午6:48 17-12-6.
 * @Modified By:
 * @Description: 生成腾讯云对象存储客户端
 */
@Configuration
public class CosConfig {
    private static String secretId;
    private static String secretKey;

    // 加载配置文件
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("cos-config");

    //静态代码块在加载类时只执行一次
    static {
        secretId = resourceBundle.getString("secretId");
        secretKey = resourceBundle.getString("secretKey");
    }

    @Bean
    public COSClient createCosClient() {
        // 初始化身份信息(appid, secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 设置 bucket 的区域
        ClientConfig clientConfig = new ClientConfig(new Region("ap-chengdu"));
        // 生成 cos 客户端
        COSClient cosClient = new COSClient(cred, clientConfig);

        return cosClient;
    }
}
