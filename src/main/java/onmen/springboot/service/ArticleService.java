package onmen.springboot.service;

import onmen.springboot.domain.ArticleMin;
import onmen.springboot.domain.PageBean;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface ArticleService {
    //查询所有
    PageBean findArticleMinByCondition(Integer category,Integer currentPage,Integer pageSize);
    //查询文章详情
    ModelAndView getArticleDetailed(ModelAndView modelAndView, String articleId, HttpServletRequest request);
    //添加评论
    void addComment(String articleId,String comment,String uid);
    //点赞
    void addUpNum(String articleId,int uid);
    //保存文章
    String saveArticle(Integer aid,String title,String content,Integer sectionId,String uanme,int uid);
    //获得所有文章
    PageBean getAllArticle(Integer currentPage,Integer pageSize);
    //删除文章
    void deleteArticleById(Integer aid);
    //根据id设置文章是否过审
    void setArticleById(Integer id,Integer state);
    //根据id获得待编辑的文章内容
    Map<String, String> findEditorArticle(Integer aid);
    //获得随机文章
     List<ArticleMin> getRandomArticleMin(Integer num);
    //获得推荐文章
    List<ArticleMin> getRecommendArticleMin(Integer uid,Integer num);
}
