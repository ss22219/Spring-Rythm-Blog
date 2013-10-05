package com.mvc.controller;

import com.mvc.model.Setting;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    SessionFactory sessionFactory;

    @RequestMapping("/")
    public String hello(ModelMap map) {
        map.addAttribute("message", ((Setting) sessionFactory.openSession().createQuery("from Setting where key='blogDescription'").list().get(0)).getValue());
        return "index";
    }
}
