package com.mvc.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-10-5
 * Time: 上午7:56
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "category_relation", schema = "", catalog = "blog")
@Entity
public class CategoryRelation {
    private int categoryId;

    @javax.persistence.Column(name = "category_id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    private int articleId;

    @javax.persistence.Column(name = "article_id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    private int categoryRelation;

    @javax.persistence.Column(name = "category_relation", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getCategoryRelation() {
        return categoryRelation;
    }

    public void setCategoryRelation(int categoryRelation) {
        this.categoryRelation = categoryRelation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryRelation that = (CategoryRelation) o;

        if (articleId != that.articleId) return false;
        if (categoryId != that.categoryId) return false;
        if (categoryRelation != that.categoryRelation) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = categoryId;
        result = 31 * result + articleId;
        result = 31 * result + categoryRelation;
        return result;
    }
}
