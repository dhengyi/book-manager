package bookmanager.web.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by dela on 1/18/18.
 *
 * @Description: 对于想要在Spring中使用过滤器, 就要继承OncePerRequestFilter
 * OncePerRequestFilter, 顾名思义, 就是每个请求只过一遍这个过滤器
 */

public class LoginFilter extends OncePerRequestFilter {
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String INDEX_PAGE = "/";    // 未登录的URL
        Object sessionId = null;

        HttpSession session = httpServletRequest.getSession(false);
        if (session != null) {
            sessionId = session.getAttribute("uid");
            if (sessionId != null) {
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            }
        }

        // 如果当前的操作的用户没有登录令牌, 那就弹出弹框提示重新登录, 并跳转到未登录页面
        if (session == null || sessionId == null) {
            // 设置response的字符集, 防止乱码
            httpServletResponse.setCharacterEncoding("GBK");

            PrintWriter out = httpServletResponse.getWriter();
            String builder = "<script language=\"javascript\">" +
                    "alert(\"网页过期，请重新登录！\");" +
                    "top.location='" +
                    INDEX_PAGE +
                    "';" +
                    "</script>";

            out.print(builder);
        }
    }
}