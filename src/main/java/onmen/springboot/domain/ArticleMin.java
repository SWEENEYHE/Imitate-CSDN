package onmen.springboot.domain;

import java.util.Date;

/****
 * 封装主页显示信息
 */
public class ArticleMin {
    private Integer aid;
    private String title;
    private String summary;
    private Date time;
    private Integer uid;
    private String uname;
    private String img;
    private Integer likenum;
    private Integer commentNum;
    private Integer state;

    public ArticleMin() {
    }




    public ArticleMin(Integer aid, String title, String summary, Date time, Integer uid, String uname, String img, Integer likenum, Integer commentNum, Integer state) {
        this.aid = aid;
        this.title = title;
        this.summary = summary;
        this.time = time;
        this.uid = uid;
        this.uname = uname;
        this.img = img;
        this.likenum = likenum;
        this.commentNum = commentNum;
        this.state = state;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getLikenum() {
        return likenum;
    }

    public void setLikenum(Integer likenum) {
        this.likenum = likenum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    @Override
    public String toString() {
        return "ArticleMin{" +
                "aid=" + aid +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", time=" + time +
                ", uid=" + uid +
                ", uname='" + uname + '\'' +
                ", img='" + img + '\'' +
                ", likenum=" + likenum +
                ", commentNum=" + commentNum +
                ", state=" + state +
                '}';
    }
}
