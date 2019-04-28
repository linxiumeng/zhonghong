package org.springblade.bgadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.bgadmin.common.utils.Query;
import org.springblade.bgadmin.modules.sys.mapper.AttestDao;
import org.springblade.bgadmin.modules.sys.entity.AttestEntity;
import org.springblade.bgadmin.modules.sys.service.AttestService;
import org.springblade.common.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("attestService")
public class AttestServiceImpl extends ServiceImpl<AttestDao, AttestEntity> implements AttestService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttestEntity> page = this.page(
                new Query<AttestEntity>(params).getPage(),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

}
