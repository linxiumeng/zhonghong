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
 * (Announcement) 实体类
 *
 * @author linxiumeng
 * @since 2019-03-12 10:46:58
 */
@Data
@TableName("tb_announcement")
@ApiModel("公告实体类")
public class Announcement {

    /**
     * 主键id
     */
    @TableId(type = IdType.INPUT)
    @ApiModelProperty(value = "主键id",name="id",example = "1")
    private Integer id;
    /**
     * 标题
     */
    @ApiModelProperty(value = "标题",name="title",example = "震惊，马云居然在阿里日婚礼当天说出这种话")
    private String title;
    /**
     * 内容
     */
    @ApiModelProperty(value = "内容",name="body",example = "近日，我国大部分人衣服都穿的特别少，还经常发热流汗，经过专家数天的不断排查，最终终于确定是因为进入了夏天。")
    private String body;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间",name="creatTime",example = "2019-12-01 12:23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date creatTime;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间",name="updateTime",example = "2019-12-01 12:23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 修改人/作者
     */
    @ApiModelProperty(value = "修改人/作者",name="modifier",example = "帅泽泽")
    private String modifier;
}