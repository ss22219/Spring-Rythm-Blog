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
@Entity
public class Category {
    private int categoryId;

    @javax.persistence.Column(name = "category_id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    private String name;

    @javax.persistence.Column(name = "name", nullable = false, insertable = true, updatable = true, length = 255, precision = 0)
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int type;

    @javax.persistence.Column(name = "type", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private int articleCount;

    @javax.persistence.Column(name = "article_count", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(int articleCount) {
        this.articleCount = articleCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (articleCount != category.articleCount) return false;
        if (categoryId != category.categoryId) return false;
        if (type != category.type) return false;
        if (name != null ? !name.equals(category.name) : category.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = categoryId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + type;
        result = 31 * result + articleCount;
        return result;
    }
}
