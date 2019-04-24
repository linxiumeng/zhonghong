package org.springblade.common.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单表(Order) 实体类
 *
 * @author linxiumeng
 * @since 2019-02-14 10:51:48
 */
@Data
@TableName("tb_order")
public class Order {

    /**
     * 订单号
     */
    @TableId(type = IdType.INPUT)
    private Integer orderNo;
    /**
     * 商品id
     */
    private Integer goodsId;
    /**
     * 快照id
     */
    private String shotIds;
    /**
     * 商品名
     */
    private String goodsName;
    /**
     * 商品图片
     */
    private String goodsPic;
    /**
     * 供应公司名
     */
    private String componyName;
    /**
     * 采购公司名
     */
    private String purchaseCompony;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 采购数量
     */
    private Double orderSize;
    /**
     * 地址
     */
    private String address;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 联系人
     */
    private String contacts;
    /**
     * 备注
     */
    private String remark;
    /**
     * 订单金额
     */
    private BigDecimal orderPrice;
    /**
     * 订单状态0为初始化
     */
    private String status;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date creatTime;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateTime;

}