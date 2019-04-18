package org.springblade.common.enums;

public enum ErrorEnum {





    用户未认证(701,"用户未认证"),
    用户未登录(700,"用户没有登陆"),
    用户密码错误(703,"用户密码错误"),
    用户不存在(702,"用户不存在");






    private String desc;//中文描述
    private int code;//中文描述

    /**
     * 私有构造,防止被外部调用
     * @param desc
     */
    ErrorEnum(int code,String desc){
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
    public int getCode(){
        return code;
    }
}
