package com.mvc.controller;

import com.mvc.exception.ServiceException;
import com.mvc.util.*;
import com.mvc.model.*;
import com.mvc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;


    @RequestMapping("/{articleId}/comment/{commentId}")
    public String commentShow(@PathVariable int articleId, @PathVariable int commentId, ModelMap map, HttpServletResponse response) throws IOException {
        Comment comment = null;
        try {
            int page = commentService.getArticleCommentPage(commentId);
            return show(articleId, page, map, response);
        } catch (ServiceException e) {
            response.sendError(404);
            return null;
        }
    }


    @RequestMapping("/{articleId}/{page}")
    public String show(@PathVariable int articleId, @PathVariable int page, ModelMap map, HttpServletResponse response) throws IOException {
        Article article = null;
        try {
            article = articleService.getArticle(articleId);
            articleService.addBrowseCount(articleId);
        } catch (ServiceException e) {
            response.sendError(404);
            return null;
        }

        page = page >= 1 ? page : 1;

        Page<Comment> comments = commentService.getArticleComment(articleId, page);
        map.put("comments", comments);
        map.put("article", article);

        return "article/show";
    }

    @RequestMapping("/{articleId}")
    public String show(@PathVariable int articleId, ModelMap map, HttpServletResponse response) throws IOException {
        return show(articleId, 1, map, response);
    }

    @RequestMapping(value = "/postComment", method = RequestMethod.POST)
    public void postComment(Comment comment, HttpServletResponse response, HttpServletRequest request) throws IOException {
        try {
            commentService.addComment(comment, request);
            response.sendRedirect("/article/" + comment.getArticle().getArticleId());
        } catch (ServiceException e) {
            response.sendRedirect("/error?message=" + URLEncoder.encode(e.getMessage(), "utf-8"));
        }
    }
}
