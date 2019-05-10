package org.springblade.common.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springblade.common.enums.GoodsAuditStatusEnum;
import org.springblade.common.enums.GoodsStatusEnum;
import org.springblade.common.validation.group.InsertGroup;
import org.springblade.common.validation.group.SelectDetailGroup;
import org.springblade.common.validation.group.UpdateGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 商品表(Goods) 实体类
 *
 * @author linxiumeng
 * @since 2019-03-07 17:56:08
 */
@Data
@TableName("tb_goods")
public class Goods implements Serializable {

    /***/
    @TableId(type = IdType.INPUT)
    @NotNull(groups = {UpdateGroup.class, SelectDetailGroup.class})
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 商品公司
     */
    private String goodsCompany;
    /**
     * 产品类型
     */
    private Long goodsType;
    /**
     * 产品名字
     */
    @NotBlank(groups = {InsertGroup.class})
    private String goodsName;
    /**
     * 产品详情
     */
    private String goodsDesc;
    /**
     * 产品单价
     */
 //   @NotNull(groups = {InsertGroup.class})
    private Double goodsPrice;
    /**
     * 产品单位
     */
    @NotBlank(groups = {InsertGroup.class})
    private String goodsUnit;
    /**
     * 产品状态0为未上架,1为已上架,2为已下架 , 转换器设置为ordinal转换器
     */
    private GoodsStatusEnum goodsStatus;
    /**
     * 库存数量
     */
    @NotBlank(groups = {InsertGroup.class})
    private String goodsStock;
    /**
     * 图片地址
     */
  //  @NotBlank(groups = {InsertGroup.class})
    private String pic;
    /**
     * 审核状态0为默认状态,1为审核通过,2为审核失败
     */
    private GoodsAuditStatusEnum auditStatus;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createDate;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateDate;
    /**
     * 贸易条款
     */
    private String tradeClause;
    /**
     * 信用方式
     */
    private String paymentBy;
    /**
     * 计价基准
     */
    private String pricingManner;
    /**
     * 计价日期
     */
    private String contractualValueDate;
    /**
     * 发货港/提货点
     */
    private String portOfDispatch;
    /**
     * 装载日
     */
    private String loadingDate;
    /**
     * 支付日
     */
    private String payDay;
    /**
     * 其他说明
     */
    private String otherDescription;
    /**
     * 油产地
     */
    private String placeOfOrigin;
    /**
     * 油种类
     */
    private String oilType;
    /**
     * api度
     */
    private String api;
    /**
     * 含硫量%
     */
    private String sulphurContent;
    /**
     * 酸值mgkoh/g
     */
    private String acidValue;
    /**
     * 残炭%
     */
    private String carbonResidue;
    /**
     * 镍ppm
     */
    private String nickel;
    /**
     * 钒ppm
     */
    private String vanadium;
    /**
     * >350摄氏度质量收缩率（%）
     */
    private String shrink;
    /**
     * 原油指标文件
     */
    private String filePoint;
    /***/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date creatTime;
    /***/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 升贴水
     */
    private String premiumsDiscounts;

    @TableField(exist = false)
    public static final String ID_COLUMN = "id";

    @TableField(exist = false)
    public static final String USER_ID_COLUMN = "user_id";

    @TableField(exist = false)
    public static final String GOODS_STATUS_COLUMN = "goods_status";

    @TableField(exist = false)
    public static final String GOODS_AUDIT_STATUS_COLUMN = "audit_status";

    @TableField(exist = false)
    public static final String CREATE_TIME_COLUMN = "creat_time";


    @TableField(exist = false)
    private GoodsTypeEntity goodsTypeEntity;
}