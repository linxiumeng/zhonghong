package org.springblade.common.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OrdersEnum implements IEnum<Integer> {
    ZERO(0,"采购商下单等待供应商确认"),
    ONE(1,"供应商打回订单，订单结束，后续有意向需重新下单"),
    TWO(2,"浮动价订单、需求订单，等待供应商发送订单金额；需要报价单"),
    THREE(3,"供应商已经报价，等待采购商确认"),
    FOUR(4,"采购商打回供应商的订单报价，等待供应商重新报价"),
    FIVE(5,"已确认订单金额，等待付款"),
    SIX(6,"已支付首付款，等待融资审批"),
    SEVEN(7,"融资审批通过，等待放款"),
    EIGHT(8,"采购商融资失败，如果后续采购商融资没有转为成功，则订单结束"),
    NINE(9,"1.采购商融资未成功，退回首付款；2.买家放弃采购，申请退款"),
    TEN(10,"全款（首付+融资）到账，待供应商确认收款后转账"),
    ELEVEN(11,"供应商已确认收款"),
    TWELVE(12,"采购商融资审批通过，有分期待还"),
    THIRTEEN(13,"特殊情况下需要补交保证金，否则该订单不能进行其他操作（暂不考虑这个状态）"),
    FOURTEEN(14,"采购商全款已付清"),
    FIFTEEN(15,"星期六");

    private String desc;//中文描述
    private int status;//中文描述

    /**
     * 私有构造,防止被外部调用
     * @param desc
     */
     OrdersEnum(int status,String desc){
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
    public static OrdersEnum getItem(Integer code){

        if(code != null){
            for(OrdersEnum item : values()){
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
