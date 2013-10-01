package com.mvc.repository;

import com.mvc.model.Article;
import com.mvc.model.Category;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class ArticleRepository extends BaseRepository<Article> {
    public List<Article> getLastArticles(int count) {
        return getSession().createQuery("from Article order by createDate desc").setMaxResults(count).list();
    }

    public List<Article> getArticleByDay(Date day, int pageIndex, int pageSize) {
        return getSession().createSQLQuery("select * from article where day(create_date) = " + day.getDay() + " and month(create_date) = " + day.getMonth() + "  and year(create_date) =" + day.getYear()).list();
    }

    public int getDayArticleCount(Date day) {
        List list = getSession().createSQLQuery("select count(*) from article where day(create_date) = " + day.getDay() + " and month(create_date) = " + day.getMonth() + "  and year(create_date) =" + day.getYear()).list();
        return list.size() == 0 ? 0 : Integer.parseInt(list.get(0).toString());
    }

    public List<Article> getArticleByMonth(Date day, int pageIndex, int pageSize) {
        return getSession().createSQLQuery("select * from article where month(create_date) = " + day.getMonth() + "  and year(create_date) =" + day.getYear())
                .setFirstResult((pageIndex - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public int getMonthArticleCount(Date date) {
        List list = getSession().createSQLQuery("select count(*) from article where month(create_date) = " + date.getMonth() + "  and year(create_date) =" + date.getYear()).list();
        return list.size() == 0 ? 0 : Integer.parseInt(list.get(0).toString());
    }

    public Article getArticle(int id) {
        List<Article> list = getSession().createQuery("from Article where articleId=" + id).list();
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<Article> getArticleByCategory(int categoryId, int pageIndex, int pageSize) {
        return getSession().createQuery("select articles from Category where categoryId=" + categoryId).setFirstResult((pageIndex - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public int getCategoryArticleCount(int categoryId) {
        List<Category> list = getSession().createQuery("from Category where categoryId=" + categoryId).list();
        return list.size() > 0 ? list.get(1).getArticleCount() : 0;
    }
}
