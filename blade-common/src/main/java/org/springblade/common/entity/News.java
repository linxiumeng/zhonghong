package org.springblade.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hanbin
 * 新闻实体类
 */
@Data
@TableName("tb_news")
@ApiModel("新闻实体类")
public class News implements Serializable {

    /**
     * 文章id
     */
    @ApiModelProperty(value = "文章id",name="id",example = "1")
    @TableId(type = IdType.INPUT)
    private Long id;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题",name="title",example = "uc震惊部大肆招人中")
    private String title;

    /**
     * 作者
     */
    @ApiModelProperty(value = "作者",name="editor",example = "帅泽泽")
    private String editor;

    /**
     * 文章详情（富文本）
     */
    @ApiModelProperty(value = "文章详情（富文本）",name="details",example = "随便写点吸引人的内容就行了")
    private String details;

    /**
     * 标签
     */
    @ApiModelProperty(value = "标签",name="tab",example = "石油")
    private String tab;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间",name="createDate",example = "2019-12-01 12:23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createDate;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间",name="updateDate",example = "2019-12-01 12:23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateDate;

    /**
     * 阅读数
     */
    @ApiModelProperty(value = "阅读数",name="views",example = "365")
    private Long views;

    /**
     * 来源
     */
    @ApiModelProperty(value = "来源",name="origin",example = "华东新闻网")
    private String origin;

    /**
     * 类型 0 - 国内 ， 1 - 国际
     */
    @ApiModelProperty(value = "类型 0 - 国内 ， 1 - 国际",name="type",example = "0")
    private Integer type;


}
