package org.springblade.bgadmin.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.bgadmin.modules.sys.entity.BannerEntity;
import org.springblade.common.utils.PageUtils;

import java.util.Map;

/**
 * 广告图表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
public interface BannerService extends IService<BannerEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 插入并返回id到实体
     * @param banner
     * @return
     */
    boolean insertReturnWithId(BannerEntity banner);
}

