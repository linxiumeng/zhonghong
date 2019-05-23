/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package org.springblade.common.config;



import lombok.Data;

import java.io.Serializable;

/**
 * 云存储配置信息
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
public class CloudStorageConfig implements Serializable {

    //公共模版（public）所有人都可以访问的
    private static final long serialVersionUID = 1L;
    private String aliyunDomain = "zhonghong.oss-cn-hangzhou.aliyuncs.com";
    private String aliyunPrefix = "jpg";
    private String aliyunEndPoint = "oss-cn-hangzhou.aliyuncs.com";
    private String aliyunAccessKeyId = "LTAIbJYkwqZFcIL0";
    private String aliyunAccessKeySecret = "4OpgYYWYwZyr5VvBToy4pVJ9hfUHom";
    private String aliyunBucketName = "zhonghong";


    private String aliyunPrivateDomain = "zhonghong-private.oss-cn-hangzhou.aliyuncs.com";
    private String aliyunPrivatePrefix = "jpg";
    private String aliyunPrivateEndPoint = "oss-cn-hangzhou.aliyuncs.com";
    private String aliyunPrivateAccessKeyId = "LTAIbJYkwqZFcIL0";
    private String aliyunPrivateAccessKeySecret = "4OpgYYWYwZyr5VvBToy4pVJ9hfUHom";
    private String aliyunPrivateBucketName = "zhonghong-private";


    private String aliyunNuoeePrivateDomain = "nuoee.oss-cn-hangzhou.aliyuncs.com";
    private String aliyunNuoeePrivatePrefix = "jpg";
    private String aliyunNuoeePrivateEndPoint = "oss-cn-hangzhou.aliyuncs.com";
    private String aliyunNuoeePrivateAccessKeyId = "LTAIbJYkwqZFcIL0";
    private String aliyunNuoeePrivateAccessKeySecret = "4OpgYYWYwZyr5VvBToy4pVJ9hfUHom";
    private String aliyunNuoeePrivateBucketName = "nuoee";
}
