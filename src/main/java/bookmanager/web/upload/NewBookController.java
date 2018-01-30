package bookmanager.web.upload;

import bookmanager.dao.dbservice.BookInfoService;
import bookmanager.dao.dbservice.BookLabelService;
import bookmanager.dao.dbservice.BookRelationLabelService;
import bookmanager.model.po.BookInfoPO;
import bookmanager.model.po.BookRelationLabelPO;
import bookmanager.utilclass.COSStorage;
import bookmanager.utilclass.DateToString;
import bookmanager.utilclass.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * @Author: spider_hgyi
 * @Date: Created in 下午8:14 17-12-3.
 * @Modified By:
 * @Description:
 */
@WebServlet(urlPatterns = "/auth/upload.do")
@MultipartConfig
public class NewBookController extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(NewBookController.class);

    private BookInfoService bookInfoService;
    private BookLabelService bookLabelService;
    private BookRelationLabelService bookRelationLabelService;
    private COSStorage cosStorage;

    // 手动获取bean
    public void init() throws ServletException {
        ServletContext servletContext = this.getServletContext();
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        bookInfoService = (BookInfoService) ctx.getBean("bookInfoServiceImpl");
        bookLabelService = (BookLabelService) ctx.getBean("bookLabelServiceImpl");
        bookRelationLabelService = (BookRelationLabelService) ctx.getBean("bookRelationLabelServiceImpl");
        cosStorage = (COSStorage) ctx.getBean("cosStorage");
    }

    /**
     * @param:
     * @return:
     * @description: 上传成功跳转至我的书籍页面，上传失败回填表单
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
        response.setCharacterEncoding("utf8");

        String bookName = request.getParameter("bookName");
        String author = request.getParameter("author");
        int uid = (Integer) request.getSession(false).getAttribute("uid");
        int amount = Integer.parseInt(request.getParameter("amount"));
        String string = request.getParameter("types");
        String date = DateToString.getStringDate();
        String describ = request.getParameter("describ");

        BookInfoPO bookInfoPO = new BookInfoPO();
        Part bookPicture;
        String bookPictureName = "";

        // 处理图片
        if ((bookPicture = request.getPart("bookPicture")).getSize() != 0) {
            String uuid = UUID.randomUUID().toString();
            bookPictureName = "http://bookmanager-1253675585.coscd.myqcloud.com/" + uuid;

            try {
                cosStorage.uploadPicture(uuid, bookPicture.getInputStream(), bookPicture.getSize());
            } catch (IOException e) {
                System.out.println("cos上传图片出错");
                e.printStackTrace();
            }

            cosStorage.shutdownClient();
        }

        // 保存上传图片信息
        bookInfoPO.setUgkName(bookName);
        bookInfoPO.setAuthor(author);
        bookInfoPO.setUgkUid(uid);
        bookInfoPO.setAmount(amount);
        bookInfoPO.setUploadDate(date);
        bookInfoPO.setBookPicture(bookPictureName);
        bookInfoPO.setDescrib(describ);

        boolean isUpload = bookInfoService.save(bookInfoPO);
        if (isUpload) {
            // 处理书籍分类
            Tools tools = new Tools();
            Set<String> types = tools.getTypes(string);

            // 得到新增书籍的ID
            int bookId = bookInfoService.getBookIDByBookNameAndUID(bookName, uid);
            // 查询所属分类的id并save至book_relation_label表
            for (String type : types) {
                int labelId = bookLabelService.getPkIdByName(type);
                BookRelationLabelPO bookRelationLabelPO = new BookRelationLabelPO(bookId, labelId);
                bookRelationLabelService.save(bookRelationLabelPO);
            }

            response.sendRedirect("/auth/mybook");
        } else {
            printInfo(response);
        }
    }

    private void printInfo(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("GBK");

        final String MYBOOK_PAGE = "/auth/mybook";

        PrintWriter out = response.getWriter();
        String builder = "<script language=\"javascript\">" +
                "alert(\"您已经上传过此书，请前往我的书籍页面进行信息的修改即可！\");" +
                "top.location='" +
                MYBOOK_PAGE +
                "';" +
                "</script>";

        out.print(builder);
    }
}