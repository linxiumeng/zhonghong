package org.springblade.bgadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.finepetro.common.utils.PageUtils;
import io.finepetro.common.utils.Query;
import io.finepetro.modules.sys.dao.FuserDao;
import io.finepetro.modules.sys.entity.FuserAccountEntity;
import io.finepetro.modules.sys.entity.FuserEntity;
import io.finepetro.modules.sys.service.FuserService;
import org.springblade.bgadmin.modules.sys.dao.FuserDao;
import org.springblade.bgadmin.modules.sys.entity.FuserEntity;
import org.springblade.bgadmin.modules.sys.service.FuserService;
import org.springblade.common.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("fuserService")
public class FuserServiceImpl extends ServiceImpl<FuserDao, FuserEntity> implements FuserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FuserEntity> page = this.selectPage(
                new Query<FuserEntity>(params).getPage(),
                new EntityWrapper<FuserEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public FuserAccountEntity getUserAccountByUserId(Integer userId) {
        return baseMapper.selectFuserAccountByUserId(userId);
    }

    @Override
    public Page<FuserAccountEntity> listUserAccountByWrapper(Page page, Wrapper wrapper) {
        wrapper = SqlHelper.fillWrapper(page,wrapper);
        page.setRecords(baseMapper.selectFuserAccountByWrapper(page,wrapper));
        return page;
    }

    @Override
    public Page<FuserAccountEntity> listWaitReviewUserAccount(Page page, Wrapper wrapper) {
        wrapper = SqlHelper.fillWrapper(page,wrapper);
        page.setRecords(baseMapper.selectWaitingReviewFuserAccountByWrapper(page,wrapper));
        return page;
    }

    @Override
    public Page<FuserAccountEntity> listWaitCreditUserAccount(Page page, Wrapper wrapper) {
        wrapper = SqlHelper.fillWrapper(page,wrapper);
        page.setRecords(baseMapper.selectWaitingCreditFuserAccountByWrapper(page,wrapper));
        return page;
    }

}
