package onmen.springboot.Interceptor;

import onmen.springboot.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class LoginInteceptor extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       String path = request.getServletPath();
        if (path.contains("user")
                ||path.contains("admin")
                ||path.contains("getAll")
                ||path.contains("editor")
                ||path.contains("upArticle")
                ||path.contains("deleteArticle")
                ||path.contains("saveArticle")
                ||path.contains("addComment")) {
            User user = (User)request.getSession().getAttribute("user");
            if(user==null){
                response.sendRedirect("/html/index.html");
                return false;
                //管理员权限判断
            }else if(path.contains("getAll")||path.contains("admin")){
                return user.getUadmin()>0;
            }
            return true;
        }
           return true;
    }
}
