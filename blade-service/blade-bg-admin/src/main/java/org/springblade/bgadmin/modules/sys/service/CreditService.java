package org.springblade.bgadmin.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.bgadmin.modules.sys.entity.CreditEntity;
import org.springblade.common.utils.PageUtils;

import java.util.Map;

/**
 * 授信表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
public interface CreditService extends IService<CreditEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

