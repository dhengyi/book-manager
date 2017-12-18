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
@RequestMapping("/bookmanager")
public class LabelTreeController {
    private BookLabelService bookLabelRepository;
    private UserService csUserRepository;

    @Autowired
    public LabelTreeController(BookLabelService bookLabelRepository,
                               UserService UserRepository) {
        this.bookLabelRepository = bookLabelRepository;
        this.csUserRepository = UserRepository;
    }

    @RequestMapping(value = "/label", method = RequestMethod.GET)
    public String showLabel(Model model) {
        List<BookLabelPO> parentLabels = bookLabelRepository.getParentLabelsByParentId(0);
        List<BookLabelPO> childrenLabels = bookLabelRepository.getChildrenLabelsNyByParentId(0);
        Map<String, List<String>> labelsName = new HashMap<String, List<String>>();

        // 时间复杂度高
        for (BookLabelPO parentLabel : parentLabels) {
            List<String> childLabelsName = new ArrayList<String>();
            for (BookLabelPO childrenLabel : childrenLabels) {
                if (parentLabel.getPkId() == childrenLabel.getParentId()) {
                    childLabelsName.add(childrenLabel.getName());
                }
            }
            labelsName.put(parentLabel.getName(), childLabelsName);
        }

        // 将存储父子标签的Map对象添加进model对象
        model.addAttribute("labelsName", labelsName);

        return "label";
    }

    /**
     * @param:
     * @return:
     * @Description: 添加标签功能所对应的控制器
     */
    @RequestMapping(value = "/label", method = RequestMethod.POST)
    public String addLabel(AddLabelVO addLabel) {
        int uid = addLabel.getUid();
        int privilege = csUserRepository.getPrivilegeByUid(uid);

        // 只有管理者才能添加标签
        if (privilege == 1) {
            String parentLabelName = addLabel.getParentName();
            String childLabelName = addLabel.getChildName();
            int parentLabelId = bookLabelRepository.getParentLabelIdByParentLabelName(parentLabelName);
            BookLabelPO label = new BookLabelPO(childLabelName, parentLabelId);
            bookLabelRepository.insertNewLabel(label);
        } else {
            // 提示用户无添加标签的权限
        }

        return "label";
    }
}
