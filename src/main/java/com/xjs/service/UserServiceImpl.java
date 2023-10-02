package com.xjs.service;

import com.xjs.mapper.UserMapper;
import com.xjs.pojo.VeteranImg;
import com.xjs.pojo.VeteranUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.logging.SimpleFormatter;


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
     * @param loginName password
     * @return
     */
    @Override
    public VeteranUser login(String loginName ,String password) {
        //1.将密码加密处理
//        String password = user.getPassword();
//        String md5Pass = DigestUtils.md5DigestAsHex(password.getBytes());
//        user.setPassword(md5Pass);
        //2.根据用户名和密码查询数据库
        VeteranUser userDB = userMapper.login(loginName,password);
            return userDB;
      /*  //密钥:该密钥是用户登录的唯一的标识符,特点:独一无二
        String token = UUID.randomUUID().toString()
                        .replace("-", "");*/

    }

    @Override
    public VeteranUser findVeteranUserById(String id) {
        return userMapper.findVeteranUserById(id);
    }

    @Override
    public int addVeteranUser(VeteranUser veteranUser) {
        veteranUser.setStatus(1);
        veteranUser.setCreateTime(new Date());
        veteranUser.setId(UUID.randomUUID().toString());
        return userMapper.addVeteranUser(veteranUser);
    }

    @Override
    public List<VeteranUser> selectAllVeteranUser(VeteranUser veteranUser) {
        List<VeteranUser> ListUser=userMapper.selectAllVeteranUser(veteranUser);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        for (VeteranUser list :ListUser) {
            if(!list.getBirthday().toString().isEmpty()){
                list.setBirthdays(simpleDateFormat.format(list.getBirthday()).toString());
            }
            if(!list.getCreateTime().toString().isEmpty()){
                list.setCreateTimes(simpleDateFormat.format(list.getCreateTime()).toString());
            }
            if(!list.getEnlistmentTime().toString().isEmpty()){
                list.setEnlistmentTimes(simpleDateFormat.format(list.getEnlistmentTime()).toString());
            }
        }
        return ListUser;
    }

    @Override
    public int updateVeteranUser(VeteranUser veteranUser) {
        return userMapper.updateVeteranUser(veteranUser);
    }

    @Override
    public int deleteUserById(String id) {
        return userMapper.deleteUserById(id);
    }

    /**
     * 照片管理
     * @param
     * @return
     */
    @Override
    public VeteranImg findVeteranImgById(Integer id) {
        return userMapper.findVeteranImgById(id);
    }

    @Override
    public int addVeteranImg(VeteranImg veteranImg) {
        return userMapper.addVeteranImg(veteranImg);
    }

    @Override
    public List<VeteranImg> selectAllVeteranImg(VeteranImg veteranImg) {
        return userMapper.selectAllVeteranImg(veteranImg);
    }

    @Override
    public int updateVeteranImg(VeteranImg veteranImg) {
        return userMapper.updateVeteranImg(veteranImg);
    }

    @Override
    public int deleteVeteranImgById(Integer id) {
        return userMapper.deleteVeteranImgById(id);
    }

    @Override
    public int deleteVeteranImgByUserId(Integer userId) {
        return userMapper.deleteVeteranImgByUserId(userId);
    }


}
