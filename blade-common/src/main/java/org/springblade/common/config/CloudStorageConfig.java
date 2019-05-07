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
    private static final long serialVersionUID = 1L;
    private String aliyunDomain = "zhonghong.oss-cn-hangzhou.aliyuncs.com";
    private String aliyunPrefix = "jpg";
    private String aliyunEndPoint = "oss-cn-hangzhou.aliyuncs.com";
    private String aliyunAccessKeyId = "LTAIbJYkwqZFcIL0";
    private String aliyunAccessKeySecret = "4OpgYYWYwZyr5VvBToy4pVJ9hfUHom";
    private String aliyunBucketName = "zhonghong";
}
