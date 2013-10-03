package com.mvc.repository;

import com.google.appengine.repackaged.org.joda.time.YearMonth;
import com.mvc.domain.DomainType;
import com.mvc.model.Article;
import com.mvc.model.Category;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class ArticleRepository extends BaseRepository<Article> {
    public List<Article> getLastArticles(int count) {
        return getSession().createQuery("from Article where deleted=false and status=" + DomainType.ArticleStatusOpen + " order by createDate desc").setMaxResults(count).list();
    }

    public List<Article> getArticleByDay(int year, int month, int day, int pageIndex, int pageSize) {
        return getSession().createSQLQuery("select * from article where deleted=false and status=" + DomainType.ArticleStatusOpen + " and day(create_date) = " + day + " and month(create_date) = " + month + "  and year(create_date) =" + year).addEntity(Article.class).list();
    }

    public int getDayArticleCount(int year, int month, int day) {
        List list = getSession().createSQLQuery("select count(*) from article where deleted=false and status=" + DomainType.ArticleStatusOpen + " and day(create_date) = " + day + " and month(create_date) = " + month + "  and year(create_date) =" + year).list();
        return list.size() == 0 ? 0 : Integer.parseInt(list.get(0).toString());
    }

    public List<Article> getArticleByMonth(int year, int month, int pageIndex, int pageSize) {
        return getSession().createSQLQuery("select * from article where deleted=false and status=" + DomainType.ArticleStatusOpen + " and  month(create_date) = " + month + "  and year(create_date) =" + year).addEntity(Article.class)
                .setFirstResult((pageIndex - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public int getMonthArticleCount(int year, int month) {
        List list = getSession().createSQLQuery("select count(*) from article where deleted=false and status=" + DomainType.ArticleStatusOpen + " and month(create_date) = " + month + "  and year(create_date) =" + year).list();
        return list.size() == 0 ? 0 : Integer.parseInt(list.get(0).toString());
    }

    public Article getArticle(int id) {
        List<Article> list = getSession().createQuery("from Article where articleId=" + id).list();
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<Article> getArticleByCategory(int categoryId, int pageIndex, int pageSize) {
        List<Article> list = getSession().createQuery("select articles from Category as cat where cat.categoryId=" + categoryId).setFirstResult((pageIndex - 1) * pageSize).setMaxResults(pageSize).list();
        for (Article article : list) {
            if (article.isDeleted() || article.getStatus() != DomainType.ArticleStatusOpen) {
                list.remove(article);
            }
        }
        return list;
    }

    public int getCategoryArticleCount(int categoryId) {
        List<Category> list = getSession().createQuery("from Category where categoryId=" + categoryId).list();
        return list.size() > 0 ? list.get(0).getArticleCount() : 0;
    }
}
