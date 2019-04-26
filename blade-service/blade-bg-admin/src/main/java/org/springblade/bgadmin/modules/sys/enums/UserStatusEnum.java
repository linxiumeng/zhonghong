package org.springblade.bgadmin.modules.sys.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

/**
 * @author hanbin
 * 用户登录状态枚举
 */
public enum UserStatusEnum implements IEnum {


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
