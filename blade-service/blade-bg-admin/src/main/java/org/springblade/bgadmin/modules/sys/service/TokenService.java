package org.springblade.bgadmin.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.common.entity.TokenEntity;
import org.springblade.common.utils.PageUtils;

import java.util.Map;

/**
 * 密钥
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
public interface TokenService extends IService<TokenEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

