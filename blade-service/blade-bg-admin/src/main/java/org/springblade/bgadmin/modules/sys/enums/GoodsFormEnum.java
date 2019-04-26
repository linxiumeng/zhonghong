package org.springblade.bgadmin.modules.sys.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

/**
 * @author hanbin
 * 订单状态枚举
 */
public enum GoodsFormEnum implements IEnum {


    UP(0,"上架"),
    DOWN(1,"下架"),
    AGREE(2,"审核通过"),
    DISAGREE(3,"拒绝"),
    AGREEING(4,"等待审核中"),
    NOT_YET_UP(5,"未上架"),
    DISAGREE_AND_AGREEING(6,"拒绝审核和等待审核中 和在一起");

    private String desc;//中文描述
    private int code;//编码

    /**
     * 私有构造,防止被外部调用
     * @param desc
     */
     GoodsFormEnum(int code, String desc){
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
    public static GoodsFormEnum getItem(Integer code){

        if(code != null){
            for(GoodsFormEnum item : values()){
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
