package org.springblade.bgadmin.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.bgadmin.modules.sys.entity.QuotationEntity;
import org.springblade.common.utils.PageUtils;

import java.util.Map;

/**
 * 报价单表

 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:04
 */
public interface QuotationService extends IService<QuotationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

