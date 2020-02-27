package onmen.springboot.controller;

import onmen.springboot.domain.ArticleMin;
import onmen.springboot.domain.PageBean;
import onmen.springboot.domain.User;
import onmen.springboot.service.ArticleService;
import onmen.springboot.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;
    /*****
     * 查询文章简介列表
     * @param category
     * @return
     */
    @RequestMapping("/findArticle")
    @ResponseBody
    public PageBean findAllArticle(Integer category, Integer currentPage, Integer pageSize){
        return  articleService.findArticleMinByCondition(category,currentPage,pageSize);
    }

    /****
     * 文章详细contoller
     * @param id
     * @return
     */
    @RequestMapping("/findArticleById")
    public ModelAndView getArticle(String id,HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        //设置跳转页面为jsp/watchBlog
        modelAndView.setViewName("jsp/watchBlog");
        //service查询文章详情
        articleService.getArticleDetailed(modelAndView,id,request);
        return modelAndView;
    }

    /***
     * 评论
     * @param articleId
     * @param comment
     * @param request
     */
    @RequestMapping("/addComment")
    public void addComment(String articleId, String comment, HttpServletRequest request){
       //session中获得当前用户
        User user = (User)(request.getSession().getAttribute("user"));
        //service中添加评论
        articleService.addComment(articleId,comment,user.getUid()+"");
    }

    /***
     * 点赞
     * @param articleId
     * @param request
     */
    @RequestMapping("/upArticle")
    public void addUp(String articleId,HttpServletRequest request){
        //session中获得当前用户
        User user= (User)request.getSession().getAttribute("user");
        //service处理
        articleService.addUpNum(articleId,user.getUid());
    }

    /***
     * 新增文章
     * @param title
     * @param content
     * @param sectionId
     * @param request
     * @return
     */
    @RequestMapping("/saveArticle")
    @ResponseBody
    public String saveArticle(Integer aid,String title,String content,Integer sectionId,HttpServletRequest request){
        //session中获得当前用户
        User user = (User)request.getSession().getAttribute("user");
        if(user==null)
            return "-1";
        //service中保存文章
       return articleService.saveArticle(aid,title,content,sectionId,user.getUname(),user.getUid());
    }

    /***
     * 取得所有文章(管理员)
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/getAllArticle")
    @ResponseBody
    public ModelAndView getAllArticle(Integer currentPage, Integer pageSize){
        ModelAndView modelAndView = new ModelAndView();
        PageBean pageBean = articleService.getAllArticle(currentPage,pageSize);
        modelAndView.getModel().put("pageBean",pageBean);
        modelAndView.setViewName("/admin/article-manager");
        return modelAndView;
    }

    /***
     * 删除文章
     * @param id
     * @return
     */
    @RequestMapping("/deleteArticleById")
    public ModelAndView deleteArticleById(Integer id){
        ModelAndView modelAndView = new ModelAndView();
        articleService.deleteArticleById(id);
        //设置跳转页面为重定向到getAllArticle
        modelAndView.setViewName("redirect:/getAllArticle");
        return modelAndView;
    }

    /****
     * 设置文章是否过审
     * @param id
     * @param state
     * @return
     */
    @RequestMapping("/setArticleById")
    public ModelAndView setArticleById(Integer id,Integer state){
        ModelAndView modelAndView = new ModelAndView();
        //重定向到getAllArticle
        modelAndView.setViewName("redirect:/getAllArticle");
        articleService.setArticleById(id,state);
        return modelAndView;
    }

    /***
     * 还原文章内容用于修改界面
     * @param aid
     * @return
     */
    @RequestMapping("/findEditorArticle")
    @ResponseBody
    public Map<String,String> findEditorArticle(Integer aid){
        return articleService.findEditorArticle(aid);
    }

    /***
     * 加载所有评论
     */
    @RequestMapping("/getAllComment")
    @ResponseBody
    public ModelAndView getAllComments(Integer currentPage, Integer pageSize){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/comment-manager");
        PageBean pageBean = commentService.getAllComment(currentPage,pageSize);
        modelAndView.getModel().put("pageBean",pageBean);
        return modelAndView;
    }

    /***
     * 删除评论
     * @param commentid
     * @return
     */
    @RequestMapping("/deleteComment")
    @ResponseBody
    public ModelAndView deleteComment(Integer commentid){
        ModelAndView modelAndView = new ModelAndView();
        commentService.deleteComment(commentid);
        modelAndView.setViewName("redirect:/getAllComment");
        return modelAndView;
    }

    /***
     * 获得推荐列表文章
     * @param request
     * @return
     */
    @RequestMapping("/getRecommendArticle")
    @ResponseBody
    public List<ArticleMin> getRecommendArticle(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        //未登陆，则随机生成推荐列表
        if(user==null){
            return articleService.getRandomArticleMin(11);
        }else{
            return articleService.getRecommendArticleMin(user.getUid(),11);
        }
    }

    /***
     * 保存阅读的内容
     * @param title
     * @param content
     * @return
     */
    @RequestMapping("/insertArticle")
    @ResponseBody
    public String grabArticle(String title,String content){
        articleService.saveArticle(null,title,content,1,"123",1);
        return "success";
    }
}
