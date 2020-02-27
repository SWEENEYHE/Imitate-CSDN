package onmen.springboot.controller;


import onmen.springboot.domain.User;
import onmen.springboot.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.List;

@Controller
public class UtilController {
    @Autowired
    private UtilService utilService;

    /***
     * 上传文件(图片)并返回路径
     * @param photo
     * @param request
     * @return
     */
    @RequestMapping("/uploadImg")
    @ResponseBody
    public String uploadImg(@RequestParam("photo")MultipartFile photo, HttpServletRequest request){
        //判断是否为空
        if(photo.isEmpty())
            return "";
        //获得原始文件名
        String filename = photo.getOriginalFilename();
        //得到后缀
        String suffix = filename.substring(filename.lastIndexOf("."));
        //生成新的文件名
        User user = (User)request.getSession().getAttribute("user");
        filename = user.getUid()+new Date().getTime()+suffix;
        String preffix = "/img/article/";
        System.out.println("reference: "+request.getHeader("Referer"));
        //如果上传的是头像则该变存储路径
        if(request.getHeader("Referer").contains("UserInfo")){
            preffix="/img/head_icon/";
        }
       String uploadPath = request.getRealPath(preffix)+filename;

        try {
            //存到文件
            photo.transferTo(new File(uploadPath));
        }catch (IOException e) {
            e.printStackTrace();
        }
        //返回路径
        return preffix+filename;
    }

    /***
     * 传递所有种类信息
     * @return
     */
    @RequestMapping("/getAllSection")
    @ResponseBody
    public List<Map<String,String>> getAllSection(){
        return utilService.getAllSection();
    }



}
