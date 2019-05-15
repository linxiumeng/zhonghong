package org.springblade.information.controller;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;
import io.swagger.annotations.Api;
import org.springblade.common.config.CloudStorageConfig;
import org.springblade.common.exception.RRException;
import org.springblade.common.utils.R;
import org.springblade.information.service.BannerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.UUID;

@RestController
@RequestMapping("upload")
@Api(tags ="文件上传（upload）")
public class UploadController {

    @Resource
    BannerService bannerService;

    private CloudStorageConfig config = new CloudStorageConfig();
    OSSClient ossClient = new OSSClient(config.getAliyunEndPoint(), config.getAliyunAccessKeyId(),
            config.getAliyunAccessKeySecret());
    @PostMapping("/upload")
    public R upload(@RequestParam("file") MultipartFile file) throws Exception{
        if(file.isEmpty()){
            throw new RRException("上传文件不能为空");
        }
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String filename = UUID.randomUUID().toString().replace("-","")+suffix;
        //上传文件返回网址
        PutObjectResult putObjectResult = ossClient.putObject(config.getAliyunBucketName(), filename, new ByteArrayInputStream(file.getBytes()));
        String url = "https://" + config.getAliyunBucketName()+"."+config.getAliyunEndPoint()+ File.separator +filename.substring(filename.lastIndexOf("\\")+1);
        return R.ok(url);
    }

}
