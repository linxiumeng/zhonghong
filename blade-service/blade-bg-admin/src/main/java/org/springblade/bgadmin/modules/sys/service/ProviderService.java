package org.springblade.bgadmin.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.bgadmin.modules.sys.entity.ProviderEntity;
import org.springblade.common.utils.PageUtils;

import java.util.Map;

/**
 * 用户表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
public interface ProviderService extends IService<ProviderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

