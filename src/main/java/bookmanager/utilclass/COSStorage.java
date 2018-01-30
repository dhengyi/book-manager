package bookmanager.utilclass;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: spider_hgyi
 * @Date: Created in 下午5:19 17-12-6.
 * @Modified By:
 * @Description: 将图片保存在对象存储上
 */
@Component("cosStorage")
public class COSStorage {
    private COSClient cosClient;

    @Autowired
    public COSStorage(COSClient cosClient) {
        this.cosClient = cosClient;
    }

    public void uploadPicture(String bookPictureName, InputStream inputStream, long length) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        // 设置输入流长度为 10M
        objectMetadata.setContentLength(length);
        // 设置 Content type, 默认是 application/octet-stream
        objectMetadata.setContentType("image/jpeg, image/png, image/gif, image/jpg");
        String bucketName = "bookmanager-1253675585";
        cosClient.putObject(bucketName, bookPictureName, inputStream, objectMetadata);

        // 关闭流
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 关闭客户端
    public void shutdownClient() {
        cosClient.shutdown();
    }
}