package onmen.springboot.controller;

import onmen.springboot.domain.PageBean;
import onmen.springboot.domain.User;
import onmen.springboot.service.UserService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /***
     *    登陆
     */
    @RequestMapping("/login")
    @ResponseBody
    public String login(String username, String password, HttpServletRequest request, HttpServletResponse response) throws JSONException {
        User user = userService.findByNameAndPw(username, password);
        return userService.login(user,request,response);
    }

    /***
     *     注册
     */
    @RequestMapping("/register")
    @ResponseBody
    public String register(String username, String password, String utelephone) {
        if (userService.findUserByName(username)) {
            return "true";
        }
        if (password == null)
            return "false";
        userService.addUser(username, password, utelephone);
        return "ok";
    }

    /***
     *  根据id获得头像
     */
    @RequestMapping("/findUserImgByUid")
    @ResponseBody
    public String getImgByUid(String uid){
        return "/img/"+userService.getImgByUid(uid);
    }

    /**
     * 请求登陆状态
     */
    @RequestMapping("/askLoginState")
    @ResponseBody
    public Map<String,String> askLoginState(HttpServletRequest request){
        return userService.askLoginState(request);
    }
    /***
     *     退出登陆
     */
    @RequestMapping("/logout")
    @ResponseBody
    public int logout(HttpServletRequest request){
       User user = (User)request.getSession().getAttribute("user");
       if(user!=null){
           request.getSession().removeAttribute("user");
           return 1;
       }
       return -1;
    }

    /***
     * 获得所有用户用于管理
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/getAllUser")
    @ResponseBody
    public ModelAndView getAllUser(Integer currentPage,Integer pageSize){
        ModelAndView modelAndView = new ModelAndView();
        PageBean pageBean = userService.getAllUserByPage(currentPage,pageSize);
        modelAndView.getModel().put("pageBean",pageBean);
        modelAndView.setViewName("/admin/user-manager");
        return modelAndView;
    }

    /***
     * 修改用户信息前存入数据
     */
    @RequestMapping("/editorUserInfo")
    @ResponseBody
    public ModelAndView setUserInfo(Integer uid,HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        if(uid==null){
            User user = (User)request.getSession().getAttribute("user");
            if(user==null){
                modelAndView.setViewName("/html/index");
                return modelAndView;
            }
            uid = user.getUid();
        }
        //将要修改的用户id存入session中
        request.getSession().setAttribute("uid",uid);
        User user = userService.getUserByUid(uid);
        modelAndView.getModel().put("user",user);
        modelAndView.setViewName("/userinfo/user-editor");
        return modelAndView;
    }

    /***
     * 修改用户信息
     * @param ugender
     * @param ustatement
     * @param img
     */
    @RequestMapping("/updateUser")
    @ResponseBody
    public ModelAndView updateUser(Integer ugender,String ustatement,String img,String utelephone,String password,HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        User user = ((User)request.getSession().getAttribute("user"));
        Integer uid = (Integer)request.getSession().getAttribute("uid");
        //判断是否为管理员修改
        if(uid==null){
            modelAndView.setViewName("/html/index");
            //判断是否为自己或者为管理员权限
            if(user==null)
                return modelAndView;
            uid = user.getUid();
            //判断是否为管理员
        }else if(user.getUadmin()!=1){
            return modelAndView;
        }
        userService.update(uid,ugender,ustatement,img,utelephone,password);
        modelAndView.setViewName("/html/index");
        return modelAndView;
    }
}
