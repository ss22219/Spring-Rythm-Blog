package com.mvc.controller;

import com.mvc.model.Article;
import com.mvc.model.Category;
import com.mvc.service.ArticleService;
import com.mvc.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/{title}/{page}")
    public String search(@PathVariable String title, @PathVariable int page, ModelMap map) {
        page = page >= 1 ? page : 1;
        Page<Article> articles = articleService.getArticleByName(title, page);

        map.put("articles", articles);
        map.put("title", title);
        map.put("search", title);
        return "/category/cat";
    }

    @RequestMapping("/{title}")
    public String search(@PathVariable String title, ModelMap map) {
        return search(title, 1, map);
    }

}
