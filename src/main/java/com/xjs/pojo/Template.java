package com.xjs.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
@Accessors(chain = true)
@TableName("template")
public class Template{
    private String id;
    private String name;
    private String templateName; //'模板名称'
    @TableField(value = "phone_number")
    private String phoneNumber; //手机号
    private String lookedPeople;//查看人
    //private String content;//'模板内容'
    private int type;//状态
    //private String remark; //'备注'

    private String templateTitle;//模板标题
    private Date createTime;// 创建时间
    private boolean  isFreeze ;//冻结查看
    private String formData ;//模板内容
    //private IDFrontImg  idFrontImg;
    //private IDNationalImg   idNationalImg;

    //private  String frontUrl;//头像面
    //private  String naUrl;//国徽面

    //private IDFrontImg idObject;
    //private IDNationalImg idaObject ;

}
