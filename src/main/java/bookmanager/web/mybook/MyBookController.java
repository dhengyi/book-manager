package bookmanager.web.mybook;

import bookmanager.dao.dbservice.*;
import bookmanager.model.po.BookInfoPO;
import bookmanager.model.po.PagePO;
import bookmanager.model.po.ReturnInfoPO;
import bookmanager.utilclass.BookUserMapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by dongmengyuan on 18-1-3.
 *
 * @Description: 我的书籍页面对应的控制器
 */

@Controller
@RequestMapping(value = "/auth/mybook")
public class MyBookController {
    private static final Logger logger = LoggerFactory.getLogger(MyBookController.class);

    private UserService userService;
    private BookInfoService bookInfoService;
    private ReturnInfoService returnInfoService;
    private BorrowInfoService borrowInfoService;

    @Autowired
    public MyBookController(UserService userService, BookInfoService bookInfoService, ReturnInfoService returnInfoService, BorrowInfoService borrowInfoService) {
        this.userService = userService;
        this.bookInfoService = bookInfoService;
        this.returnInfoService = returnInfoService;
        this.borrowInfoService = borrowInfoService;
    }

    // 我的上传书籍部分
    @RequestMapping(value = {"", "/upload", "/upload/{page}"}, method = RequestMethod.GET)
    public String getUploadBook(Model model, HttpSession session, @PathVariable(value = "page", required = false) String pagePre) {
        int currentPage = 1;
        if (pagePre != null) {
            currentPage = Integer.parseInt(pagePre);
        }

        PagePO pagePO = new PagePO(currentPage);
        // 拿到当前登录者的id
        Object obj = session.getAttribute("uid");
        int uid = Integer.parseInt(obj.toString());

        // 获取我所上传的书的总数
        int uploadBookCount = bookInfoService.getUploadBookCountByUid(uid);
        pagePO.setTotalCount(uploadBookCount);
        pagePO.setTotalPage((uploadBookCount % 5 == 0) ? uploadBookCount / 5 : uploadBookCount / 5 + 1);

        // 获取我所上传的书(根据页数)
        List<BookInfoPO> uploadBookInfos = bookInfoService.getUploadBookByUidAndPage(uid, pagePO);
        // 获取我上传的书的被借次数
        Map<BookInfoPO, Integer> uploadBorrowCount = BookUserMapUtil.getBorrowCount(uploadBookInfos, borrowInfoService);

        // 把数据封装起来传给前端页面
        model.addAttribute("uploadBorrowCount", uploadBorrowCount);
        model.addAttribute("uploadBooks", uploadBookInfos);
        model.addAttribute("pageInfo", pagePO);
        model.addAttribute("select", 0);

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

        return "mybooks";
    }

    // 我所借阅书籍的部分
    @RequestMapping(value = {"/borrow", "/borrow/{page}"}, method = RequestMethod.GET)
    public String getBorrowBook(Model model, HttpSession session, @PathVariable(value = "page", required = false) String pagePre) {
        int currentPage = 1;
        if (pagePre != null) {
            currentPage = Integer.parseInt(pagePre);
        }

        PagePO pagePO = new PagePO(currentPage);

        // 拿到当前登录者的id
        Object obj = session.getAttribute("uid");
        int uid = Integer.parseInt(obj.toString());

        // 获取我所借阅的书的总数
        int borrowBookCount = borrowInfoService.getBorrowCountByUid(uid);
        pagePO.setTotalCount(borrowBookCount);
        pagePO.setTotalPage((borrowBookCount % 5 == 0) ? borrowBookCount / 5 : borrowBookCount / 5 + 1);

        // 获取我所借阅的书
        List<BookInfoPO> borrowBookInfoPOS = bookInfoService.getBorrowBookByUid(uid, pagePO);
        // 用工具类找到被借阅书籍的所有者姓名
        Map<BookInfoPO, String> borrowBookInfosMap = BookUserMapUtil.getBookInfo(borrowBookInfoPOS, userService);
        // 获取我借阅的书被借次数
        Map<BookInfoPO, Integer> borrowBookCountMap = BookUserMapUtil.getBorrowCount(borrowBookInfoPOS, borrowInfoService);

        // 把数据封装起来传给前端页面
        model.addAttribute("borrowBookCount", borrowBookCountMap);
        model.addAttribute("borrowBooks", borrowBookInfosMap);
        model.addAttribute("pageInfo", pagePO);
        model.addAttribute("select", 1);

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

        return "mybooks";
    }

