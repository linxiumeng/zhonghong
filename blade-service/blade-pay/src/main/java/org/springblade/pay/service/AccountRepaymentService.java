package org.springblade.pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.common.entity.AccountRepayment;


/**
 * 分歧还款表(TbAccountRepayment)表服务接口
 *
 * @author linxiumeng
 * @since 2019-02-18 11:59:29
 */
public interface AccountRepaymentService extends IService<AccountRepayment> {

    /**
     * 返回主键的插入
     * @param accountRepayment
     * @return
     */
    int insertWithId(AccountRepayment accountRepayment);



}