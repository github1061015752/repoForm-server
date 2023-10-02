package com.xjs.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
@Data
@Accessors(chain = true)
@TableName("veteran_user")
public class VeteranUser extends BasePojo implements Serializable{

    //@TableId(type = IdType.AUTO) //主键自增
    private String id;
    private String name; //用户名
    private String phone; //手机号
    private String loginName; //用户登录名
    private String sex;//性别
    private Integer age;//年龄
    private String nation;//族别
    private String cname; //中文平英缩写
    private String imgId;//头像ID
    private String unit;//所属单位
    private String ntivePlace;//籍贯/户口所在地
    private String arms;//兵种
    private Integer enlistmentAge;//入伍年龄

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date enlistmentTime;//入伍时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday; //出生日期
    private String dataProvider;//资料提供人
    private String remark;//备注
    private Integer status;//状态
    private String password;// 密码
    private Date startTime;

    /**
     * 查询反回值
     * */
    private String createTimes;//创建时间
    private String enlistmentTimes;//入伍时间
    private String birthdays; //出生日期

}
