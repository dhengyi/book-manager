package bookmanager.web.login;

import bookmanager.dao.dbservice.BookLabelService;
import bookmanager.dao.dbservice.UserService;
import bookmanager.model.vo.login.UserLoginVO;
import bookmanager.utilclass.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

/**
 * Created by dela on 11/27/17.
 */

@Controller
@RequestMapping(value = "/login")
public class LoginController {
    private UserService userService;
    private BookLabelService bookLabelService;

    @Autowired
    public LoginController(UserService userService, BookLabelService bookLabelService) {
        this.userService = userService;
        this.bookLabelService = bookLabelService;
    }

    // 当URL为/login且请求类型为GET的时候, 默认返回index.jsp页面(即未登录主页面)
    @RequestMapping(method = RequestMethod.GET)
    public String showMainPage() {
        return "index";
    }

    // 当URL为/login且请求类型为POST的时候, 处理用户登录表单
    @RequestMapping(method = RequestMethod.POST)
    public String userLogin(@Valid UserLoginVO user, HttpSession session, HttpServletRequest httpServletRequest) throws UnsupportedEncodingException {
        String username = new String(user.getName().getBytes("iso-8859-1"), "utf-8");

        if (!username.equals("")) {
            // 登录成功, 跳转到main.jsp页面
            if (checkPassword(username, user.getPassword(), user)) {
                session.setAttribute("uid", user.getUid());

                httpServletRequest.setAttribute("bookLabel", bookLabelService.getBookLabelById(0));
                return "main";
            } else {
                // 若不成功, 则带上错误参数返回index.jsp页面(未登录前首页)
                return "redirect:index.jsp?error=yes";
            }
        } else {
            return "redirect:index.jsp?error=yes";
        }

    }

    private boolean checkPassword(String username, String password, UserLoginVO user) {
        UserLoginVO userLoginVO = userService.getPasswordAndUidByName(username);
        String codePasswd = MD5.codeByMD5(password);
        if (userLoginVO.getPassword().equals(codePasswd)) {
            user.setUid(userLoginVO.getUid());
            return true;
        }
        return false;
    }

}
