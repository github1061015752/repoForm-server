package com.xjs.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * 身份证正面图片
 */
@Data
@Accessors(chain = true)
@TableName("idfrontimg")
public class IDFrontImg {
    private String id;
    private  String fieldName;//文件名
    private  String file;//文件
    private Date createTime;
    private  String templateId;
}
