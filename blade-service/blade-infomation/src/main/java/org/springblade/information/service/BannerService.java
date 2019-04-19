package org.springblade.information.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.common.entity.Banner;

import java.util.List;

/**
 * (Banner)表服务接口
 *
 * @author linxiumeng
 * @since 2019-03-06 18:32:12
 */
public interface BannerService extends IService<Banner> {

    /**
     * 显示展示出来的banner
     * @return
     */
    List<Banner> listIsOpenBanners();
    
}