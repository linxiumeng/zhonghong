package org.springblade.bgadmin.modules.sys.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

/**
 * @author hanbin
 * 订单状态枚举
 */
public enum RepaymentOrderEnum implements IEnum {

    APPLY_REPAYMENT(0,"申请融资"),
    DISAGREE_REPAYMENT(1,"融资未通过"),
    WAITING_REPAYMENT_GIVE_MONEY(2,"等待融资放款"),
    REPAYMENT_SUCCESS(3,"融资成功");

    private String desc;//中文描述
    private int code;//中文描述

    /**
     * 私有构造,防止被外部调用
     * @param desc
     */
     RepaymentOrderEnum(int code, String desc){
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
    public static RepaymentOrderEnum getItem(Integer code){

        if(code != null){
            for(RepaymentOrderEnum item : values()){
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
