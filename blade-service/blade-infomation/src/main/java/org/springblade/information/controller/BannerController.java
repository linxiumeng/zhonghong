package org.springblade.information.controller;

import io.swagger.annotations.Api;
import org.springblade.common.entity.Banner;
import org.springblade.common.utils.R;
import org.springblade.information.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author linxiumeng
 * @since 2019-03-06 18:32:12
 */
@Api(tags = "(Banner)表操作控制器",description = " * @author linxiumeng")
@RestController
@RequestMapping("api/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;


    @PostMapping("getBanner")
    public R getBanner(){
        List<Banner> banners = bannerService.listIsOpenBanners();
        return R.ok().put("result",banners);
    }
    
}