package org.springblade.common.form;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 商品表(Goods) 实体类
 *
 * @author linxiumeng
 * @since 2019-02-13 15:23:01
 */
@Data
public class GoodsForm extends PageForm {

    /***/
    private Long id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 产品类型
     */
    private String goodsType;
    /**
     * 产品名字
     */
    private String goodsName;
    /**
     * 产品详情
     */
    private String goodsDesc;
    /**
     * 产品单价
     */
    private Double goodsPrice;
    /**
     * 产品单位
     */
    private String goodsUnit;
    /**
     * 产品地区
     */
    private String goodsArea;
    /**
     * 产品状态
     */
    private Integer goodsStatus;
    /**
     * 库存数量
     */
    private String goodsStock;
    /**
     * 图片地址
     */
    private String pic;


    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date startDate;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date endDate;

    /**
     * 查询关键字
     */
    private String keywords;


}