package com.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mvc.util.Page;
import com.mvc.model.*;
import com.mvc.service.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/cat/{categoryId}/{page}")
    public String cat(@PathVariable int categoryId, @PathVariable int page, ModelMap map, HttpServletResponse response) throws IOException {
        page = page >= 1 ? page : 1;
        Page<Article> articles = articleService.getArticleByCategory(categoryId, page);
        Category category = categoryService.getCategory(categoryId);
        if (category == null) {
            response.sendError(404);
            return null;
        }
        map.put("articles", articles);
        map.put("title", category.getName());
        return "category/cat";
    }

    @RequestMapping(value = "/cat/{categoryId}")
    public String cat(@PathVariable int categoryId, ModelMap map, HttpServletResponse response) throws IOException {
        return cat(categoryId, 1, map, response);
    }


    @RequestMapping(value = "/month/{month}/{page}")
    public String month(@PathVariable String month, @PathVariable int page, ModelMap map, HttpServletResponse response) throws IOException {
        page = page >= 1 ? page : 1;
        Map<String, String> monthCategory = categoryService.getMonthCategory();
        if (!monthCategory.containsKey(month)) {
            response.sendError(404);
            return null;
        }
        try {
            int year = Integer.parseInt(month.substring(0, 4));
            int mon = Integer.parseInt(month.substring(5, month.length()));
            map.put("title", year + "年" + month + "月");
            Page<Article> articles = articleService.getArticleByMonth(year, mon, page);
            map.put("articles", articles);
            return "/category/cat";
        } catch (Exception e) {
            response.sendError(404);
            return null;
        }
    }

    @RequestMapping(value = "/month/{month}")
    public String month(@PathVariable String month, ModelMap map, HttpServletResponse response) throws IOException {
        return month(month, 1, map, response);
    }

    @RequestMapping(value = "/day/{day}")
    public String day(@PathVariable String day, ModelMap map, HttpServletResponse response) throws IOException {
        return day(day, 1, map, response);
    }

    @RequestMapping(value = "/day/{day}/{page}")
    public String day(@PathVariable String day, @PathVariable int page, ModelMap map, HttpServletResponse response) throws IOException {
        page = page >= 1 ? page : 1;
        try {
            int year = Integer.parseInt(day.substring(0, 4));
            int month = Integer.parseInt(day.substring(5, day.lastIndexOf('-')));
            int dayNo = Integer.parseInt(day.substring(day.lastIndexOf('-') + 1, day.length()));
            map.put("title", year + "年" + month + "月" + dayNo + "日");
            Page<Article> articles = articleService.getArticleByDay(year, month, dayNo, page);
            map.put("articles", articles);
            return "/category/cat";
        } catch (Exception e) {
            response.sendError(404);
            return null;
        }
    }
}
