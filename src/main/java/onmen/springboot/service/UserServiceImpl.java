package onmen.springboot.service;

import onmen.Utils;
import onmen.springboot.config.HttpSessionConfig;
import onmen.springboot.dao.UserInfoMapper;
import onmen.springboot.dao.UserMapper;
import onmen.springboot.domain.PageBean;
import onmen.springboot.domain.User;
import onmen.springboot.domain.UserInfo;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 用户业务处理类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    /***
     * 查询所有用户
     * @return
     */
    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    /***
     * 用户民+密码查询   用户登陆
     * @param username
     * @param password
     * @return
     */
    @Override
    public User findByNameAndPw(String username, String password) {
        return userMapper.findUserByUnameAndUpassword(username,password);
    }

    /***
     * 添加用户
     * @param username
     * @param password
     * @param utelephone
     */
    @Override
    public void addUser(String username, String password,String utelephone) {
       userMapper.save(new User(null,username,1,"",password,0,0,"0.jpg",utelephone));
    }

    /***
     * 根据用户名判断是否有同名用户
     * @param username
     * @return
     */
    @Override
    public boolean findUserByName(String username) {
        return userMapper.existsAllByUname(username);
    }

    /***
     * 登陆处理，防止一个账号多次登陆
     * @param user
     * @param request
     * @param response
     * @return
     * @throws JSONException
     */
    @Override
    public String login(User user, HttpServletRequest request, HttpServletResponse response) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        //用户名或密码错误
        if (user == null) {
            jsonObject.put("state", "fail");
            return jsonObject.toString();
        }
        //存在用户
        //获取所有session HttpSessionConfig
        HttpSessionConfig sessionConfig = new HttpSessionConfig();
        //判断是否存在该用户的session，是则修改成当前用户的信息，删除以前的信息
        List<HttpSession> activeSessions = sessionConfig.getActiveSessions();
        for (HttpSession activeSession : activeSessions) {
            //如果该session中存在username对应的user
            if(activeSession.getAttribute(user.getUname())!=null){
                //删除通过sessionId删除原来的session
                sessionConfig.removeSession(activeSession.getId());
                break;
            }
        }
        //getSession
        HttpSession session = request.getSession();
        //存user
        session.setAttribute("user",user);
        //存cookie
        Cookie cookie = new Cookie("user", user.toStringParam());
        cookie.setMaxAge(60*60*24*7);
        response.addCookie(cookie);
        //放回json
        jsonObject.put("state", "success");
        return jsonObject.toString();
    }

    /***
     * 根据uid得到用户头像
     * @param uid
     * @return
     */
    @Override
    public String getImgByUid(String uid) {
        return userMapper.findUserByUid(Integer.parseInt(uid)).getImg();
    }

    /***
     * 根据uid得到user
     * @param uid
     * @return
     */
    @Override
    public User getUserByUid(Integer uid) {
        return userMapper.findUserByUid(uid);
    }

    /***
     * 根据用户id查询用户数据信息userInfo
     * @param uid
     * @return
     */
    @Override
    public UserInfo getUserInfoByUid(Integer uid) {
        return userInfoMapper.findByUid(uid);
    }

    /***
     * 获得用户登陆状态：map<uname,><img,>
     * @param request
     * @return
     */
    @Override
    public Map<String, String> askLoginState(HttpServletRequest request) {
       User user = (User) request.getSession().getAttribute("user");
        HashMap<String, String> map = new HashMap<>();
        if(user==null){
           map.put("uname","");
           map.put("img","");
       }else{
            map.put("uname",user.getUname());
            map.put("img",user.getImg());
        }
        return map;
    }

    /***
     * 分页获得所有用户(管理员)
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageBean getAllUserByPage(Integer currentPage, Integer pageSize) {
        PageBean<User> pageBean = new PageBean<>();
        //1.每页数量
        int size = Utils.pageSizeControl(pageSize);
        //2.总条数
        int total = userMapper.getUserCount();
        //3.获得分页数量
        int pageCount = Utils.getPageTotal(total,size);
        //4.当前所在页
        int current = Utils.currentPageControl(currentPage,pageCount);
        //5.查询实体
        List<User> list = Utils.transObjectsToBean(userMapper.getAllUserByPage((current-1)*size,size),User.class);
        //填充pageBean
        pageBean.setPageCount(pageCount);
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalItem(total);
        pageBean.setPageItems(list);
        return pageBean;
    }

    /***
     * 更新用户信息
     * @param uid
     * @param ugender
     * @param ustatement
     * @param img
     */
    @Override
    public void update(Integer uid, Integer ugender, String ustatement, String img,String utelephone,String password) {
       //先查询出来
        User user = userMapper.findUserByUid(uid);
        //进行数据校验
       if(ugender!=null&&(ugender==0||ugender==1))
       user.setUgender(ugender);
       if(ustatement!=null&&ustatement.length()>0)
         user.setUstatement(ustatement);
       else
           user.setUstatement("这个人很懒还没有写任何东西...");
       if(img!=null&&img.length()>0)
       user.setImg(img);
       if(utelephone!=null&&utelephone.length()>0)
       user.setUtelephone(utelephone);
       if(password!=null&&password.length()>0)
       user.setUpassword(password);
       //重新更新
       userMapper.save(user);
    }
}
