package org.springblade.common.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

/**
 * @author hanbin
 * 用户状态的枚举
 */
public enum UserStatusEnum implements IEnum {

    /**
     *
    UNCOMMIT_AND_UNCOMMIT("认证状态未提交，授信状态未提交"),

    *//**
     *
     *//*
    INVERIFICATION_AND_UNCOMMIT("认证中，未提交"),

    *//**
     *
     *//*
    FAILVERIFICATION_AND_UNCOMMIT("认证失败，未提交"),

    *//**
     *
     *//*
    COMPLETE_AMD_UNCOMMIT("认证完成，未提交"),

    *//**
     *
     *//*
    COMPLETE_AND_INVERIFICATION("认证完成，授信中"),

    *//**
     *
     *//*
    COMPLETE_AND_FAILVERIFICATION("认证完成，授信失败"),

    COMPLETE_AND_COMPLETE("认证完成，授信成功")




    ;


    String value;

    UserStatusEnum(String value){
    }*/


    /**
     * 初始化
     */
    INITIAL(0),
    /**
     * 认证中
     */
    VERIFYING(1),
    /**
     * 拒绝
     */
    REFUSE(2),
    /**
     * 接收
     */
    ACCEPT(3),

    FOUR(4),FIVE(5),SIX(6);

    @Override
    public Serializable getValue() {
        return code;
    }

    int code;

    UserStatusEnum(int code){
        this.code = code;
    }

    @JsonCreator
    public static UserStatusEnum getItem(Integer code){

        if(code != null){
            for(UserStatusEnum item : values()){
                if(item.getCode() == code.intValue()){
                    return item;
                }
            }
        }

        return null;
    }

    @JsonValue
    public int getCode() {
        return code;
    }

}