    // 下架图书意味着删除数据库中与这本书所关联的所有信息
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteBook(int pkId) throws IOException {
        bookInfoService.deleteBookByBookId(pkId);

        return "mybooks";
    }

//    // 为展现图书信息的页面进行表单回填做准备
//    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
//    public String updateBook(BookInfoPO bookInfo, HttpSession session) throws ServletException, IOException {
//        System.out.println(bookInfo);
//        System.out.println(session.toString());
//        session.setAttribute("bookInfo", bookInfo);
//
//        return "mybooks";
//    }
//
//    // 修改书籍的页面
//    @RequestMapping(value = "/edit", method = RequestMethod.GET)
//    public String showEditPage(HttpSession session) {
//        BookInfoPO bookInfoPO = (BookInfoPO) session.getAttribute("bookInfo");
//        ModelAndView modelAndView = new ModelAndView();
//
//        modelAndView.addObject("bookInfo", bookInfoPO);
//        session.removeAttribute("bookInfo");
//
//        return "editbook";
//    }

//    // 更新书籍后进行的后台处理
//    @RequestMapping(value = "/edit.do", method = RequestMethod.POST)
//    public void editBookInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        request.setCharacterEncoding("utf8");
//        response.setCharacterEncoding("utf8");
//
//        int bookId = Integer.parseInt(request.getParameter("bookId"));
//        String bookName = request.getParameter("bookName");
//        String author = request.getParameter("author");
//        int amount = Integer.parseInt(request.getParameter("amount"));
//
//        BookInfoPO bookInfoPO = new BookInfoPO();
//
//        // 保存修改书籍的信息
//        bookInfoPO.setPkId(bookId);
//        bookInfoPO.setUgkName(bookName);
//        bookInfoPO.setAuthor(author);
//        bookInfoPO.setAmount(amount);
//
//        bookInfoService.updateBook(bookInfoPO);
//
//        response.sendRedirect("/auth/mybook");
//    }

    // TODO 更新图书信息的AJAX做法
//    @RequestMapping(value = "/editbook", method = RequestMethod.POST)
//    public void editBook(Integer pkId, HttpServletResponse response) throws IOException {
//        // 获取图书信息
//        BookInfoPO book = bookInfoService.getBookByPkId(pkId);
//
//        // 自己封装的vo
//        EditBook edit = new EditBook();
//        edit.setBookInfoPO(book);
//
//        // 将返回的数据转换成json格式
//        Gson gson = new Gson();
//        String json = gson.toJson(edit);
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json; charset=utf-8");
//        PrintWriter writer = response.getWriter();
//        // 将json字符串返回到前端
//        writer.append(json);
//    }

    // 归还图书
    @RequestMapping(value = "/return", method = RequestMethod.POST)
    public String returnBook(ReturnInfoPO returnInfoPO) throws IOException {
        // 添加归还信息
        returnInfoService.save(returnInfoPO);
        // 更新书籍信息表中书籍的数量
        bookInfoService.updateBookCountByBookId(returnInfoPO.getBookInfoPkId());
        // 删除借阅表中的数据
        borrowInfoService.deleteBorrowInfoByBookIdAndUid(returnInfoPO.getBookInfoPkId(), returnInfoPO.getCsUserId());

        return "mybooks";
    }
}