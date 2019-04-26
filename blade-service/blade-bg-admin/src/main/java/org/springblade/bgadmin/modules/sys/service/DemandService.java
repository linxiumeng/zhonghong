package org.springblade.bgadmin.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.bgadmin.modules.sys.entity.DemandEntity;
import org.springblade.bgadmin.modules.sys.form.mybatis.DemandCondition;
import org.springblade.common.utils.PageUtils;

import java.util.Map;

/**
 * 需求表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:04
 */
public interface DemandService extends IService<DemandEntity> {

    PageUtils queryPage(Map<String, Object> params);

    IPage listDemandOrderUsers(IPage page, DemandCondition condition);
}

