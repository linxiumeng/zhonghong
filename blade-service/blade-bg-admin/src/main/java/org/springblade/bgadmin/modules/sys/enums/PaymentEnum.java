package org.springblade.bgadmin.modules.sys.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

/**
 * @author hanbin
 * 订单状态枚举
 */
public enum PaymentEnum implements IEnum {

    NOT_PAY(0,"未付款"),
    ALREADY_PAY(1,"已付款");

    private String desc;//中文描述
    private int code;//中文描述

    /**
     * 私有构造,防止被外部调用
     * @param desc
     */
     PaymentEnum(int code, String desc){
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
    public static PaymentEnum getItem(Integer code){

        if(code != null){
            for(PaymentEnum item : values()){
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
