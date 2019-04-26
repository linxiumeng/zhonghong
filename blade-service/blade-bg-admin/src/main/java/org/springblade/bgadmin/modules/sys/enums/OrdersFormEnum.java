package org.springblade.bgadmin.modules.sys.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

/**
 * @author hanbin
 * 订单状态枚举
 */
public enum OrdersFormEnum implements IEnum {

    NOT_PUT_ORDER(0,"未下单"),
    ALREADY_PUT_ORDER(1,"已下单"),
    FAILD_PUT_ORDER(2,"订单失败");

    private String desc;//中文描述
    private int code;//中文描述

    /**
     * 私有构造,防止被外部调用
     * @param desc
     */
     OrdersFormEnum(int code, String desc){
        this.desc=desc;
        this.code=code;
    }

    /**
     * 定义方法,返回描述,跟常规类的定义没区别
     * @return
     */
    public String getDesc(){
        return desc;
    }

    @JsonCreator
    public static OrdersFormEnum getItem(Integer code){

        if(code != null){
            for(OrdersFormEnum item : values()){
                if(item.getCode() == code.intValue()){
                    return item;
                }
            }
        }

        return null;
    }

    @JsonValue
    public int getCode(){
        return code;
    }

    @Override
    public Serializable getValue() {
        return this.getCode();
    }
}
