package bookmanager.web.mybook;

import bookmanager.dao.dbservice.BookInfoService;
import bookmanager.dao.dbservice.BookLabelService;
import bookmanager.dao.dbservice.BookRelationLabelService;
import bookmanager.model.po.BookInfoPO;
import bookmanager.model.po.BookRelationLabelPO;
import bookmanager.utilclass.DateToString;
import bookmanager.utilclass.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Set;
import java.util.UUID;

/**
 * @Author: spider_hgyi
 * @Date: Created in 下午9:16 18-1-28.
 * @Modified By:
 * @Description:
 */
@Controller
@RequestMapping("/auth")
public class EditBookController {
    private BookInfoService bookInfoService;
    private BookLabelService bookLabelService;
    private BookRelationLabelService bookRelationLabelService;

    @Autowired
    public EditBookController(BookInfoService bookInfoService, BookLabelService bookLabelService,
                              BookRelationLabelService bookRelationLabelService) {
        this.bookInfoService = bookInfoService;
        this.bookLabelService = bookLabelService;
        this.bookRelationLabelService = bookRelationLabelService;
    }

    // 编辑页面的展现
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String showEditPage(@RequestParam("id") int bookId, Model model) {
        BookInfoPO bookInfoPO = bookInfoService.getBookInfoById(bookId);

        model.addAttribute("bookInfo", bookInfoPO);

        return "editbook";
    }

    // 重新编辑书籍后进行的后台处理
    @RequestMapping(value = "/edit.do", method = RequestMethod.POST)
    public void editBookInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf8");
        response.setCharacterEncoding("utf8");

        int bookId = Integer.parseInt(request.getParameter("id"));
        String bookName = request.getParameter("bookName");
        String author = request.getParameter("author");
        int amount = Integer.parseInt(request.getParameter("amount"));


        BookInfoPO bookInfoPO = new BookInfoPO();

        // 保存修改书籍的信息
        bookInfoPO.setPkId(bookId);
        bookInfoPO.setUgkName(bookName);
        bookInfoPO.setAuthor(author);
        bookInfoPO.setAmount(amount);

        bookInfoService.updateBook(bookInfoPO);

        response.sendRedirect("/auth/mybook");
    }
}
