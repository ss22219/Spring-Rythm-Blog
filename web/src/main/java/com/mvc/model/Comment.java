package com.mvc.model;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-10-4
 * Time: 上午7:26
 * To change this template use File | Settings | File Templates.
 */
public class Comment {
    private int commentId;
    private int articleId;
    private Integer parentId;
    private boolean deleted;
    private int status;
    private Date createDate;
    private String userName;
    private String userEmail;
    private String userIp;
    private int userId;
    private String content;
    private Article article;
    private User user;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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
        result = 31 * result + (userIp != null ? userEmail.hashCode() : 0);
        result = 31 * result + userId;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private Comment parent;

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }
}
