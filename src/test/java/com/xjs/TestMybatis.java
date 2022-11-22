package com.xjs;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xjs.mapper.UserMapper;
import com.xjs.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import java.util.List;

@SpringBootTest
public class TestMybatis {

    @Autowired
    private UserMapper userMapper;

    //核心: 以对象的方式操作数据库
    //原则: 使用对象中不为null的属性,进行操作  MP内部使用动态Sql
    //注意事项: MP只适用于单表操作!!!
    @Test
    public void saveUser(){
        User user = new User();
        user.setUsername("MP")
            .setPassword("123456")
                .setMobile("13333")
            .setEmail("11@qq.com")
            .setStatus("true");
        userMapper.insert(user);
    }

    /**
     * 1.根据ID查询数据库
     */
    @Test
    public void test01(){
        int id = 1;
        User user = userMapper.selectById(id);
        System.out.println(user);
    }

    /**
     * 2.查询username="admin" status=true的用户
     *  参数: QueryWrapper 条件构造器  拼接where条件
     *  原则: 根据不为null的属性进行操作
     *  写法2:
     *       通过条件构造器手动封装
     *       关键字:  = eq, > gt, < lt, >= ge, <= le, != ne
     */
    @Test
    public void test02(){
        //1.构建条件构造器
        User user = new User();
        user.setUsername("admin").setStatus("true");
        QueryWrapper queryWrapper = new QueryWrapper(user);
        List<User> list1 = userMapper.selectList(queryWrapper);

        //2.手动构建条件构造器 默认连接符使用 and
        QueryWrapper<User> queryWrapper2 = new QueryWrapper();
        queryWrapper2.eq("username","admin")
                     .eq("status", true);
        List<User> list2 = userMapper.selectList(queryWrapper2);
        System.out.println(list1);
        System.out.println(list2);
    }

    /**
     * 3.查询id>1的数据
     */
    @Test
    public void test03(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("id",1);
        List<User> userList = userMapper.selectList(queryWrapper);
        System.out.println(userList);
    }

    /**
     * 4.查询username含有"admin"的数据
     * 规则:  like      username like "%xxxx%"
     *       likeLeft  username like "%xxxx"
     *       likeRight username like "xxxx%"
     */
    @Test
    public void test04(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username", "admin");
        List<User> userList = userMapper.selectList(queryWrapper);
        System.out.println(userList);
    }

    /**
     * 5.查询id=1,2,3,4的数据 并且按照ID倒叙排列
     */
    @Test
    public void test05(){
        Integer[] ids = {1,2,3,4};
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id",ids)
                    .orderByDesc("id");
        List<User> userList = userMapper.selectList(queryWrapper);
        System.out.println(userList);
    }

    /**
     * 6.动态Sql: 根据电话和邮箱查询数据
     *   语法: condition: true  动态拼接数据
     *                   false 不拼接数据
     */
    @Test
    public void test06(){
        String phone = "";
        String email = "1235678@qq.com";
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.hasLength(phone), "phone",phone)
                    .eq(StringUtils.hasLength(email), "email",email);
        List<User> userList = userMapper.selectList(queryWrapper);
        System.out.println(userList);
    }

    /**
     * 7.查询主键字段
     * Sql: select id from user
     * 使用场景: 一般用在关联查询时
     */
    @Test
    public void test07(){
        List<Object> ids = userMapper.selectObjs(null);
        System.out.println(ids);
    }
}
