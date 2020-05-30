package com.xyj.demo.controller;

import com.xyj.demo.pojo.User;
import com.xyj.demo.result.Result;
import com.xyj.demo.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserServer userServer;

    @CrossOrigin
    @PostMapping(value="api/login")
    @ResponseBody
    public Result login(@RequestBody User user, HttpSession session) {
        String username = user.getUsername();
        username = HtmlUtils.htmlEscape(username);

        User user1 = userServer.get(username, user.getPassword());
        if (null == user1) {
            return new Result(400, "账号密码出错");
        } else {
            session.setAttribute("user", user1);
            return new Result(200, "登录成功");
        }
    }
}
