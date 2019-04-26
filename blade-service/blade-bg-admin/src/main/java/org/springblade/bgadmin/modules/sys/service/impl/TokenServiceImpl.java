package org.springblade.bgadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.finepetro.common.utils.PageUtils;
import io.finepetro.common.utils.Query;
import io.finepetro.modules.sys.dao.TokenDao;
import io.finepetro.modules.sys.entity.TokenEntity;
import io.finepetro.modules.sys.service.TokenService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("tokenService")
public class TokenServiceImpl extends ServiceImpl<TokenDao, TokenEntity> implements TokenService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TokenEntity> page = this.selectPage(
                new Query<TokenEntity>(params).getPage(),
                new EntityWrapper<TokenEntity>()
        );

        return new PageUtils(page);
    }

}
