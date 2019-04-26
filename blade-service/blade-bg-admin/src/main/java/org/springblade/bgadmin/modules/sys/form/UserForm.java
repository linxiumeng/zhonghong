package org.springblade.bgadmin.modules.sys.form;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springblade.bgadmin.modules.sys.enums.UserCreditStatusEnum;
import org.springblade.bgadmin.modules.sys.enums.UserLoginStatusEnum;
import org.springblade.bgadmin.modules.sys.enums.UserStatusEnum;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * @author hanbin
 * 用户表单
 */
@Data
public class UserForm extends BaseForm{


    /**
     * 登录状态
     */
    @JsonInclude(NON_EMPTY)
    UserLoginStatusEnum loginStatus = null;

    /**
     * 用户状态
     */
    @JsonInclude(NON_EMPTY)
    UserStatusEnum status = null;

    /**
     * 授信状态
     */
    @JsonInclude(NON_EMPTY)
    UserCreditStatusEnum creditStatus = null;

    /**
     * 用户ID
     */
    Integer userId;


    /**
     * 等待条件
     */
    private Integer waitPageConditionStatus;

    /**
     * 授信评级
     */
    private String creditLevel;

    /**
     * 单笔最高额度
     */
    private String creditUnit;

    /**
     * 最高可用额度(剩余) = 审核额度 - 已用额度
     */
    private String creditHigh;

    /**
     * 全部（）
     */
    private String creditLimit;



}

