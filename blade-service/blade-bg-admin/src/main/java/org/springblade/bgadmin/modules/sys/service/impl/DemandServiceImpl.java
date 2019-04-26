package org.springblade.bgadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.finepetro.common.utils.PageUtils;
import io.finepetro.common.utils.Query;
import io.finepetro.modules.sys.dao.DemandDao;
import io.finepetro.modules.sys.entity.DemandEntity;
import io.finepetro.modules.sys.entity.DemandOrderUserEntity;
import io.finepetro.modules.sys.form.mybatis.DemandCondition;
import io.finepetro.modules.sys.service.DemandService;
import org.springblade.bgadmin.modules.sys.dao.DemandDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("demandService")
public class DemandServiceImpl extends ServiceImpl<DemandDao, DemandEntity> implements DemandService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DemandEntity> page = this.selectPage(
                new Query<DemandEntity>(params).getPage(),
                new EntityWrapper<DemandEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public Page listDemandOrderUsers(Page page, DemandCondition condition) {
        List<DemandOrderUserEntity> records = baseMapper.selectDemandList(page,condition);
        page.setRecords(records);
        return page;
    }

}
