package org.springblade.bgadmin.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品表
 *
 * @author hanbinbinbinibinbinbin
 */
@TableName("tb_goods_type")
@Data
public class GoodsTypeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Integer id;

    /**
     * 类型名字
     */
    private String name;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 是否使用
     */
    private Integer isOpen;

    /**
     * 修改时间
     */
    private Date updateDate;


}
