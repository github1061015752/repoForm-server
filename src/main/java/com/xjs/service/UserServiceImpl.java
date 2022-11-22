package com.xjs.service;

import com.xjs.mapper.UserMapper;
import com.xjs.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    /**
     * 业务思维能力: 用户登录操作
     * 登录业务逻辑:
     *      1.将用户的密码进行加密处理
     *      2.根据用户名和密码进行数据库查询
     *        查到了: 用户名和密码正确  返回token密钥
     *        没查到: 用户名和密码错误  返回null
     *
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        //1.将密码加密处理
//        String password = user.getPassword();
//        String md5Pass = DigestUtils.md5DigestAsHex(password.getBytes());
//        user.setPassword(md5Pass);
        //2.根据用户名和密码查询数据库
        User userDB = userMapper.findUserByUP(user);
        if(userDB == null){
            //用户名或者密码错误
            return null;
        }else {
            return userDB;
        }
      /*  //密钥:该密钥是用户登录的唯一的标识符,特点:独一无二
        String token = UUID.randomUUID().toString()
                        .replace("-", "");*/

    }



    @Override
    @Transactional
    public void addUser(User user) {
        Date date = new Date(); //获取当前时间
        String md5Pass =
                DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user
                .setId(1)
                .setUsername("wanghongfu")
                .setMobile("123456")
                .setLoginName("whf")
                .setPassword(md5Pass)
                .setStatus("Y")
                .setEmail("1983796868@qq.com")
                .setLoginName("whf")
                .setRoleId(11)
                .setStatus("Y")
                .setCreate("王洪福")
                .setCreateTime(date);
        userMapper.addUser(user);
    }

}
