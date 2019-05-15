package org.springblade.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hanbin
 * 继承了需求类，用于连表查询返回数据
 */
@Data
@TableName("tb_demand")
@ApiModel("需求实体类")
public class DemandDetail extends Demand {

    /**
     * 账户
     */
    @ApiModelProperty(value = "账户",name="mobile",example = "123454321")
    private String mobile;

    /**
     * 公司名字
     */
    @ApiModelProperty(value = "公司名字",name="companyName",example = "随便叫什么公司")
    private String companyName;

    /**
     * 报价次数
     */
    @ApiModelProperty(value = "报价次数",name="quoteTimes",example = "136")
    private Integer quoteTimes;

    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态",name="orderStatus",example = "1")
    private Integer orderStatus;


}
