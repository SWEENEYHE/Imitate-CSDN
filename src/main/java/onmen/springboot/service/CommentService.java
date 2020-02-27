package onmen.springboot.service;

import onmen.springboot.domain.Comment;
import onmen.springboot.domain.PageBean;

import java.util.List;


public interface CommentService{
    //根据文章id查询评论
     public List<Comment> getCommentByArticleId(String articleId);

     PageBean getAllComment(Integer currentPage, Integer pageSize);

     void deleteComment(Integer commentId);
}
