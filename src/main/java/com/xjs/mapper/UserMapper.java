package com.xjs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjs.pojo.User;
import org.springframework.stereotype.Repository;


//泛型对象必须添加
@Repository
public interface UserMapper extends BaseMapper<User> {

    User findUserByUP(User user);
    void addUser(User user);






}
