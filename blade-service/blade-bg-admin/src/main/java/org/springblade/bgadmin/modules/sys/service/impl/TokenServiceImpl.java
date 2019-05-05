package org.springblade.bgadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.bgadmin.common.utils.Query;
import org.springblade.bgadmin.modules.sys.mapper.TokenDao;

import org.springblade.bgadmin.modules.sys.service.TokenService;
import org.springblade.common.entity.TokenEntity;
import org.springblade.common.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("tokenService")
public class TokenServiceImpl extends ServiceImpl<TokenDao, TokenEntity> implements TokenService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TokenEntity> page = this.page(
                new Query<TokenEntity>(params).getPage(),
                new QueryWrapper<TokenEntity>()
        );

        return new PageUtils(page);
    }

}
