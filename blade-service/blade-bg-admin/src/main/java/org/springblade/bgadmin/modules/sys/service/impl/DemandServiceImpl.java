package org.springblade.bgadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.bgadmin.common.utils.Query;
import org.springblade.bgadmin.modules.sys.dao.DemandDao;
import org.springblade.bgadmin.modules.sys.entity.DemandEntity;
import org.springblade.bgadmin.modules.sys.entity.DemandOrderUserEntity;
import org.springblade.bgadmin.modules.sys.form.mybatis.DemandCondition;
import org.springblade.bgadmin.modules.sys.service.DemandService;
import org.springblade.common.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("demandService")
public class DemandServiceImpl extends ServiceImpl<DemandDao, DemandEntity> implements DemandService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DemandEntity> page = this.page(
                new Query<DemandEntity>(params).getPage(),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

    @Override
    public IPage listDemandOrderUsers(IPage page, DemandCondition condition) {
        List<DemandOrderUserEntity> records = baseMapper.selectDemandList(page,condition);
        page.setRecords(records);
        return page;
    }

}
