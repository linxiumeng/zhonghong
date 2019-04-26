package org.springblade.bgadmin.modules.sys.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

/**
 * @author hanbin
 * 用户登录状态枚举
 */
public enum UserCreditStatusEnum implements IEnum {


    /**
     * 初始化
     */
    INITIAL(0),
    /**
     * 授信中
     */
    CREDITING(1),
    /**
     * 拒绝
     */
    REFUSE(2),
    /**
     * 接收
     */
    ACCEPT(3);

    @Override
    public Serializable getValue() {
        return code;
    }

    int code;

    UserCreditStatusEnum(int code){
        this.code = code;
    }

    @JsonCreator
    public static UserCreditStatusEnum getItem(Integer code){
        if(code != null) {
            for (UserCreditStatusEnum item : values()) {
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
