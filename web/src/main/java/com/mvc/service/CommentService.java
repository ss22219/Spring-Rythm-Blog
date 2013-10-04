package com.mvc.service;

import com.mvc.domain.DomainType;
import com.mvc.exception.ServiceException;
import com.mvc.model.Article;
import com.mvc.model.Comment;
import com.mvc.model.User;
import com.mvc.repository.CommentRepository;
import com.mvc.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private SettingService settingService;

    @Autowired
    private ArticleService articleService;

    public Page<Comment> getArticleComment(int articleId, int pageIndex) {
        int commentPageSize = Integer.parseInt(settingService.getSetting("commentPageSize"));
        List<Comment> list = commentRepository.getArticleParentComment(articleId, pageIndex, commentPageSize);
        int totalCount = commentRepository.getArticleParentCommentCount(articleId);
        return new Page<Comment>(list, pageIndex, commentPageSize, totalCount, 5);
    }

    public List<Comment> getLastComment() {
        return commentRepository.getLastComment(Integer.parseInt(settingService.getSetting("lastCommentCount")));
    }

    public Comment getComment(int commentId) throws ServiceException {

        Comment comment = commentRepository.getComment(commentId);
        if (comment == null) {

            throw new ServiceException("not found comment!");
        }
        return comment;
    }

    public int getArticleCommentPage(int commentId) throws ServiceException {
        Comment comment = getComment(commentId);

        while (true) {
            if (comment.getParent() != null)
                comment = comment.getParent();
            else
                break;
        }
        int laterCount = commentRepository.getCommentLaterCount(commentId);
        int pageSize = Integer.parseInt(settingService.getSetting("CommentPageSize"));

        return (int) Math.ceil(laterCount / (double) pageSize);
    }

    public void addComment(Comment comment, HttpServletRequest request) throws ServiceException {
        User user = (User) request.getSession().getAttribute("user");

        if (!settingService.getSetting("closeComment").equals("0")) {

            if (user != null || settingService.getSetting("notLoginComment").equals("1")) {
                Article article = articleService.getArticle(comment.getArticle().getArticleId());

                if (article.getStatus() == DomainType.ArticleStatusOpen || (user != null && user.getRole() == DomainType.Admin)) {
                    Comment parent = null;

                    if (comment.getParent() != null) {

                        parent = getComment(comment.getParent().getCommentId());
                        if (!parent.isDeleted() && parent.getArticle().getArticleId() == comment.getArticle().getArticleId() && (parent.getStatus() == DomainType.CommentStatusOpen || (user != null && (user.getRole() == DomainType.Admin || parent.getUser() != null && parent.getUser().getUserId() == user.getUserId())))) {
                            comment.setParent(parent);

                        } else {
                            throw new ServiceException("对不起，你提交的数据存在异常！");
                        }
                    } else {
                        comment.setParent(null);
                    }

                    comment.setCreateDate(new Date());
                    comment.setStatus(Integer.parseInt(settingService.getSetting("defaultCommentStatus")));
                    comment.setArticle(article);
                    String ipAddress = request.getHeader("X-FORWARDED-FOR");
                    if (ipAddress == null) {
                        ipAddress = request.getRemoteAddr();
                    }
                    comment.setUserIp(ipAddress);
                    if (user != null) {
                        comment.setUser(user);
                        comment.setUserName(user.getUserName());
                        comment.setUserEmail(user.getEmail());
                    }
                    comment.setDeleted(false);

                    commentRepository.save(comment);
                } else {
                    throw new ServiceException("抱歉，你没有权限评论该文章。");
                }
            } else {
                throw new ServiceException("抱歉，你需要登录后才能评论。");
            }
        } else {
            throw new ServiceException("抱歉，评论功能已经关闭。");
        }
    }

}
