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
public class Comment {
    private int commentId;

    @javax.persistence.Column(name = "comment_id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
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

    private Integer parentId;

    @javax.persistence.Column(name = "parent_id", nullable = true, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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

    private int status;

    @javax.persistence.Column(name = "status", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    private String userName;

    @javax.persistence.Column(name = "user_name", nullable = false, insertable = true, updatable = true, length = 255, precision = 0)
    @Basic
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String userEmail;

    @javax.persistence.Column(name = "user_email", nullable = false, insertable = true, updatable = true, length = 255, precision = 0)
    @Basic
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    private int userIp;

    @javax.persistence.Column(name = "user_ip", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getUserIp() {
        return userIp;
    }

    public void setUserIp(int userIp) {
        this.userIp = userIp;
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

    private String content;

    @javax.persistence.Column(name = "content", nullable = false, insertable = true, updatable = true, length = 65535, precision = 0)
    @Basic
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (articleId != comment.articleId) return false;
        if (commentId != comment.commentId) return false;
        if (deleted != comment.deleted) return false;
        if (status != comment.status) return false;
        if (userId != comment.userId) return false;
        if (userIp != comment.userIp) return false;
        if (content != null ? !content.equals(comment.content) : comment.content != null) return false;
        if (createDate != null ? !createDate.equals(comment.createDate) : comment.createDate != null) return false;
        if (parentId != null ? !parentId.equals(comment.parentId) : comment.parentId != null) return false;
        if (userEmail != null ? !userEmail.equals(comment.userEmail) : comment.userEmail != null) return false;
        if (userName != null ? !userName.equals(comment.userName) : comment.userName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commentId;
        result = 31 * result + articleId;
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (deleted ? 1 : 0);
        result = 31 * result + status;
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userEmail != null ? userEmail.hashCode() : 0);
        result = 31 * result + userIp;
        result = 31 * result + userId;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
