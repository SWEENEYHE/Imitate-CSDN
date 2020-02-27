package onmen.springboot.dao;

import onmen.springboot.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/****
 * 文章相关数据库查询
 */
@Transactional
public interface ArticleMapper extends JpaRepository<Article,Long> {
    //根据id查询文章
    Article getArticleByAidAndState(int articleId,int state);

    //多表查询获得articleMin
    @Query(nativeQuery=true,value="select a.aid,a.title,a.summary,a.time,a.uid,a.uname,img,a.likenum,a.commentnum,a.state from article a,user u where a.uid=u.uid and a.sectionId=?1 and a.state=1 limit ?2,?3")
    List<Object[]> getArticleMin(int category,int curPage,int pageSize);

    //根据文章id查找作者id
    @Query("select uid from Article where aid=?1")
    Integer findUserIdByArticleId(int aid);

    //获得赞数
    @Query("select likenum from Article where aid=?1 and state=1")
    Integer getLikeNum(int aid);

    //更新文章信息
    @Modifying
    @Query("update Article set likenum  = ?2 where aid = ?1")
    void updateArticleLikeNum(int aid,int likenum);

    //查询某钟类型文章的总数用于分页控制
    @Query("select count(a.aid) from Article a where sectionId=?1 and state=1")
    Integer getArticleCountBySectionId(int sectionId);

    //取得文章总条数
    @Query("select count(a.aid) from Article a")
    Integer getArticleCount();

    //查询所有文章
    @Query(nativeQuery = true,value = "select a.aid,a.title,a.summary,a.time,a.uid,a.uname,u.img,a.likenum,a.commentnum,a.state from article a,user u where a.uid=u.uid limit ?1,?2")
    List<Object[]> getArticleWithPage(Integer current,Integer size);

    //根据id删除文章
    void deleteArticleByAid(int aid);

    //更新state
    @Modifying
    @Query("update Article set state=?2 where aid=?1")
    void updateArticleState(int id,int state);

    //查询待修改文章信息
    @Query(nativeQuery = true ,value = "select a.title,a.acontent,a.sectionId from article a where a.aid=?1")
    List<Object[]> findEditorArticle(int aid);

    //保存文章
    @Modifying
    @Query("update Article set title=?2,acontent=?3,sectionId=?4,state=0 where aid=?1")
    void updateArticle(int aid,String title,String content,int sectionId);

    //多表查询
    @Query(nativeQuery = true,value="Select a.aid,a.title,a.summary,a.time,a.uid,a.uname,u.img,a.likenum,a.commentNum,a.state from article a,commend c,user u where c.uid = ?1 and u.uid = a.uid and c.aid=a.aid limit ?2,?3")
    List<Object[]> getRecommendArticleMin(int uid,int s1,int s2);
}
