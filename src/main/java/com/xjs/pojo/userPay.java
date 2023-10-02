package com.xjs.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("personal_info")
public class userPay {

    private String id;
    private String name; //用户名
    private String phone; //手机号
    private String imgId;//图片id
    private String imgUrl;//图片路劲
    private String idNumber; //用户登录名
    private String identity;//身份证号
    private String remark;//备注
    private String password;//密码

}
