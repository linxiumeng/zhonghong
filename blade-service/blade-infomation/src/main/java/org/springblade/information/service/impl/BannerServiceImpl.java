package org.springblade.information.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.common.entity.Banner;
import org.springblade.information.mapper.BannerDao;
import org.springblade.information.service.BannerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Banner)表服务实现类
 *
 * @author linxiumeng
 * @since 2019-03-06 18:32:12
 */
@Service("bannerService")
public class BannerServiceImpl extends ServiceImpl<BannerDao, Banner> implements BannerService {
    @Resource
    private BannerDao bannerDao;

    @Override
    public List<Banner> listIsOpenBanners() {
        QueryWrapper<Banner> wrapper = new QueryWrapper<>();
        //查询展示出来的和 按照倒叙排列
        wrapper.eq("is_open","1").orderBy(true,false,"sort");
        return this.list(wrapper);
    }
}