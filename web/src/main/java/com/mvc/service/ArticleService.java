package com.mvc.service;

import com.mvc.model.Article;
import com.mvc.repository.ArticleRepository;
import com.mvc.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private SettingService settingService;

    public Page<Article> getArticleByCategory(int categoryId, int pageIndex) {
        int pageSize = Integer.parseInt(settingService.getSetting("articlePageSize"));
        List<Article> list = articleRepository.getArticleByCategory(categoryId, pageIndex, pageSize);
        Page<Article> page = new Page<Article>(list, pageIndex, pageSize, articleRepository.getCategoryArticleCount(categoryId), 4);
        return page;
    }

    public List<Article> getLastArticle() {
        int lastArticleCount = Integer.parseInt(settingService.getSetting("lastArticleCount"));
        return articleRepository.getLastArticles(lastArticleCount);
    }

    public Article getArticle(int id) {
        return articleRepository.getArticle(id);
    }

    public List<Article> getArticleByDay(Date day) {
        return articleRepository.getArticleByDay(day, 1, 5);
    }

    public Page<Article> getArticleByMonth(Date month, int pageIndex) {
        int pageSize = Integer.parseInt(settingService.getSetting("articlePageSize"));
        List<Article> list = articleRepository.getArticleByMonth(month, pageIndex, pageSize);
        return new Page<Article>(list, pageIndex, pageSize, articleRepository.getMonthArticleCount(month), 4);
    }

}
