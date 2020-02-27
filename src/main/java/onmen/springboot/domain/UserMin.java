package onmen.springboot.domain;

import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name="user")
public class UserMin {
    private String uname;
    private String img;

    public UserMin() {
    }

    public UserMin(String uname, String img) {
        this.uname = uname;
        this.img = img;
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
}
