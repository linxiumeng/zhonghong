package org.springblade.common.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * (Banner) 实体类
 *
 * @author linxiumeng
 * @since 2019-03-06 18:32:12
 */
@Data
@TableName("tb_banner")
@ApiModel("广告实体类")
public class Banner {

    /**
     * 主键ID
     */
    @TableId(type = IdType.INPUT)
    @ApiModelProperty(value = "主键ID",name="id",example = "1")
    private Long id;
    /**
     * logo
     */
    @ApiModelProperty(value = "logo",name="logo",example = "假装有图片")
    private String logo;
    /**
     * banner路径
     */
    @ApiModelProperty(value = "banner路径",name="path",example = "假装有路径")
    private String path;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序",name="sort",example = "1")
    private Integer sort;
    /**
     * 是否展示 0-隐藏，1-展示
     */
    @ApiModelProperty(value = "是否展示 0-隐藏，1-展示",name="isOpen",example = "1")
    private Integer isOpen;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间",name="createDate",example = "2019-12-01 12:23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createDate;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间",name="updateDate",example = "2019-12-01 12:23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateDate;
}