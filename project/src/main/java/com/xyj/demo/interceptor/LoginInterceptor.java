package com.xyj.demo.interceptor;

import com.xyj.demo.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String contextPath = session.getServletContext().getContextPath();
        String[] requireAuthPages = new String[] {
                "index",
        };
        System.out.println(contextPath);
        String uri = request.getRequestURI();   // 返回除去host（域名或者ip）部分的路径
        System.out.println(uri);
        uri = StringUtils.remove(uri, contextPath+"/");
        String page = uri;
        System.out.println(page);
        if (beginWith(page, requireAuthPages)) {
            User user = (User) session.getAttribute("user");
            if (null == user) {
                response.sendRedirect ("login");
                return false;
            }
        }
        return true;
    }


    private boolean beginWith(String page, String[] requiredAuthPages) {
        boolean result = false;
        for (String requiredAuthpage : requiredAuthPages) {
            if (StringUtils.startsWith(page, requiredAuthpage)) {
                result = true;
                break;
            }
        }
        return result;
    }

}
