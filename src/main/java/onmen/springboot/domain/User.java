package onmen.springboot.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@DynamicUpdate
@DynamicInsert
@Data
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;
    private String uname;
    private Integer ugender;
    private String ustatement;
    private String upassword;
    private Integer uclass;
    private Integer uadmin;
    private String img;
    private String utelephone;

    public User() {
    }

    public User(String uname, String img) {
        this.uname = uname;
        this.img = img;
    }

    public User(Integer uid, String uname, Integer ugender, String ustatement, String upassword, Integer uclass, Integer uadmin, String img, String utelephone) {
        this.uid = uid;
        this.uname = uname;
        this.ugender = ugender;
        this.ustatement = ustatement;
        this.upassword = upassword;
        this.uclass = uclass;
        this.uadmin = uadmin;
        this.img = img;
        this.utelephone = utelephone;
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

    public Integer getUgender() {
        return ugender;
    }

    public void setUgender(Integer ugender) {
        this.ugender = ugender;
    }

    public String getUstatement() {
        return ustatement;
    }

    public void setUstatement(String ustatement) {
        this.ustatement = ustatement;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public Integer getUclass() {
        return uclass;
    }

    public void setUclass(Integer uclass) {
        this.uclass = uclass;
    }

    public Integer getUadmin() {
        return uadmin;
    }

    public void setUadmin(Integer uadmin) {
        this.uadmin = uadmin;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUtelephone() {
        return utelephone;
    }

    public void setUtelephone(String utelephone) {
        this.utelephone = utelephone;
    }

    public String toStringParam(){
        return this.uname+":"+this.ugender+":"+this.ustatement+":"+this.uclass+":"+this.img;
    }
    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", ugender=" + ugender +
                ", ustatement='" + ustatement + '\'' +
                ", upassword='" + upassword + '\'' +
                ", uclass=" + uclass +
                ", uadmin=" + uadmin +
                ", img='" + img + '\'' +
                ", utelephone='" + utelephone + '\'' +
                '}';
    }
}
