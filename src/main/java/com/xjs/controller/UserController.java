package com.xjs.controller;


import com.xjs.pojo.User;
import com.xjs.service.UserService;
import com.xjs.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin    //前后端进行跨域操作
public class UserController {

    @Autowired
    private UserService userService;



    /**
     * 业务需求: 实现用户登录操作
     * URL地址: /user/login
     * 请求类型: post
     * 参数:    username/password  json串
     * 返回值:  SysResult对象(token密钥)
     */
    @PostMapping("/login")
    public SysResult login(@RequestBody User user){
        User token = userService.login(user);
        System.out.println("token"+token);
        //判断token是否为null
        if(token == null){
            return SysResult.fail(); //表示用户登录失败!!
        }
        return SysResult.success(token);//表示用户登录成功!!
    }



    /**
     * 业务说明: 用户新增
     * 请求路径 /user/addUser
     * 请求类型 POST
     * 请求参数: 整个form表单数据封装为js对象进行参数传递
     * 返回值结果: SysResult对象
     */
    @PostMapping("/addUser")
    public SysResult addUser(@RequestBody User user){

        userService.addUser(user);
        return SysResult.success(); //200


    }



}
