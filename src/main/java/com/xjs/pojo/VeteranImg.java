package com.xjs.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
@Data
@Accessors(chain = true)
@TableName("veteran_img")
public class VeteranImg extends BasePojo{
    private  Integer id;
    private Integer userId; //用户名
    private String imgUrl; //手机号
    private String imgName; //照片名称
    private String imgDetail;//照片介绍
    private Date uploadTime; //上传时间
    private String remark;//备注
    private Integer status;//状态/是否启用  Y启用状态  N禁用状态


}
