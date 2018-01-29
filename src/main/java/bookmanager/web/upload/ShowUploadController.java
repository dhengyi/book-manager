package bookmanager.web.upload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: spider_hgyi
 * @Date: Created in 下午3:10 18-1-25.
 * @Modified By:
 * @Description:
 */
@Controller
@RequestMapping("/auth")
public class ShowUploadController {
    @RequestMapping("/upload")
    public String showUploadPage() {
        return "pushbook";
    }
}
