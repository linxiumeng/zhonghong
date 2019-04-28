package org.springblade.bgadmin.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

import java.util.Date;

/**
 * @author hanbin
 * 常见问题实体
 */
@TableName("tb_faq")
@Data
public class FaqEntity {

    /**
     * id
     */
    @TableId(type = IdType.INPUT)
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 循序
     */
    @TableField("`order`")
    private Integer order;

    /**
     * 是否显示
     */
    private Integer isOpen;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新时间
     */
    private Date updateDate;

}
