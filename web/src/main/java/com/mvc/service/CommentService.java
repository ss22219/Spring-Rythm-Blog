package com.mvc.service;

import com.mvc.model.Comment;
import com.mvc.repository.CommentRepository;
import com.mvc.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-10-3
 * Time: 下午7:00
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private SettingService settingService;

    public Page<Comment> getArticleComment(int articleId, int pageIndex) {
        int commentPageSize = Integer.parseInt(settingService.getSetting("commentPageSize"));
        List<Comment> list = commentRepository.getArticleParentComment(articleId, pageIndex, commentPageSize);
        int totalCount = commentRepository.getArticleParentCommentCount(articleId);
        return new Page<Comment>(list, pageIndex, commentPageSize, totalCount, 5);
    }

    public List<Comment> getLastComment() {
        return commentRepository.getLastComment(Integer.parseInt(settingService.getSetting("lastCommentCount")));
    }


}
