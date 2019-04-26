package org.springblade.bgadmin.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.bgadmin.modules.sys.entity.OrderEntity;
import org.springblade.common.utils.PageUtils;

import java.util.Map;

/**
 * 采购单表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

