package bookmanager.web.upload;

import bookmanager.dao.dbservice.BookInfoService;
import bookmanager.model.po.BookInfoPO;
import bookmanager.model.vo.bookinfo.UploadBook;
//import bookmanager.utilclass.COSStorage;
import bookmanager.utilclass.DateToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author: spider_hgyi
 * @Date: Created in 下午8:14 17-12-3.
 * @Modified By:
 * @Description:
 */
@Controller
@RequestMapping("/bookmanager")
public class NewBookController {
    private BookInfoService bookInfoService;
//    private COSStorage cosStorage;

//    @Autowired
//    public NewBookController(BookInfoService bookInfoService, COSStorage cosStorage) {
//        this.bookInfoService = bookInfoService;
//        this.cosStorage = cosStorage;
//    }

    /**
     * @param:
     * @return:
     * @description: 上传成功跳转至我的书籍页面，上传失败回填表单
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadBook(@RequestParam("bookPicture") MultipartFile bookPicture,
                             @Valid UploadBook uploadBook, Errors errors, HttpServletRequest request) {
        if (errors.hasErrors()) {
            return "uploadForm";
        }

        BookInfoPO bookInfoPO = new BookInfoPO();
        bookInfoPO.setUgkName(uploadBook.getName());
        bookInfoPO.setAuthor(uploadBook.getAuthor());
        bookInfoPO.setUgkUid((Integer) request.getSession(false).getAttribute("uid"));
        bookInfoPO.setUploadDate(DateToString.getStringDate());
        bookInfoPO.setDescrib(uploadBook.getDescrib());
        String bookPictureName = UUID.randomUUID().toString();
        bookInfoPO.setBookPicture(bookPictureName);
//            cosStorage.uploadPicture(bookPictureName, bookPicture.getInputStream());
        bookInfoService.save(bookInfoPO);

        return "mybook";
    }
}
