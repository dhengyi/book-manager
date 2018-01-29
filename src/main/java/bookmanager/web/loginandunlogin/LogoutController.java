package bookmanager.web.loginandunlogin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author: spider_hgyi
 * @Date: Created in 下午4:53 18-1-24.
 * @Modified By:
 * @Description: 注销登录
 */

@Controller
@RequestMapping("/logout.do")
public class LogoutController {
    @RequestMapping(method = RequestMethod.GET)
    public String showIndexPage(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();

        return "redirect:/";
    }
}
