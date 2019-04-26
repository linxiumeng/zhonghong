package org.springblade.common.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Banner {

    /***/
    @TableId(type = IdType.INPUT)
    private Long id;
    /**
     * logo
     */
    private String logo;
    /**
     * banner路径
     */
    private String path;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 是否展示 0-隐藏，1-展示
     */
    private Integer isOpen;
    /***/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createDate;
    /***/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateDate;
}