package onmen.springboot.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/****
 * 评论实体
 */
@Data
@Entity
@Table(name="comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentid;
    private Integer articleid;
    private Integer uid;
    private Date  commenttime;
    private String commentcontent;

    public Comment() {
    }

    public Comment(Integer articleid, Integer uid, Date commenttime, String commentcontent) {
        this.articleid = articleid;
        this.uid = uid;
        this.commenttime = commenttime;
        this.commentcontent = commentcontent;
    }

    public Comment(Integer commentid, Integer articleid, Integer uid, Date commenttime, String commentcontent) {
        this.commentid = commentid;
        this.articleid = articleid;
        this.uid = uid;
        this.commenttime = commenttime;
        this.commentcontent = commentcontent;
    }

    public Integer getCommentid() {
        return commentid;
    }

    public void setCommentid(Integer commentid) {
        this.commentid = commentid;
    }

    public Integer getArticleid() {
        return articleid;
    }

    public void setArticleid(Integer articleid) {
        this.articleid = articleid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getCommenttime() {
        return commenttime;
    }

    public void setCommenttime(Date commenttime) {
        this.commenttime = commenttime;
    }

    public String getCommentcontent() {
        return commentcontent;
    }

    public void setCommentcontent(String commentcontent) {
        this.commentcontent = commentcontent;
    }
}
