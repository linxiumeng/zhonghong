package org.springblade.information.controller;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.auth.sts.AssumeRoleRequest;
import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import io.swagger.annotations.Api;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springblade.common.annotation.Login;
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
import java.net.URL;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("upload")
@Api(tags = "文件上传（upload）")
public class UploadController {

    static Logger logger = LoggerFactory.getLogger(UploadController.class);

    @Resource
    BannerService bannerService;

    private CloudStorageConfig config = new CloudStorageConfig();
    OSSClient ossClient = new OSSClient(config.getAliyunEndPoint(), config.getAliyunAccessKeyId(),
            config.getAliyunAccessKeySecret());

    OSSClient ossPrivateClient = new OSSClient(config.getAliyunEndPoint(), config.getAliyunAccessKeyId(),
            config.getAliyunAccessKeySecret());

    @PostMapping("/upload")
    public R upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String filename = UUID.randomUUID().toString().replace("-", "") + suffix;
        //上传文件返回网址
        PutObjectResult putObjectResult = ossClient.putObject(config.getAliyunBucketName(), filename, new ByteArrayInputStream(file.getBytes()));
        String url = "https://" + config.getAliyunBucketName() + "." + config.getAliyunEndPoint() + File.separator + filename.substring(filename.lastIndexOf("\\") + 1);
        return R.ok(url);
    }

    @PostMapping("/privateUpload")
    public R test(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String filename = UUID.randomUUID().toString().replace("-", "") + suffix;
        //上传文件返回网址
        PutObjectResult putObjectResult = ossClient.putObject(config.getAliyunNuoeePrivateBucketName(), filename, new ByteArrayInputStream(file.getBytes()));
        String url = "https://" + config.getAliyunNuoeePrivateDomain() + "." + config.getAliyunEndPoint() + File.separator + filename.substring(filename.lastIndexOf("\\") + 1);
        return R.ok(url);
    }

    @PostMapping("/privateFileUrl")
    @Login
    public R getStsInfo(String objectName) throws Exception {

        String endpoint = "sts.aliyuncs.com";
        String accessKeyId = "LTAIFeV8lu5U5sOU";
        String accessKeySecret = "ST7cYpABerW8dGjwngp0eSQSmaOxfG";
        String roleArn = "acs:ram::1555578657709723:role/ramoss";
        String roleSessionName = "oss-temperary";
        String policy = "{\n" +
                "    \"Version\": \"1\", \n" +
                "    \"Statement\": [\n" +
                "        {\n" +
                "            \"Action\": [\n" +
                "                \"oss:*\"\n" +
                "            ], \n" +
                "            \"Resource\": [\n" +
                "                \"acs:oss:*:*:*\" \n" +
                "            ], \n" +
                "            \"Effect\": \"Allow\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        AssumeRoleResponse response = null;
        try {
            // 添加endpoint（直接使用STS endpoint，前两个参数留空，无需添加region ID）
            DefaultProfile.addEndpoint("", "", "Sts", endpoint);
            // 构造default profile（参数留空，无需添加region ID）
            IClientProfile profile = DefaultProfile.getProfile("", accessKeyId, accessKeySecret);
            // 用profile构造client
            DefaultAcsClient client = new DefaultAcsClient(profile);
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setMethod(MethodType.POST);
            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
            // 若policy为空，则用户将获得该角色下所有权限
            request.setPolicy(policy);
            // 设置凭证有效时间
            request.setDurationSeconds(1000L);
            response = client.getAcsResponse(request);
        } catch (ClientException e) {
            logger.error("Failed：{}", ExceptionUtils.getFullStackTrace(e));
        }

        OSSClient ossClient = new OSSClient(endpoint, response.getCredentials().getAccessKeyId(), response.getCredentials().getAccessKeySecret(), response.getCredentials().getSecurityToken());
        //    OSSClient ossClient = new OSSClient(endpoint,"LTAI6C8UVSnZIV07","5KO3tKKh8UBhKoB9BQKahxfEHAgUo0");
        // 设置URL过期时间为1小时。
        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        URL url = ossClient.generatePresignedUrl("nuoee", objectName, expiration);
        System.out.println(url.getQuery());
        return R.ok().put("result",url.getQuery());
    }




}
