package bookmanager.web.label;

import bookmanager.dao.dbservice.BookInfoService;
import bookmanager.dao.dbservice.BookLabelService;
import bookmanager.dao.dbservice.BookRelationLabelService;
import bookmanager.dao.dbservice.UserService;
import bookmanager.model.po.BookInfoPO;
import bookmanager.model.po.PagePO;
import bookmanager.utilclass.BookUserMapUtil;
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
public class TypesController {
    private BookInfoService bookInfoService;
    private UserService userService;
    private BookLabelService bookLabelService;

    @Inject
    public TypesController(BookInfoService bookInfoService, UserService userService, BookLabelService bookLabelService) {
        this.bookInfoService = bookInfoService;
        this.userService = userService;
        this.bookLabelService = bookLabelService;
    }

    @RequestMapping(value = "/label", method = RequestMethod.GET)
    public String showTypes(@RequestParam(value = "type") String labelIdPre,
                          @RequestParam(value = "page", required = false) String pageNowPre,
                          Model model) {
        int labelId, pageNow;
        List<BookInfoPO> bookInfoPOS = null;

        if (pageNowPre == null) {
            pageNowPre = "1";
        }

        pageNow = Integer.parseInt(pageNowPre);
        labelId = Integer.parseInt(labelIdPre);
        PagePO pagePO = new PagePO(pageNow);

        String labelName = bookLabelService.getNameByPkId(labelId);
        bookInfoPOS = bookInfoService.getBookByLabelAndPage(pagePO, labelId);
        int bookCount = bookInfoService.getBookCountByLabel(labelId);
        pagePO.setTotalPage((bookCount % 5 == 0) ? bookCount / 5 : bookCount / 5 + 1);

        // 得到相应书籍的拥有者姓名
        Map<BookInfoPO, String> bookInfoPOStringMap =
                BookUserMapUtil.getBookInfo(bookInfoPOS, userService);

        model.addAttribute("books", bookInfoPOStringMap);
        model.addAttribute("pageInfo", pagePO);
        model.addAttribute("labelname", labelName);
        model.addAttribute("labelid", labelId);

        System.out.println(pagePO);

        return "showtype";
    }
}
