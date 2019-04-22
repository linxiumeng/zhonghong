package org.springblade.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

/**
 * @author hanbin
 * 产品状态 上架/下架的状态
 *
 * 产品状态0为未上架,1为已上架,2为已下架
 *
 */
public enum GoodsStatusEnum implements IEnum<Integer> {
    NOT_ON(0,"未上架"),ON(1,"已上架"),DOWN(2,"已下架");

    private int code;

    private String desc;

    GoodsStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * mybatis-plus的转换方法
     * @return
     */
    @Override
    public Integer getValue() {
        return this.getCode();
    }

    /**
     * json构造的方法
     * @param code
     * @return
     */
    @JsonCreator
    public static GoodsStatusEnum getItem(Integer code){

        if(code != null){
            for(GoodsStatusEnum item : values()){
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

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
