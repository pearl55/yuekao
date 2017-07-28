package com.bw.gaoyongpei.controller;

import com.bw.gaoyongpei.bean.User;
import com.bw.gaoyongpei.respstory.UserRops;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

/**
 * Created by gaoyongpei on 2017/7/28.
 */
@Controller
public class UserController {

    @Autowired
    private UserRops userRops;
    @Autowired
    private RedisTemplate redisTemplate;

    /*
    登入
    redis缓存
     */
    @RequestMapping("dengru")
    public String getlogin(String uname, String passwd, HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        System.out.println("-login----------------------");

            //确定计算方法
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            //加密后的字符串
            String newpas = base64en.encode(md5.digest(passwd.getBytes("utf-8")));

            User login = userRops.findByUnameAndPasswd(uname, newpas);
            if (login != null) {
                session.setAttribute("login", login);
                return "forward:showlist";
            }
        return "login";
    }
    /*
    查询
     */

    @RequestMapping("showlist")
    public String getlist(Map<String,Object> map){
        List<User> users = userRops.findAll();
        map.put("user",users);
        return "list";
    }
    /*
    注册
     */

    @RequestMapping(value = "add")
    public String add(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newstr=base64en.encode(md5.digest(user.getPasswd().getBytes("utf-8")));
        user.setPasswd(newstr);
        userRops.save(user);
        return "login";
    }
    /*
    修改
     */

    @RequestMapping(value = "xiugai")
    public String getupdate(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        System.out.println("-update---------");
        //确定计算方法
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newpas=base64en.encode(md5.digest(user.getPasswd().getBytes("utf-8")));
        user.setPasswd(newpas);
        userRops.save(user);
        return "forward:showlist";
    }
    @RequestMapping("/")
    public String getMain(){
        return "index";
    }
    @RequestMapping("register")
    public String getregister(){
        return "add";
    }
    @RequestMapping("getlogin2")
    public String getlogin2(){
        return "login";
    }


    /*
        删除
         */

    @RequestMapping( value = "delete")
    public String deleteUser(Integer id){
        userRops.delete(id);
        return "forward:showlist";
    }
    /*
        查询一条数据
     */

    @RequestMapping(value = "getId")
    public String getUserById(Integer id,Map<String,Object> map){
        User user = null;
        ValueOperations<String,User> operations = redisTemplate.opsForValue();
        Boolean exists = redisTemplate.hasKey("user");
        if(exists){
            user =  operations.get("user");
            map.put("user", user);
            return "update";
        }else {
            System.out.println("-查询一条---------");
            User user2 = userRops.findOne(id);
            operations.set("user",user2);
            map.put("user", user2);
            return "update";
        }
    }



}
