package com.mvc.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comment {
    private int commentId;
    private Integer parentId;
    private boolean deleted;
    private int status;
    private Date createDate;
    private String userName;
    private String userEmail;
    private String userIp;
    private User user;
    private Article article;
    private int articleId;

    @Column(name = "comment_id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    @Column(name = "parent_id", nullable = true, insertable = false, updatable = false, length = 10, precision = 0)
    @Basic
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Column(name = "deleted", nullable = false, insertable = true, updatable = true, length = 0, precision = 0)
    @Basic
    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Column(name = "status", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Column(name = "article_id", nullable = false, insertable = false, updatable = false, length = 10, precision = 0)
    @Basic
    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int status) {
        this.articleId = status;
    }

    @Column(name = "create_date", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "user_name", nullable = false, insertable = true, updatable = true, length = 255, precision = 0)
    @Basic
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "user_email", nullable = false, insertable = true, updatable = true, length = 255, precision = 0)
    @Basic
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Column(name = "user_ip", nullable = false, insertable = true, updatable = true, length = 20, precision = 0)
    @Basic
    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (commentId != comment.commentId) return false;
        if (deleted != comment.deleted) return false;
        if (status != comment.status) return false;
        if (userIp != comment.userIp) return false;
        if (createDate != null ? !createDate.equals(comment.createDate) : comment.createDate != null) return false;
        if (parentId != null ? !parentId.equals(comment.parentId) : comment.parentId != null) return false;
        if (userEmail != null ? !userEmail.equals(comment.userEmail) : comment.userEmail != null) return false;
        if (userName != null ? !userName.equals(comment.userName) : comment.userName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commentId;
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (deleted ? 1 : 0);
        result = 31 * result + status;
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userEmail != null ? userEmail.hashCode() : 0);
        result = 31 * result + (userIp != null ? userIp.hashCode() : 0);
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

    @ManyToOne
    @JoinColumn(name = "article_id", referencedColumnName = "article_id", nullable = false)
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    private String content;

    @Column(name = "content", nullable = false, insertable = true, updatable = true, precision = 0)
    @Basic
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private Comment parent;

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "comment_id", nullable = true,insertable = true,updatable = true)
    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }
}
