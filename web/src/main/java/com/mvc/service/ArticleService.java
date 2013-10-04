package com.mvc.service;

import com.mvc.exception.ServiceException;
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

    public Article getArticle(int id) throws ServiceException {
        Article article = articleRepository.getArticle(id);
        if (article == null)
            throw new ServiceException("抱歉，你访问的地址貌似有问题？");
        return article;
    }

    public Page<Article> getArticleByDay(int year, int month, int day, int pageIndex) {
        int pageSize = Integer.parseInt(settingService.getSetting("articlePageSize"));
        List<Article> list = articleRepository.getArticleByDay(year, month, day, 1, 5);
        return new Page<Article>(list, pageIndex, pageSize, articleRepository.getDayArticleCount(year, month, day), 5);
    }

    public Page<Article> getArticleByMonth(int year, int month, int pageIndex) {
        int pageSize = Integer.parseInt(settingService.getSetting("articlePageSize"));
        List<Article> list = articleRepository.getArticleByMonth(year, month, pageIndex, pageSize);
        return new Page<Article>(list, pageIndex, pageSize, articleRepository.getMonthArticleCount(year, month), 5);
    }

    public void addBrowseCount(int articleId) throws ServiceException {
        Article article = getArticle(articleId);
        article.setBrowseCount(article.getBrowseCount() + 1);
        articleRepository.save(article);
    }

    public void addCommentCount(int articleId) throws ServiceException {
        Article article = getArticle(articleId);
        article.setCommentCount(article.getCommentCount() + 1);
        articleRepository.save(article);
    }
}
