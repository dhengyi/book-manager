package bookmanager.web.loginandunlogin;

import bookmanager.dao.dbservice.BookInfoService;
import bookmanager.dao.dbservice.UserService;
import bookmanager.model.po.BookInfoPO;
import bookmanager.model.po.PagePO;
import bookmanager.utilclass.BookUserMapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by dela on 1/6/18.
 *
 * @Description: 登录后搜索框对应的控制器
 */

@Controller
@RequestMapping("/auth")
public class SearchBooksController {
    private static final Logger logger = LoggerFactory.getLogger(SearchBooksController.class);

    private BookInfoService bookInfoService;
    private UserService userService;

    @Autowired
    public SearchBooksController(BookInfoService bookInfoService, UserService userService) {
        this.bookInfoService = bookInfoService;
        this.userService = userService;
    }

    @RequestMapping(value = "/search", method = POST)
    public String search(HttpServletRequest httpServletRequest, Model model) throws UnsupportedEncodingException {
        int currentPage = 1;
        PagePO pagePO = new PagePO(currentPage);

        String keyword = new String(httpServletRequest.getParameter("keyword").getBytes("iso-8859-1"), "utf-8");
        model.addAttribute("keyword", keyword);

        int bookCount = bookInfoService.getBookCountByNAO(keyword);
        pagePO.setTotalCount(bookCount);
        pagePO.setTotalPage((bookCount % 5 == 0) ? bookCount / 5 : bookCount / 5 + 1);

        keyword = "%" + keyword + "%";
        List<BookInfoPO> bookInfoPOS = bookInfoService.getBookInfoByNAOAndPage(keyword, pagePO);

        Map<BookInfoPO, String> bookInfoPOStringMap = BookUserMapUtil.getBookInfo(bookInfoPOS, userService);

        model.addAttribute("books", bookInfoPOStringMap);
        model.addAttribute("pageInfo", pagePO);

        // 在这里添加分页的逻辑是因为JSP页面中EL表达式对逻辑运算的支持不太良好
        model.addAttribute("ELPageValue", (currentPage - 1) / 5 * 5);

        // 当总页数大于5时，需要如下属性
        if (pagePO.getTotalPage() >= 6) {
            model.addAttribute("isOneOfNextFivePage", (pagePO.getTotalPage() - 1) / 5 * 5 + 1);
            model.addAttribute("reachNextFivePage", (currentPage + 4) / 5 * 5 + 1);
        }

        return "showresult";
    }

    @RequestMapping(value = "/search", method = GET)
    public String searchOnePage(@RequestParam(value = "keyword") String keyword,
                                @RequestParam(value = "page", required = false) String page, Model model) throws UnsupportedEncodingException {
        int currentPage = 1;
        if (page != null) {
            currentPage = Integer.parseInt(page);
        }
        PagePO pagePO = new PagePO(currentPage);

        keyword = new String(keyword.getBytes("iso-8859-1"), "utf-8");
        model.addAttribute("keyword", keyword);

        int bookCount = bookInfoService.getBookCountByNAO(keyword);
        pagePO.setTotalCount(bookCount);
        pagePO.setTotalPage((bookCount % 5 == 0) ? bookCount / 5 : bookCount / 5 + 1);

        keyword = "%" + keyword + "%";
        List<BookInfoPO> bookInfoPOList = bookInfoService.getBookInfoByNAOAndPage(keyword, pagePO);

        Map<BookInfoPO, String> bookInfoPOStringMap = BookUserMapUtil.getBookInfo(bookInfoPOList, userService);
        model.addAttribute("books", bookInfoPOStringMap);
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

        return "showresult";
    }
}