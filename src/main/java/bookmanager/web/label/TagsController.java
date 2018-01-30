package bookmanager.web.label;

import bookmanager.dao.dbservice.BookLabelService;
import bookmanager.model.po.BookLabelPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: spider_hgyi
 * @Date: Created in 下午1:36 17-11-20.
 * @Modified By:
 * @Description:
 */
@Controller
@RequestMapping("/auth")
public class TagsController {
    private static final Logger logger = LoggerFactory.getLogger(TagsController.class);

    private BookLabelService bookLabelRepository;

    @Autowired
    public TagsController(BookLabelService bookLabelRepository) {
        this.bookLabelRepository = bookLabelRepository;
    }

    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public String showLabel(Model model) {
        List<BookLabelPO> parentLabels = bookLabelRepository.getBookLabelByParentId(0);
        List<BookLabelPO> childrenLabels = bookLabelRepository.getChildrenLabelsByParentId(0);
        Map<String, Map<Integer, String>> labelsName = new HashMap<String, Map<Integer, String>>();

        // 时间复杂度高
        for (BookLabelPO parentLabel : parentLabels) {
            Map<Integer, String> childLabelsName = new HashMap<Integer, String>();
            for (BookLabelPO childrenLabel : childrenLabels) {
                if (parentLabel.getPkId() == childrenLabel.getParentId()) {
                    childLabelsName.put(childrenLabel.getPkId(), childrenLabel.getName());
                }
            }
            labelsName.put(parentLabel.getName(), childLabelsName);
        }

        // 将存储父子标签的Map对象添加进model对象
        model.addAttribute("labelsName", labelsName);

        return "alltags";
    }
}
