package org.springblade.common.enums;

public enum PermissionCodeEnum {
    ZERE(6001,"企业未认证"),
    ONE(6002,"企业认证中"),
    TWO(6003,"认证失败"),
    THREE(6004,"企业认证成功"),
    FOUR(6005,"企业未授信"),
    FIVE(6006,"企业授信中"),
    SIX(6007,"企业授信失败"),
    SEVER(6000,"企业授信通过");


    private String desc;//中文描述
    private int status;//中文描述

    /**
     * 私有构造,防止被外部调用
     * @param desc
     */
    PermissionCodeEnum(int status,String desc){
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
    public int getStatus(){
        return status;
    }
}
