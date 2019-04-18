package org.springblade.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hanbin
 * 新闻实体类
 */
@Data
@TableName("tb_news")
public class News implements Serializable {

    /**
     * 文章id
     */
    @TableId(type = IdType.INPUT)
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 作者
     */
    private String editor;

    /**
     * 文章详情（富文本）
     */
    private String details;

    /**
     * 标签
     */
    private String tab;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createDate;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateDate;

    /**
     * 阅读数
     */
    private Long views;

    /**
     * 来源
     */
    private String origin;

    /**
     * 类型 0 - 国内 ， 1 - 国际
     */
    private Integer type;


}
