package onmen.springboot.service;

import onmen.springboot.domain.PageBean;
import onmen.springboot.domain.User;
import onmen.springboot.domain.UserInfo;
import org.json.JSONException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface UserService {
    //查询所有用户
    List<User> findAll();
    //根据用户名和密码查询用户
    User findByNameAndPw(String username,String password);
    //插入新的用户
    void addUser(String username,String password,String utelephone);
    //判断是否存在用户名
    boolean findUserByName(String username);
    //登陆控制
    String login(User user, HttpServletRequest request, HttpServletResponse response) throws JSONException;

    String getImgByUid(String uid);

    User getUserByUid(Integer uid);

    UserInfo getUserInfoByUid(Integer uid);

    //请求登陆状态
    Map<String,String> askLoginState(HttpServletRequest request);

    //用户列表
    PageBean getAllUserByPage(Integer currentPage,Integer pageSize);
    //更新用户信息
    void update(Integer uid,Integer ugender,String ustatement,String img,String utelephone,String password);
}
