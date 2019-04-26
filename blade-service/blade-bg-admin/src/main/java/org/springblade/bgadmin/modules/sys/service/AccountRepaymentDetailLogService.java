package org.springblade.bgadmin.modules.sys.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.bgadmin.modules.sys.entity.AccountRepaymentDetailLogEntity;

/**
 * 分期还款表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
public interface AccountRepaymentDetailLogService extends IService<AccountRepaymentDetailLogEntity> {


    IPage listAccountRepaymentDetailLog(IPage page, Wrapper wrapper);

}

