package com.mvc.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-10-1
 * Time: 下午4:12
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Article {
    private int articleId;
    private String title;
    private String content;
    private Date createDate;
    private Date modifyDate;
    private int commentCount;
    private int browseCount;
    private int type;
    private int status;
    private User user;
    private List<Category> categorys;

    @Column(name = "article_id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    @Column(name = "title", nullable = false, insertable = true, updatable = true, length = 255, precision = 0)
    @Basic
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "content", nullable = false, insertable = true, updatable = true, length = 65535, precision = 0)
    @Basic
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "create_date", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    @Basic
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "modify_date", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    @Basic
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Column(name = "comment_count", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    @Column(name = "browse_count", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getBrowseCount() {
        return browseCount;
    }

    public void setBrowseCount(int browseCount) {
        this.browseCount = browseCount;
    }

    @Column(name = "type", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Column(name = "status", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (articleId != article.articleId) return false;
        if (browseCount != article.browseCount) return false;
        if (commentCount != article.commentCount) return false;
        if (status != article.status) return false;
        if (type != article.type) return false;
        if (content != null ? !content.equals(article.content) : article.content != null) return false;
        if (createDate != null ? !createDate.equals(article.createDate) : article.createDate != null) return false;
        if (modifyDate != null ? !modifyDate.equals(article.modifyDate) : article.modifyDate != null) return false;
        if (title != null ? !title.equals(article.title) : article.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = articleId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (modifyDate != null ? modifyDate.hashCode() : 0);
        result = 31 * result + commentCount;
        result = 31 * result + browseCount;
        result = 31 * result + type;
        result = 31 * result + status;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JoinTable(name = "category_relation", catalog = "blog", schema = "", joinColumns = @JoinColumn(name = "article_id", referencedColumnName = "article_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = false))
    @ManyToMany
    public List<Category> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<Category> categorys) {
        this.categorys = categorys;
    }
}
