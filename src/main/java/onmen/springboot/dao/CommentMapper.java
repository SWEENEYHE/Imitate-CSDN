package onmen.springboot.dao;

import onmen.springboot.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/***
 * 评论dao
 */
@Transactional
@Component
public interface CommentMapper extends JpaRepository<Comment,Long> {
     List<Comment> getCommentByArticleid(int articleid);

     Comment saveAndFlush(Comment comment);

     @Query("Select count(commentid) from Comment")
     Integer getCommentCount();

     @Query(nativeQuery = true,value="select * from Comment limit ?1,?2")
     List<Object[]> getCommentWithPage(Integer currentPage,Integer pageSize);

     void deleteCommentByCommentid(Integer commentId);
}
