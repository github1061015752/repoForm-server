package com.xjs.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor //vo 与web前端与后端交互的对象
public class SysResult implements Serializable {
    private Integer status; //200成功  201失败
    private String  msg;    //提示信息
    private Object  data;   //服务器返回值数据

    public static SysResult fail(){

        return new SysResult(201,"业务调用失败!!",null);
    }
    public static SysResult failMsg(String msg){

        return new SysResult(203,msg,null);
    }
    public static SysResult success(){

        return new SysResult(200,"业务调用成功!!",null);
    }


    public static SysResult success(Object data){

        return new SysResult(200,"业务调用成功!!",data);
    }

    public static SysResult success(String msg,Object data){

        return new SysResult(200,msg,data);
    }
    public static <T> T cast(Object o, Class<T> type) {
        if (type.isInstance(o)) return type.cast(o);
        throw new RuntimeException("can not cast " + o.getClass() + " to '" + type);
    }
}
