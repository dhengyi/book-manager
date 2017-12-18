//package bookmanager.utilclass;
//
//import com.qcloud.cos.COSClient;
//import com.qcloud.cos.model.ObjectMetadata;
//import com.qcloud.cos.model.PutObjectResult;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.io.InputStream;
//
///**
// * @Author: spider_hgyi
// * @Date: Created in 下午5:19 17-12-6.
// * @Modified By:
// * @Description: 将图片保存在对象存储上
// */
//@Component
//public class COSStorage {
//    private COSClient cosClient;
//
//    @Autowired
//    public COSStorage(COSClient cosClient) {
//        this.cosClient = cosClient;
//    }
//
//    public void uploadPicture(String bookPictureName, InputStream inputStream) {
//        ObjectMetadata objectMetadata = new ObjectMetadata();
//        // 设置输入流长度为 500
//        objectMetadata.setContentLength(500);
//        // 设置 Content type, 默认是 application/octet-stream
//        objectMetadata.setContentType("application/pdf, image/jpeg, image/png, image/gif");
//        PutObjectResult putObjectResult = cosClient.putObject("bookmanager", bookPictureName,
//                inputStream, objectMetadata);
//    }
//}
