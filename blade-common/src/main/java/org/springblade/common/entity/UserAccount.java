package org.springblade.common.entity;

import lombok.Data;

/**
 * @author hanbin
 * 用户与账户的整合表，用于连接查询返回结果
 */
@Data
public class UserAccount extends UserEntity {


    /**
     * 账户信息实体
     */
    private Account account;

    /**
     * 上架产品数量
     */
    private Integer upperGoodCount;

    /**
     * 逾期次数
     */
    private Integer overdueCount;


}
