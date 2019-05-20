package org.springblade.pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.common.entity.AccountRepaymentStep;

import java.util.List;

/**
 * 分期还款详情表(AccountRepaymentStep)表服务接口
 *
 * @author linxiumeng
 * @since 2019-03-13 13:33:47
 */
public interface AccountRepaymentStepService extends IService<AccountRepaymentStep> {

    int countNotRepayment(Long repaymentId);
    
}