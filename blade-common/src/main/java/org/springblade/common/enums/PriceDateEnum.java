package org.springblade.common.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author hanbin
 */
public enum PriceDateEnum implements IEnum<Integer> {
    DAY(0,"天"),WEEK(1,"周"),MONTH(2,"月"),YEAR(3,"年"),MINUTE(4,"分钟"),HOUR(5,"小时");

    private String desc;//中文描述
    private int status;//中文描述

    /**
     * 私有构造,防止被外部调用
     * @param desc
     */
     PriceDateEnum(int status, String desc){
        this.desc=desc;
        this.status=status;
    }

    /**
     * 定义方法,返回描述,跟常规类的定义没区别
     * @return
     */
    public String getDesc(){
        return desc;
    }

    @JsonValue
    public Integer getStatus(){
        return status;
    }

    @JsonCreator
    public static PriceDateEnum getItem(Integer code){

        if(code != null){
            for(PriceDateEnum item : values()){
                if(item.getStatus() == code.intValue()){
                    return item;
                }
            }
        }

        return null;
    }

    @Override
    public Integer getValue() {
        return getStatus();
    }
}
