package bookmanager.web.loginandunlogin;

import bookmanager.dao.dbservice.UserService;
import bookmanager.model.vo.UserLoginVO;
import bookmanager.utilclass.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by dela on 11/27/17.
 *
 * @Description: 登录框对应的控制器
 */

@Controller
@RequestMapping(value = "/login.do")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void userLogin(@Valid UserLoginVO user, HttpServletResponse response, HttpSession session) throws IOException {
        String username = new String(user.getName().getBytes("iso-8859-1"), "utf-8");

        if (!username.equals("")) {
            // 登录成功, 初始化main页面的借阅信息, 然后跳转到main.jsp页面
            if (checkPassword(username, user.getPassword(), user)) {
                // 将uid设置到session里
                session.setAttribute("uid", user.getUid());

                response.sendRedirect("/auth/");
            } else {
                printInfo(response);
            }
        } else {
            printInfo(response);
        }
    }

    private boolean checkPassword(String username, String password, UserLoginVO user) {
        UserLoginVO userLoginVO = userService.getPasswordAndUidByUsername(username);

        String codePasswd = MD5.codeByMD5(password);
        if (userLoginVO != null) {
            if (userLoginVO.getPassword().equals(codePasswd)) {
                user.setUid(userLoginVO.getUid());

                return true;
            }
        }

        return false;
    }

    // 登录信息有误
    private void printInfo(HttpServletResponse response) throws IOException {
        // 设置response的字符集, 防止乱码
        response.setCharacterEncoding("GBK");

        final String INDEX_PAGE = "/";    // 未登录的URL

        // 打印登录弹框
        PrintWriter out = response.getWriter();
        String builder = "<script language=\"javascript\">" +
                "alert(\"用户名或密码有误，请重新登录！\");" +
                "top.location='" +
                INDEX_PAGE +
                "';" +
                "</script>";

        out.print(builder);
    }
}