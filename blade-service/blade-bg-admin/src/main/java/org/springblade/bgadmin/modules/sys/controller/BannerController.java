package org.springblade.bgadmin.modules.sys.controller;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.finepetro.common.exception.RRException;
import io.finepetro.common.utils.R;
import io.finepetro.common.validator.ValidatorUtils;
import io.finepetro.modules.oss.cloud.CloudStorageConfig;
import io.finepetro.modules.sys.entity.BannerEntity;
import io.finepetro.modules.sys.service.BannerService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
public class BannerController {
    @Autowired
    private BannerService bannerService;

    private CloudStorageConfig config = new CloudStorageConfig();
    OSSClient ossClient = new OSSClient(config.getAliyunEndPoint(), config.getAliyunAccessKeyId(),
            config.getAliyunAccessKeySecret());

    /**
     * 列表sys:banner:list
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:banner:list")
    public R list(@RequestParam Map<String, Object> params) {
        return R.ok().put("result", bannerService.selectList(new EntityWrapper<BannerEntity>().orderBy("`sort`", false)));

    }


    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:banner:add")
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
    @RequestMapping("/update")
    @RequiresPermissions("sys:banner:showhide")
    public R update(@RequestBody BannerEntity banner) {
        ValidatorUtils.validateEntity(banner);
        bannerService.updateById(banner);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:banner:delete")
    public R delete(@RequestBody Integer[] ids) {
        bannerService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    @PostMapping("order")
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
