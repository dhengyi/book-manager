package bookmanager.web.label;

import bookmanager.dao.dbservice.BookInfoService;
import bookmanager.dao.dbservice.BookLabelService;
import bookmanager.dao.dbservice.UserService;
import bookmanager.model.po.BookInfoPO;
import bookmanager.model.po.PagePO;
import bookmanager.utilclass.BookUserMapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

/**
 * @Author: spider_hgyi
 * @Date: Created in 下午7:08 18-1-16.
 * @Modified By:
 * @Description:
 */
@Controller
@RequestMapping("/auth")
public class TypesController {
    private static final Logger logger = LoggerFactory.getLogger(TypesController.class);

    private BookInfoService bookInfoService;
    private UserService userService;
    private BookLabelService bookLabelService;

    @Inject
    public TypesController(BookInfoService bookInfoService, UserService userService, BookLabelService bookLabelService) {
        this.bookInfoService = bookInfoService;
        this.userService = userService;
        this.bookLabelService = bookLabelService;
    }

    @RequestMapping(value = "/types", method = RequestMethod.GET)
    public String showTypes(@RequestParam(value = "tag") String labelIdPre,
                          @RequestParam(value = "page", required = false) String pageNowPre,
                          Model model) {
        int labelId, currentPage;
        List<BookInfoPO> bookInfoPOS ;

        if (pageNowPre == null) {
            pageNowPre = "1";
        }

        currentPage = Integer.parseInt(pageNowPre);
        labelId = Integer.parseInt(labelIdPre);
        PagePO pagePO = new PagePO(currentPage);

        String labelName = bookLabelService.getNameByPkId(labelId);
        bookInfoPOS = bookInfoService.getBookByLabelIdAndPage(pagePO, labelId);
        int bookCount = bookInfoService.getBookCountByLabelId(labelId);
        pagePO.setTotalCount(bookCount);
        pagePO.setTotalPage((bookCount % 5 == 0) ? bookCount / 5 : bookCount / 5 + 1);

        // 得到相应书籍的拥有者姓名
        Map<BookInfoPO, String> bookInfoPOStringMap =
                BookUserMapUtil.getBookInfo(bookInfoPOS, userService);

        model.addAttribute("books", bookInfoPOStringMap);
        model.addAttribute("pageInfo", pagePO);
        model.addAttribute("labelName", labelName);
        model.addAttribute("labelId", labelId);

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

        return "showtype";
    }
}
