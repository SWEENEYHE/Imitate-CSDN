package onmen.springboot.service;



/***
 * 评论业务处理类
 */
import onmen.Utils;
import onmen.springboot.dao.CommentMapper;
import onmen.springboot.domain.Comment;
import onmen.springboot.domain.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    /***
     * 加载某篇文章的评论列表
     * @param articleId
     * @return
     */
    @Override
    public List<Comment> getCommentByArticleId(String articleId) {
        int articleIdI = Integer.parseInt(articleId);
        return commentMapper.getCommentByArticleid(articleIdI);
    }

    /***
     * 加载所有的评论
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageBean getAllComment(Integer currentPage, Integer pageSize) {
        PageBean<Comment> pageBean = new PageBean<>();
        //1.每页数量
        int size = Utils.pageSizeControl(pageSize);
        //2.总条数
        int total = commentMapper.getCommentCount();
        //3.获得分页数量
        int pageCount = Utils.getPageTotal(total,size);
        //4.当前所在页
        int current = Utils.currentPageControl(currentPage,pageCount);
        //5.加载所有文章的信息
        List<Object[]> objects = commentMapper.getCommentWithPage((current-1)*size,size);
        List<Comment> lists = Utils.transObjectsToBean(objects,Comment.class);

        //填充pageBean
        pageBean.setTotalItem(total);
        pageBean.setPageSize(size);
        pageBean.setCurrentPage(current);
        pageBean.setPageItems(lists);
        pageBean.setPageCount(pageCount);
        return pageBean;
    }

    /***
     * 删除评论
     * @param commentId
     */
    @Override
    public void deleteComment(Integer commentId) {
        commentMapper.deleteCommentByCommentid(commentId);
    }
}

