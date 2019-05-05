package org.springblade.bgadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.bgadmin.common.utils.Query;
import org.springblade.bgadmin.modules.sys.mapper.FuserDao;
import org.springblade.bgadmin.modules.sys.entity.FuserAccountEntity;
import org.springblade.bgadmin.modules.sys.entity.FuserEntity;
import org.springblade.bgadmin.modules.sys.service.FuserService;
import org.springblade.common.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("fuserService")
public class
FuserServiceImpl extends ServiceImpl<FuserDao, FuserEntity> implements FuserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FuserEntity> page = this.page(
                new Query<FuserEntity>(params).getPage(),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

    @Override
    public FuserAccountEntity getUserAccountByUserId(Integer userId) {
        return baseMapper.selectFuserAccountByUserId(userId);
    }

    @Override
    public IPage<FuserAccountEntity> listUserAccountByWrapper(IPage page, Wrapper wrapper) {
        //wrapper = SqlHelper.fillWrapper(page,wrapper);
        page.setRecords(baseMapper.selectFuserAccountByWrapper(page,wrapper));
        return page;
    }

    @Override
    public IPage<FuserAccountEntity> listWaitReviewUserAccount(IPage page, Wrapper wrapper) {
        //wrapper = SqlHelper.fillWrapper(page,wrapper);
        page.setRecords(baseMapper.selectWaitingReviewFuserAccountByWrapper(page,wrapper));
        return page;
    }

    @Override
    public IPage<FuserAccountEntity> listWaitCreditUserAccount(IPage page, Wrapper wrapper) {
        //wrapper = SqlHelper.fillWrapper(page,wrapper);
        page.setRecords(baseMapper.selectWaitingCreditFuserAccountByWrapper(page,wrapper));
        return page;
    }

}
