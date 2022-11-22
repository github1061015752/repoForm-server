package com.xjs.pojo;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;

//pojo基类，完成2个任务，2个日期，实现序列化
//自动填充规则: 公共属性!!!

/**
 * whf
 * 2022/11/10
 */
@Data
@Accessors(chain=true)
public class BasePojo implements Serializable{
	/**
	 * '创建人'
	 */
	private String create;
	/**
	 * '创建时间'
	 */
	private Date createTime;
	/**
	 * '修改人'
	 */
	private String modify;
	/**
	 * '修改时间'
	 */
	private Date modifyTime;

}
