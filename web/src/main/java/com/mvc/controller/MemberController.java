package com.mvc.controller;

import com.mvc.exception.ServiceException;
import com.mvc.model.User;
import com.mvc.service.UserService;
import com.mvc.viewmodel.Login;
import com.mvc.viewmodel.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap modelMap, String returnUrl, String message) {
        if (message != null && message.length() > 0)
            modelMap.addAttribute("message", message);
        modelMap.addAttribute("returnUrl", returnUrl);
        return "member/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(@Valid Login login, BindingResult result, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, String returnUrl) throws IOException {

        modelMap.addAttribute("returnUrl", returnUrl);
        List<String> errors = new ArrayList<String>();
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                errors.add(error.getDefaultMessage());
            }
        } else {
            User user = userService.login(login.getUserName(), login.getPassword());
            if (user == null) {
                errors.add("登录失败，用户名不存在或密码错误。");
            } else {
                request.getSession().setAttribute("user", user);
                if (returnUrl == null || returnUrl.length() == 0) {
                    response.sendRedirect("/");
                } else {
                    response.sendRedirect(returnUrl);
                }
                return null;
            }
        }

        modelMap.addAttribute("errors", errors);
        return "member/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "/member/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(@Valid Register register, BindingResult result, ModelMap modelMap, HttpServletResponse response) throws IOException {
        List<String> errors = new ArrayList<String>();
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                errors.add(error.getDefaultMessage());
            }
        } else if (!register.getPassword().equals(register.getRePassword())) {
            errors.add("两次密码不一致。");
        } else {
            User user = new User();
            user.setEmail(register.getEmail());
            user.setNiceName(register.getUserName());
            user.setUserName(register.getUserName());
            user.setPassword(register.getPassword());
            try {
                userService.register(user);
                response.sendRedirect("/member/login?message=" + URLEncoder.encode("注册成功。", "utf-8"));
            } catch (ServiceException e) {
                errors.add(e.getMessage());
            }
        }
        modelMap.addAttribute("errors", errors);
        return "/member/register";
    }

    @RequestMapping(value = "/lostPassword", method = RequestMethod.GET)
    public String lostPassword() {
        return "member/lostPassword";
    }

    @RequestMapping(value = "/lostPassword", method = RequestMethod.POST)
    public String lostPassword(String userName, ModelMap modelMap) {
        List<String> errors = new ArrayList<String>();
        try {
            userService.lostPassword(userName);
        } catch (ServiceException e) {
            errors.add(e.getMessage());
        }
        modelMap.addAttribute("errors", errors);
        return "member/lostPassword";
    }

    @RequestMapping(value = "/logOut")
    public void logOut(HttpServletResponse response, HttpServletRequest request) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect("/member/login?message=" + URLEncoder.encode("你已经成功退出登录。", "utf-8"));
    }
}
