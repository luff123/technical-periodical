package org.luff.tp.controller;

import org.luff.tp.model.UserModel;
import org.luff.tp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public UserModel login(String id, String password, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return userService.login(id, password);
    }

    @RequestMapping("/register")
    @ResponseBody
    public String register(String id, String name, String password, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return userService.register(id, name, password);
    }

    @RequestMapping("/change_password")
    @ResponseBody
    public String changePassword(String id, String originalPassword, String newPassword, HttpServletResponse response) {
        System.out.println("id: " + id);
        System.out.println("originatePassword: " + originalPassword);
        System.out.println("newPassword: " + newPassword);
        response.setHeader("Access-Control-Allow-Origin", "*");
        return userService.changePassword(id, originalPassword, newPassword);
    }

    @RequestMapping("/change_name")
    @ResponseBody
    public String changeName(String id, String name, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return userService.changeName(id, name);
    }
}
