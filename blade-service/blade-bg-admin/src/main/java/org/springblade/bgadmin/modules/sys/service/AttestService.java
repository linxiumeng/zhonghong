package org.springblade.bgadmin.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.bgadmin.modules.sys.entity.AttestEntity;
import org.springblade.common.utils.PageUtils;

import java.util.Map;

/**
 * 企业认证信息表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
public interface AttestService extends IService<AttestEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

