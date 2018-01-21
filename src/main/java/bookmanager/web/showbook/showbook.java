package bookmanager.web.showbook;

import bookmanager.dao.dbservice.BookCommentService;
import bookmanager.dao.dbservice.BookInfoService;
import bookmanager.dao.dbservice.BorrowInfoService;
import bookmanager.dao.dbservice.UserService;
import bookmanager.model.po.BookCommentPO;
import bookmanager.model.po.BookInfoPO;
import bookmanager.model.po.BorrowInfoPO;
import bookmanager.utilclass.BookUserMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by dongmengyuan on 18-1-3.
 */

@Controller
@RequestMapping(value = "/showbook")
public class showbook {
    private UserService userService;
    private BookInfoService bookInfoService;
    private BorrowInfoService borrowInfoService;
    private BookCommentService bookCommentService;

    @Autowired
    public showbook(UserService userService, BookInfoService bookInfoService, BorrowInfoService borrowInfoService, BookCommentService bookCommentService) {
        this.userService = userService;
        this.bookInfoService = bookInfoService;
        this.borrowInfoService = borrowInfoService;
        this.bookCommentService = bookCommentService;
    }


    //显示图书详情
    @RequestMapping(value = "/showBook/{bookInfoPkId}/{ugk_uid}",method = RequestMethod.GET)
    public String showBook(@PathVariable(value = "bookInfoPkId") Integer bookInfoPkId, @PathVariable(value = "ugk_uid") Integer ugk_uid, Model model) {

        System.out.println(bookInfoPkId);
        BookInfoPO bookInfoPO = bookInfoService.getBookByPkId(bookInfoPkId);
        List<BookCommentPO> bookCommentPO = bookCommentService.getContent(bookInfoPkId);
        //获取评论者的名字（id-->name)
        Map<BookCommentPO, String> username = BookUserMapUtil.getUsername(bookCommentPO, userService);
        //获取书籍上传者的名字（id-->name)
        Map<BookInfoPO, String> BookInfo = BookUserMapUtil.getOneBookInfo(bookInfoPO, userService);


        int borrowCount = borrowInfoService.getBorrowCount(bookInfoPkId);
        model.addAttribute("borrowCount",borrowCount);

        model.addAttribute("bookMap",BookInfo);
        model.addAttribute("book",bookInfoPO);
        model.addAttribute("content",username);
        model.addAttribute("contentCount",bookCommentPO.size());
        return "showbook";
    }


    //借阅图书
    @RequestMapping(value = "/borrowBook",method = RequestMethod.POST)
    @ResponseBody
    public String borrowBook(BorrowInfoPO borrowInfoPO, HttpSession session) {
        String msg;
        //判断用户是否登录
        if(borrowInfoPO.getCsUserId()==0){
            msg="false";
            return msg;
        }

        //日期格式转换
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        borrowInfoPO.setBorrowDate(simpleDateFormat.format(new Date()));
        //检查该用户是否借阅过此书
        BorrowInfoPO check = borrowInfoService.checkBookIsBorrow(borrowInfoPO);
        if(check!=null){
            msg="您已借过该本书";
            return msg;
        }
        borrowInfoService.save(borrowInfoPO);
        msg="true";
        return msg;

    }

    //发表评论
    @RequestMapping(value = "/submitContent",method = RequestMethod.POST)
    public String SubmitContent(BookCommentPO bookCommentPO) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        bookCommentPO.setCommentDatetime(simpleDateFormat.format(new Date()));
        bookCommentService.submitContent(bookCommentPO);

        return "showbook";
    }
}
