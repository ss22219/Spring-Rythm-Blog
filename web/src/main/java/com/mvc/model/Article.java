package com.mvc.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-10-5
 * Time: 上午7:56
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Article {
    private int articleId;

    @javax.persistence.Column(name = "article_id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    private String title;

    @javax.persistence.Column(name = "title", nullable = false, insertable = true, updatable = true, length = 255, precision = 0)
    @Basic
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String content;

    @javax.persistence.Column(name = "content", nullable = false, insertable = true, updatable = true, length = 65535, precision = 0)
    @Basic
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private Date createDate;

    @javax.persistence.Column(name = "create_date", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    private Date modifyDate;

    @javax.persistence.Column(name = "modify_date", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    private int commentCount;

    @javax.persistence.Column(name = "comment_count", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    private int browseCount;

    @javax.persistence.Column(name = "browse_count", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getBrowseCount() {
        return browseCount;
    }

    public void setBrowseCount(int browseCount) {
        this.browseCount = browseCount;
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

    private int status;

    @javax.persistence.Column(name = "status", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private boolean deleted;

    @javax.persistence.Column(name = "deleted", nullable = false, insertable = true, updatable = true, length = 0, precision = 0)
    @Basic
    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    private int userId;

    @javax.persistence.Column(name = "user_id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (articleId != article.articleId) return false;
        if (browseCount != article.browseCount) return false;
        if (commentCount != article.commentCount) return false;
        if (deleted != article.deleted) return false;
        if (status != article.status) return false;
        if (type != article.type) return false;
        if (userId != article.userId) return false;
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
        result = 31 * result + (deleted ? 1 : 0);
        result = 31 * result + userId;
        return result;
    }
}
