package onmen.springboot.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="userinfo")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uinforid;
    private Integer uid;
    private Integer uarticlenum;
    private Integer ufannum;
    private Integer uupnum;
    private Integer ucommentnum;
    private Integer uviewnum;
    private Integer ucoin;

    public UserInfo() {
    }

    public UserInfo(Integer uid, Integer uarticlenum, Integer ufannum, Integer uupnum, Integer ucommentnum, Integer uviewnum, Integer ucoin) {
        this.uid = uid;
        this.uarticlenum = uarticlenum;
        this.ufannum = ufannum;
        this.uupnum = uupnum;
        this.ucommentnum = ucommentnum;
        this.uviewnum = uviewnum;
        this.ucoin = ucoin;
    }

    public Integer getUinforid() {
        return uinforid;
    }

    public void setUinforid(Integer uinforid) {
        this.uinforid = uinforid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getUarticlenum() {
        return uarticlenum;
    }

    public void setUarticlenum(Integer uarticlenum) {
        this.uarticlenum = uarticlenum;
    }

    public Integer getUfannum() {
        return ufannum;
    }

    public void setUfannum(Integer ufannum) {
        this.ufannum = ufannum;
    }

    public Integer getUupnum() {
        return uupnum;
    }

    public void setUupnum(Integer uupnum) {
        this.uupnum = uupnum;
    }

    public Integer getUcommentnum() {
        return ucommentnum;
    }

    public void setUcommentnum(Integer ucommentnum) {
        this.ucommentnum = ucommentnum;
    }

    public Integer getUviewnum() {
        return uviewnum;
    }

    public void setUviewnum(Integer uviewnum) {
        this.uviewnum = uviewnum;
    }

    public Integer getUcoin() {
        return ucoin;
    }

    public void setUcoin(Integer ucoin) {
        this.ucoin = ucoin;
    }
}
