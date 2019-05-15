package org.springblade.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hanbin
 * 用户与账户的整合表，用于连接查询返回结果
 */
@Data
@ApiModel("用户与账户整合表")
public class UserAccount extends UserEntity {


    /**
     * 账户信息实体
     */
    @ApiModelProperty(value = "账户信息实体",name="account",example = "1365")
    private Account account;

    /**
     * 上架产品数量
     */
    @ApiModelProperty(value = "上架产品数量",name="upperGoodCount",example = "31")
    private Integer upperGoodCount;

    /**
     * 逾期次数
     */
    @ApiModelProperty(value = "逾期次数",name="overdueCount",example = "9")
    private Integer overdueCount;


}
