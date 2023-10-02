package com.xjs.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("xjs_user")
public class User extends BasePojo{
//    @TableId(type = IdType.AUTO) //主键自增
    private Integer id;
    private String username; //用户名
    private String mobile; //手机号
    private String loginName; //用户登录名
    private String password;//密码
    private String email;//邮箱
    private String nickname;//昵称
    private Integer roleId;//角色id
    private String status;

}

