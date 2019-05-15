/**
 * Copyright 2018 首页 http://www.finepetro.com
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.springblade.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 用户Token
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:22:07
 */
@Data
@TableName("tb_token")
@ApiModel("用户token实体类")
public class TokenEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID",name="userId",example = "136")
    @TableId(type = IdType.INPUT)
    private Long userId;
    /**
     * 用户token
     */
    @ApiModelProperty(value = "用户token",name="token",example = "13612")
    private String token;
    /**
     * 过期时间
     */
    @ApiModelProperty(value = "过期时间",name="expireTime",example = "2019-12-01 12:23")
    private Date expireTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间",name="updateTime",example = "2019-12-01 12:23")
    private Date updateTime;

    /**
     * 设置：用户ID
     */
    @ApiModelProperty(value = "设置：用户ID",name="setUserId",example = "1")
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取：用户ID
     */
    @ApiModelProperty(value = "获取：用户ID",name="getUserId",example = "1")
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置：token
     */
    @ApiModelProperty(value = "设置：token",name="setToken",example = "36")
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 获取：token
     */
    @ApiModelProperty(value = "获取：token",name="getToken",example = "36")
    public String getToken() {
        return token;
    }

    /**
     * 设置：过期时间
     */
    @ApiModelProperty(value = "设置：过期时间",name="setExpireTime",example = "2019-12-01 12:23")
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * 获取：过期时间
     */
    @ApiModelProperty(value = "获取：过期时间",name="getExpireTime",example = "2019-12-01 12:23")
    public Date getExpireTime() {
        return expireTime;
    }

    /**
     * 设置：更新时间
     */
    @ApiModelProperty(value = "设置：更新时间",name="setUpdateTime",example = "2019-12-01 12:23")
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取：更新时间
     */
    @ApiModelProperty(value = "获取：更新时间",name="getUpdateTime",example = "2019-12-01 12:23")
    public Date getUpdateTime() {
        return updateTime;
    }
}
