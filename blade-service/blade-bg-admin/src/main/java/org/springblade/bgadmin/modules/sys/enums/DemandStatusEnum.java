package org.springblade.bgadmin.modules.sys.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

/**
 * @author hanbin
 * 用户登录状态枚举
 */
public enum DemandStatusEnum implements IEnum {


    //需求单状态0为初始化，1为招标中，2为暂停招标，3为停止招标

    /**
     * 初始化
     */
    INITIAL(0),
    /**
     * 招标中
     */
    TENDERING(1),
    /**
     * 暂停
     */
    PAUSE(2),
    /**
     * 停止
     */
    STOP(3);


    @Override
    public Serializable getValue() {
        return code;
    }

    int code;

    DemandStatusEnum(int code){
        this.code = code;
    }

    @JsonCreator
    public static DemandStatusEnum getItem(Integer code){

        if(code != null){
            for(DemandStatusEnum item : values()){
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
