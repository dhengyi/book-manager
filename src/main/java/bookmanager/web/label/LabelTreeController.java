package bookmanager.web.label;

import bookmanager.dao.dbservice.BookLabelService;
import bookmanager.dao.dbservice.UserService;
import bookmanager.model.po.BookLabelPO;
import bookmanager.model.vo.label.AddLabelVO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
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
public class LabelTreeController {
    @Autowired
    private BookLabelService bookLabelRepository;

    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public String showLabel(Model model) {
        List<BookLabelPO> parentLabels = bookLabelRepository.getParentLabelsByParentId(0);
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
