package com.mvc.repository;

import com.mvc.model.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepository extends BaseRepository<Comment> {
    public List<Comment> getLastComment(int count) {
        return getSession().createQuery("from Comment where deleted=false and status=1 order by createDate desc ")
                .setMaxResults(count).list();
    }

    public List<Comment> getArticleParentComment(int articleId, int pageIndex, int pageSize) {
        return getSession().createQuery("from Comment where deleted=false and status=1 and articleId=" + articleId).setFirstResult((pageIndex - 1) * pageSize).setMaxResults(pageSize).list();
    }

    public int getArticleParentCommentCount(int articleId) {
        return getSession().createQuery("from Comment where deleted=false and status=1 and articleId=" + articleId).list().size();
    }

    public Comment getComment(int id) {
        return (Comment) getSession().get(Comment.class, id);
    }
}