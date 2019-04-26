package org.springblade.bgadmin.modules.sys.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

/**
 * @author hanbin
 * 用户登录状态枚举
 */
public enum UserLoginStatusEnum implements IEnum {


    /**
     * 冻结状态
     */
    FREEZE(0),
    /**
     * 正常
     */
    NORMAL(1);

    @Override
    public Serializable getValue() {
        return code;
    }

    int code;

    UserLoginStatusEnum(int code){
        this.code = code;
    }

    @JsonCreator
    public static UserLoginStatusEnum getItem(Integer code){
        if(code != null) {
            for (UserLoginStatusEnum item : values()) {
                if (item.getCode() == code) {
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
