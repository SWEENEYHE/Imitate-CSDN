package onmen.springboot.service;

import onmen.Utils;
import onmen.springboot.dao.ArticleMapper;
import onmen.springboot.dao.CommentMapper;
import onmen.springboot.dao.UserInfoMapper;
import onmen.springboot.dao.UserMapper;
import onmen.springboot.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/***
 * 文章业务处理类
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    /***
     * 查询所有文章简略信息用于首页列表展示
     * @param category
     * @return
     */
    @Override
    public PageBean findArticleMinByCondition(Integer category,Integer currentPage,Integer pageSize) {
        if(category==null)
            category = 1;
        PageBean<ArticleMin> pageBean = new PageBean<>();
        //1.每页数量size
        int size = Utils.pageSizeControl(pageSize);
        //2.总条数total
        int total = articleMapper.getArticleCountBySectionId(category);
        //3.获得分页数量pageCount
        int pageCount = Utils.getPageTotal(total,size);
        //4.当前所在页current
        int current = Utils.currentPageControl(currentPage,pageCount);
        //查询数据库
        List<Object[]> objects = articleMapper.getArticleMin(category,current-1,size);
        //将查询出的object[]类型转换成articleMin类型
        List<ArticleMin> lists = Utils.transObjectsToBean(objects,ArticleMin.class);
        //1.封装内容
        pageBean.setPageItems(lists);
        //2.设置当前页
        pageBean.setCurrentPage(current);
        //3.设置页的大小
        pageBean.setPageSize(size);
        //4.设置总页数
        pageBean.setPageCount(pageCount);
        //5.设置总条数
        pageBean.setTotalItem(total);
        return pageBean;
    }

    /***
     *     获得文章详情页所有信息
     */
    @Override
    public ModelAndView getArticleDetailed(ModelAndView modelAndView, String articleId, HttpServletRequest request) {
        int articleIdI = Integer.parseInt(articleId);
        //获得文章
        Article article = articleMapper.getArticleByAidAndState(articleIdI,1);
        //如果文章被还没有审核通过则判断是否为作者
        if(article==null){
            article = articleMapper.getArticleByAidAndState(articleIdI,0);
            User user = (User)request.getSession().getAttribute("user");
            if(user==null||user.getUid()!=article.getUid())
                return modelAndView;
        }
        //存储到model中
        modelAndView.getModel().put("article", article);
        //查询文章评论
        List<Comment> comments = commentMapper.getCommentByArticleid(article.getAid());
        //存储文章评论
        modelAndView.getModel().put("comments", comments);
        //查询评论对应的用户头像和用户名
        List<UserMin> userMins = new ArrayList<>();
        for (Comment comment : comments) {
            userMins.add(userMapper.findUserNameImgs(comment.getUid()));
        }
        modelAndView.getModel().put("userMin", userMins);

        //作者
        User user = userMapper.findUserByUid((article.getUid()));
        modelAndView.getModel().put("user", user);
        //作者详情
        UserInfo userInfo = userInfoMapper.findByUid(article.getUid());
        modelAndView.getModel().put("userInfo", userInfo);
        return modelAndView;
    }

    /***
     * 添加评论并更新数据
     * @param articleId
     * @param comment
     * @param uid
     */
    @Override
    public void addComment(String articleId, String comment, String uid) {
        int articleIdI = Integer.parseInt(articleId);
        int uidI = Integer.parseInt(uid);
        //保存文章
        commentMapper.saveAndFlush(new Comment(null,articleIdI, uidI, null, comment));
        //查询被评论人
        int beCommentUid = articleMapper.findUserIdByArticleId(articleIdI);
        int upNum = userInfoMapper.getUserCommentNum(beCommentUid);
        //更新被评论人信息
        userInfoMapper.updateUserCommentNum(upNum + 1, beCommentUid);
    }

    /***
     * 点赞处理
     * @param articleId
     * @param uid
     */
    @Override
    public void addUpNum(String articleId, int uid) {
        int articleIdI = Integer.parseInt(articleId);
        //获得likenum 然后更新加1更新 article
        int likeNum = articleMapper.getLikeNum(articleIdI);
        articleMapper.updateArticleLikeNum(articleIdI, likeNum + 1);
        //获得总点赞数 并更新 userinfo
        int totalUpNum = userInfoMapper.getUserUpNum(uid);
        userInfoMapper.updateUpNum(uid, totalUpNum + 1);
    }

    /***
     * 保存文章,state设置为0，需等管理员审核通过后放行
     */
    @Override
    public String saveArticle(Integer aid,String title, String content, Integer sectionId, String uname, int uid) {
        Article article;
        //如果aid为空则新增
        if(aid==null){
             article = articleMapper.saveAndFlush(new Article(title, content, Utils.getSummary(content), sectionId, null, 0, 0, uid, uname, 0));
             aid = article.getAid();
        }else{//否则更新
           articleMapper.updateArticle(aid,title,content,sectionId);
        }
      return aid + "";
    }

    /***
     * 获得所有的文章(用于管理员界面)
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageBean getAllArticle(Integer currentPage, Integer pageSize) {
        PageBean<ArticleMin> pageBean = new PageBean<>();
        //1.每页数量
        int size = Utils.pageSizeControl(pageSize);
        //2.总条数
        int total = articleMapper.getArticleCount();
        //3.获得分页数量
        int pageCount = Utils.getPageTotal(total,size);
        //4.当前所在页
        int current = Utils.currentPageControl(currentPage,pageCount);
        //5.加载所有文章的信息
        List<Object[]> objects = articleMapper.getArticleWithPage((current-1)*size,size);
        List<ArticleMin> lists = Utils.transObjectsToBean(objects,ArticleMin.class);
        //填充pageBean对象
        pageBean.setTotalItem(total);
        pageBean.setPageSize(size);
        pageBean.setCurrentPage(current);
        pageBean.setPageItems(lists);
        pageBean.setPageCount(pageCount);
        return pageBean;
    }

    /***
     * 删除文章
     * @param aid
     */
    @Override
    public void deleteArticleById(Integer aid) {
        articleMapper.deleteArticleByAid(aid);
    }

    /***
     * 设置文章是否过审
     * @param id
     * @param state
     */
    public void setArticleById(Integer id,Integer state){
        articleMapper.updateArticleState(id,state);
    }


    /***
     * 根据文章id获取文章部分信息用于修改文章内容
     */
    @Override
    public Map<String, String> findEditorArticle(Integer aid) {
        String[] keys = {"title","content","sectionId"};
        return Utils.transObjectsToMap(keys,articleMapper.findEditorArticle(aid).get(0));
    }

    /****
     * 随机加载几篇文章用于推荐的冷启动
     * @param num 加载的数量
     */
    public List<ArticleMin> getRandomArticleMin(Integer num){
        //最终文章list
        List<ArticleMin> lists = new ArrayList<>();
        //取得不重复的随机类别
        Integer [] random = Utils.getRandom(1,20,num+1);
        for(int i = 0 ; i<num ; i++){
            List<Object[]> objects = articleMapper.getArticleMin(random[i],0,1);
            //如果该类别没有文章则跳过
            if(objects==null||objects.size()==0)
                continue;
            List<ArticleMin> articleMins = Utils.transObjectsToBean(objects,ArticleMin.class);
            //将该类别的首篇文章取出加入到推荐中
            lists.add(articleMins.get(0));
        }
        return lists;
    }

    /***
     * 查询推荐表、文章表、用户表取得推荐信息
     * @param uid
     * @param num
     * @return
     */
    @Override
    public List<ArticleMin> getRecommendArticleMin(Integer uid,Integer num) {
        List<ArticleMin> articleMins = Utils.transObjectsToBean(articleMapper.getRecommendArticleMin(uid,0,num),ArticleMin.class);
        //多表查询
        //如果该用户还没有推荐内容则随机生成
        if(articleMins==null||articleMins.size()<=0)
            articleMins = getRandomArticleMin(num);
        return articleMins;
    }
}
