package org.springblade.bgadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.bgadmin.modules.sys.mapper.AccountRepaymentDetailLogDao;
import org.springblade.bgadmin.modules.sys.entity.AccountRepaymentDetailLogEntity;
import org.springblade.bgadmin.modules.sys.service.AccountRepaymentDetailLogService;
import org.springframework.stereotype.Service;


@Service("accountRepaymentDetailLogService")
public class AccountRepaymentDetailLogServiceImpl extends ServiceImpl<AccountRepaymentDetailLogDao, AccountRepaymentDetailLogEntity> implements AccountRepaymentDetailLogService {


    @Override
    public IPage listAccountRepaymentDetailLog(IPage page, Wrapper wrapper) {
      //  wrapper = SqlHelper.fillWrapper(page,wrapper);
        page.setRecords(baseMapper.selectAccountRepaymentDetailLogList(page,wrapper));
        return page;
    }
}
