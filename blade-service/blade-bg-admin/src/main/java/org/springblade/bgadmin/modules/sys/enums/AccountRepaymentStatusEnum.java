package org.springblade.bgadmin.modules.sys.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

/**
 * @author hanbin
 * 用户登录状态枚举
 */
public enum AccountRepaymentStatusEnum implements IEnum {


    /**
     * 未付款
     */
    NOT_PAY(0),
    /**
     * 已付款
     */
    ALREADY_PAY(1),
    /**
     * 逾期
     */
    OVER_DEADLINE(2);


    @Override
    public Serializable getValue() {
        return code;
    }

    int code;

    AccountRepaymentStatusEnum(int code){
        this.code = code;
    }

    @JsonCreator
    public static AccountRepaymentStatusEnum getItem(Integer code){

        if(code != null){
            for(AccountRepaymentStatusEnum item : values()){
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
