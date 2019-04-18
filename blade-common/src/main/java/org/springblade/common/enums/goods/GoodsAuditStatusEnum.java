package org.springblade.common.enums.goods;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

/**
 * @author hanbin
 * 商品认证枚举
 * <p>
 * 审核状态0为默认状态,1为审核通过,2为审核失败
 */
public enum GoodsAuditStatusEnum implements IEnum {

    /**
     * 默认
     */
    DEFAULT(0, "默认"),

    /**
     * 通过
     */
    OK(1, "通过"),

    /**
     * 不通过
     */
    NO_OK(2, "不通过");

    private int code;

    private String desc;

    GoodsAuditStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @JsonCreator
    public GoodsAuditStatusEnum getItem(Integer code){

        if(code != null){
            for(GoodsAuditStatusEnum item : values()){
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

    @Override
    public Serializable getValue() {
        return this.getCode();
    }
}
