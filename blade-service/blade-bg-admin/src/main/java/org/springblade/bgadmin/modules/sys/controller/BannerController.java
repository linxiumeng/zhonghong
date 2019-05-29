package org.springblade.bgadmin.modules.sys.controller;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.bgadmin.modules.oss.cloud.CloudStorageConfig;
import org.springblade.bgadmin.modules.sys.entity.BannerEntity;
import org.springblade.bgadmin.modules.sys.service.BannerService;
import org.springblade.common.exception.RRException;
import org.springblade.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 * 广告图表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@RestController
@RequestMapping("sys/banner")
@Api(tags = "广告图表", description = " * @author jinzeze")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    private CloudStorageConfig config = new CloudStorageConfig();
    OSSClient ossClient = new OSSClient(config.getAliyunEndPoint(), config.getAliyunAccessKeyId(),
            config.getAliyunAccessKeySecret());

    /**
     * 列表sys:banner:list
     */
    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "")
    //@RequiresPermissions("sys:banner:list")
    public R list(@RequestParam Map<String, Object> params) {
        return R.ok().put("result", bannerService.list(new QueryWrapper<BannerEntity>().orderBy(true,false,"`sort`")));

    }


    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("sys:banner:add")
    @ApiOperation(value = "保存", notes = "")
    public R save(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为NULL");
        }
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String filename = UUID.randomUUID().toString().replace("-", "") + suffix;
        //上传文件返回网址
        PutObjectResult putObjectResult = null;
        try {
            putObjectResult = ossClient.putObject(config.getAliyunBucketName(), filename, new ByteArrayInputStream(file.getBytes()));
        } catch (IOException e) {
            return R.error();
        }

        if (putObjectResult != null) {
            String url = "https://" + config.getAliyunBucketName() + "." + config.getAliyunEndPoint() + File.separator + filename.substring(filename.lastIndexOf("\\") + 1);

            BannerEntity banner = new BannerEntity();
            banner.setSort(0);
            banner.setIsOpen(0);
            banner.setPath(url);

            boolean flag = bannerService.insertReturnWithId(banner);
            if (flag) {
                return R.ok().put("result", banner);
            }
        }
        return R.error();

    }


    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "")
    //@RequiresPermissions("sys:banner:showhide")
    public R update(@RequestBody BannerEntity banner) {
        //ValidatorUtils.validateEntity(banner);
        bannerService.updateById(banner);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "")
    //@RequiresPermissions("sys:banner:delete")
    public R delete(@RequestBody Integer[] ids) {
        bannerService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

    @PostMapping("order")
    @ApiOperation(value = "订单", notes = "")
    public R orderBanners(@RequestBody List<Integer> bannerIds) {

        int cusor = bannerIds.size();
        LinkedList<BannerEntity> banners = new LinkedList<>();
        for (Integer bannerId : bannerIds) {
            BannerEntity bannerEntity = new BannerEntity();
            bannerEntity.setId(bannerId);
            bannerEntity.setSort(cusor--);
            banners.add(bannerEntity);
        }

        boolean flag = bannerService.updateBatchById(banners);

        if (flag) {
            return R.ok();
        }

        return R.error("更新失败");
    }

}
