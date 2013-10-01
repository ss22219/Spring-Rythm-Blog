package com.mvc.controller;

import com.mvc.model.*;
import com.mvc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private SettingService settingService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        List<Category> categories = categoryService.getCategorys();
        List<Article> lastArticle = articleService.getLastArticle();
        Map<String,String> monthCategory = categoryService.getMonthCategory();
        modelMap.addAttribute("categories",categories);
        modelMap.addAttribute("lastArticle",lastArticle);
        modelMap.addAttribute("monthCategory",monthCategory);

        return "index";
    }
}
