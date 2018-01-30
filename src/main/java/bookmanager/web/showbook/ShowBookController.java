package bookmanager.web.showbook;

import bookmanager.dao.dbservice.BookCommentService;
import bookmanager.dao.dbservice.BookInfoService;
import bookmanager.dao.dbservice.BorrowInfoService;
import bookmanager.dao.dbservice.UserService;
import bookmanager.model.po.BookCommentPO;
import bookmanager.model.po.BookInfoPO;
import bookmanager.model.po.BorrowInfoPO;
import bookmanager.model.po.PagePO;
import bookmanager.utilclass.BookUserMapUtil;
import bookmanager.utilclass.DateToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * Created by dongmengyuan on 18-1-3.
 */

@Controller
@RequestMapping(value = "/auth/showbook")
public class ShowBookController {
    private static final Logger logger = LoggerFactory.getLogger(ShowBookController.class);

    private UserService userService;
    private BookInfoService bookInfoService;
    private BorrowInfoService borrowInfoService;
    private BookCommentService bookCommentService;

    @Autowired
    public ShowBookController(UserService userService, BookInfoService bookInfoService, BorrowInfoService borrowInfoService,
                    BookCommentService bookCommentService) {
        this.userService = userService;
        this.bookInfoService = bookInfoService;
        this.borrowInfoService = borrowInfoService;
        this.bookCommentService = bookCommentService;
    }

    //显示图书详情
    @RequestMapping(value = "/{bookInfoPkId}", method = RequestMethod.GET)
    public String showBook(@PathVariable(value = "bookInfoPkId") Integer bookInfoPkId, Model model,
                           @RequestParam(value = "page", required = false) String page) {
        int currentPage = 1;
        if (page != null) {
            currentPage = Integer.parseInt(page);
        }
        PagePO pagePO = new PagePO(currentPage);

        BookInfoPO bookInfoPO = bookInfoService.getBookInfoByBookId(bookInfoPkId);
        // 获取此图书的借阅总数
        int borrowCount = borrowInfoService.getBorrowCountByBookId(bookInfoPkId);
        // 获取此图书下的评论的总数
        int bookCommentCount = bookCommentService.getBookCommentCountByBookId(bookInfoPkId);
        // 获取此书下当前页的评论
        List<BookCommentPO> bookCommentPOS = bookCommentService.getContent(bookInfoPkId, pagePO);
        // 获取评论者的名字（id-->name)
        Map<BookCommentPO, String> bookCommentsMap = BookUserMapUtil.getUsername(bookCommentPOS, userService);
        // 获取书籍上传者的名字（id-->name)
        Map<BookInfoPO, String> bookInfoMap = BookUserMapUtil.getOneBookInfo(bookInfoPO, userService);

        pagePO.setTotalCount(bookCommentCount);
        pagePO.setTotalPage((bookCommentCount % 5 == 0) ? bookCommentCount / 5 : bookCommentCount / 5 + 1);

        model.addAttribute("borrowCount", borrowCount);
        model.addAttribute("bookInfo", bookInfoPO);
        model.addAttribute("bookInfoMap", bookInfoMap);
        model.addAttribute("bookCommentsMap", bookCommentsMap);
        model.addAttribute("commentCount", bookCommentCount);
        model.addAttribute("pageInfo", pagePO);

        // 在这里添加分页的逻辑是因为JSP页面中EL表达式对逻辑运算的支持不太良好
        model.addAttribute("ELPageValue", (currentPage - 1) / 5 * 5);

        // 当总页数大于5时，需要如下属性
        if (pagePO.getTotalPage() >= 6) {
            model.addAttribute("isOneOfNextFivePage", (pagePO.getTotalPage() - 1) / 5 * 5 + 1);
            model.addAttribute("reachNextFivePage", (currentPage + 4) / 5 * 5 + 1);
        }

        // 当前页面大于等于6页的时候, 需要显示"[...]"按钮--返回到前一个5页
        if (currentPage >= 6) {
            model.addAttribute("returnPreFivePage", (currentPage - 1) / 5 * 5 - 4);
        }

        return "showbook";
    }

    // 借阅图书
    @RequestMapping(value = "/borrow/{bookId}", method = RequestMethod.GET)
    public void borrowBook(@PathVariable("bookId") int bookId, HttpServletResponse response, HttpSession session) throws IOException {
        int uid = Integer.parseInt(session.getAttribute("uid").toString());
        final String SHOWBOOK_PAGE = "/auth/showbook/" + bookId;
        response.setCharacterEncoding("GBK");

        // 根据bookId和uid判断用户是否借阅过此书
        int check = borrowInfoService.checkBookIsBorrow(bookId, uid);
        if (check != 0) {
            printBorrowBookInfo(response, bookId);
        } else {
            BorrowInfoPO borrowInfoPO = new BorrowInfoPO(bookId, uid, DateToString.getStringDate());
            borrowInfoService.save(borrowInfoPO);
            bookInfoService.decBookCountByBookId(bookId);

            PrintWriter out = response.getWriter();
            String builder = "<script language=\"javascript\">" +
                    "alert(\"借阅成功！\");" +
                    "top.location='" +
                    SHOWBOOK_PAGE +
                    "';" +
                    "</script>";

            out.print(builder);
        }
    }

    // 发表评论
    @RequestMapping(value = "/submitcontent.do", method = RequestMethod.POST)
    public String SubmitContent(int uid, int bookId, String content) {
        BookCommentPO bookCommentPO = new BookCommentPO(bookId, uid, content, DateToString.getStringDate());

        bookCommentService.submitContent(bookCommentPO);

        return "showbook";
    }

    // 打印借阅图书的信息
    private void printBorrowBookInfo(HttpServletResponse response, int bookId) throws IOException {
        response.setCharacterEncoding("GBK");

        final String SHOWBOOK_PAGE = "/auth/showbook/" + bookId;

        PrintWriter out = response.getWriter();
        String builder = "<script language=\"javascript\">" +
                "alert(\"你已经借过此书，不能重复借阅！\");" +
                "top.location='" +
                SHOWBOOK_PAGE +
                "';" +
                "</script>";

        out.print(builder);
    }
}
