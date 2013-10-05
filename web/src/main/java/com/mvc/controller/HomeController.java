package com.mvc.controller;

import com.mvc.model.Setting;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    SessionFactory sessionFactory;

    @RequestMapping("/")
    public String hello(ModelMap map) {
        Setting setting = (Setting) sessionFactory.openSession().createQuery("from Setting where key='blogTitle'").list().get(0);
        map.addAttribute("title", setting.getValue());
        return "index";
    }
}
