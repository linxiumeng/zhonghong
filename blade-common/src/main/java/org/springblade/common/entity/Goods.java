package org.springblade.common.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("商品实体类")
public class Goods implements Serializable {

    /**
     * 自增id
     */
    @TableId(type = IdType.INPUT)
    @NotNull(groups = {UpdateGroup.class, SelectDetailGroup.class})
    @ApiModelProperty(value = "自增id",name="id",example = "1")
    private Long id;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id",name="userId",example = "1")
    private Long userId;
    /**
     * 商品公司
     */
    @ApiModelProperty(value = "商品公司",name="goodsCompany",example = "闲憩小馆")
    private String goodsCompany;
    /**
     * 产品类型
     */
    @ApiModelProperty(value = "产品类型",name="goodsType",example = "1")
    private Long goodsType;
    /**
     * 产品名字
     */
    @ApiModelProperty(value = "产品名字",name="goodsName",example = "汽油")
    @NotBlank(groups = {InsertGroup.class})
    private String goodsName;
    /**
     * 产品详情
     */
    @ApiModelProperty(value = "产品详情",name="goodsDesc",example = "就是可以用的那种汽油")
    private String goodsDesc;
    /**
     * 产品单价
     */
    @ApiModelProperty(value = "产品单价",name="goodsPrice",example = "123.21")
 //   @NotNull(groups = {InsertGroup.class})
    private Double goodsPrice;
    /**
     * 产品单位
     */
    @ApiModelProperty(value = "产品单位",name="goodsUnit",example = "吨")
    @NotBlank(groups = {InsertGroup.class})
    private String goodsUnit;
    /**
     * 产品状态0为未上架,1为已上架,2为已下架 , 转换器设置为ordinal转换器
     */
    @ApiModelProperty(value = "产品状态0为未上架,1为已上架,2为已下架 , 转换器设置为ordinal转换器",name="goodsStatus",example = "0")
    private GoodsStatusEnum goodsStatus;
    /**
     * 库存数量
     */
    @ApiModelProperty(value = "库存数量",name="goodsStock",example = "666")
    @NotBlank(groups = {InsertGroup.class})
    private String goodsStock;
    /**
     * 图片地址
     */
    @ApiModelProperty(value = "图片地址",name="pic",example = "")
  //  @NotBlank(groups = {InsertGroup.class})
    private String pic;
    /**
     * 审核状态0为默认状态,1为审核通过,2为审核失败
     */
    @ApiModelProperty(value = "审核状态0为默认状态,1为审核通过,2为审核失败",name="auditStatus",example = "1")
    private GoodsAuditStatusEnum auditStatus;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注",name="remarks",example = "真是汽油")
    private String remarks;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间",name="createDate",example = "2019-12-01 12:23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createDate;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间",name="updateDate",example = "2019-12-01 12:23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateDate;
    /**
     * 贸易条款
     */
    @ApiModelProperty(value = "贸易条款",name="tradeClause",example = "21")
    private String tradeClause;
    /**
     * 信用方式
     */
    @ApiModelProperty(value = "信用方式",name="paymentBy",example = "12")
    private String paymentBy;
    /**
     * 计价基准
     */
    @ApiModelProperty(value = "计价基准",name="pricingManner",example = "32")
    private String pricingManner;
    /**
     * 计价日期
     */
    @ApiModelProperty(value = "计价日期",name="contractualValueDate",example = "2019-12-01 12:23")
    private String contractualValueDate;
    /**
     * 发货港/提货点
     */
    @ApiModelProperty(value = "发货港/提货点",name="portOfDispatch",example = "12")
    private String portOfDispatch;
    /**
     * 装载日
     */
    @ApiModelProperty(value = "装载日",name="loadingDate",example = "2019-12-01 12:23")
    private String loadingDate;
    /**
     * 支付日
     */
    @ApiModelProperty(value = "支付日",name="payDay",example = "2019-12-01 12:23")
    private String payDay;
    /**
     * 其他说明
     */
    @ApiModelProperty(value = "其他说明",name="otherDescription",example = "这个汽油没有副作用")
    private String otherDescription;
    /**
     * 油产地
     */
    @ApiModelProperty(value = "油产地",name="placeOfOrigin",example = "墨西哥")
    private String placeOfOrigin;
    /**
     * 油种类
     */
    @ApiModelProperty(value = "油种类",name="oilType",example = "汽油")
    private String oilType;
    /**
     * api度
     */
    @ApiModelProperty(value = "api度",name="api",example = "32")
    private String api;
    /**
     * 含硫量%
     */
    @ApiModelProperty(value = "含硫量%",name="sulphurContent",example = "12")
    private String sulphurContent;
    /**
     * 酸值mgkoh/g
     */
    @ApiModelProperty(value = "酸值mgkoh/g",name="acidValue",example = "32")
    private String acidValue;
    /**
     * 残炭%
     */
    @ApiModelProperty(value = "残炭%",name="carbonResidue",example = "12")
    private String carbonResidue;
    /**
     * 镍ppm
     */
    @ApiModelProperty(value = "镍ppm",name="nickel",example = "21")
    private String nickel;
    /**
     * 钒ppm
     */
    @ApiModelProperty(value = "钒ppm",name="vanadium",example = "21")
    private String vanadium;
    /**
     * >350摄氏度质量收缩率（%）
     */
    @ApiModelProperty(value = ">350摄氏度质量收缩率（%）",name="shrink",example = "23")
    private String shrink;
    /**
     * 原油指标文件
     */
    @ApiModelProperty(value = "原油指标文件",name="filePoint",example = "\\d0e06cbb1e164e99a9129fc025dc4f68.jpg")
    private String filePoint;
    /**
     * 创建日期
     */
    @ApiModelProperty(value = "创建日期",name="creatTime",example = "2019-12-01 12:23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date creatTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间",name="updateTime",example = "2019-12-01 12:23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 升贴水
     */
    @ApiModelProperty(value = "升贴水",name="premiumsDiscounts",example = "23")
    private String premiumsDiscounts;
    /**
     *自增id
     */
    @ApiModelProperty(value = "自增id",name="id",example = "1")
    @TableField(exist = false)
    public static final String ID_COLUMN = "id";
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID",name="user_id",example = "2")
    @TableField(exist = false)
    public static final String USER_ID_COLUMN = "user_id";
    /**
     * 商品状态
     */
    @ApiModelProperty(value = "商品状态",name="goods_status",example = "1")
    @TableField(exist = false)
    public static final String GOODS_STATUS_COLUMN = "goods_status";
    /**
     * 审查状态
     */
    @ApiModelProperty(value = "审查状态",name="audit_status",example = "0")
    @TableField(exist = false)
    public static final String GOODS_AUDIT_STATUS_COLUMN = "audit_status";
    /**
     *创建时间
     */
    @ApiModelProperty(value = "创建时间",name="creat_time",example = "2019-12-01 12:23")
    @TableField(exist = false)
    public static final String CREATE_TIME_COLUMN = "creat_time";

    /**
     * 商品列表实体类
     */
    @ApiModelProperty(value = "商品列表实体类",name="goodsTypeEntity",example = "")
    @TableField(exist = false)
    private GoodsTypeEntity goodsTypeEntity;
}