package org.springblade.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
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
     *自增id
     */
    @TableId
    @ApiModelProperty(value = "自增id",name="id",example = "1")
    private Long id;

    /**
     * 类型名字
     */
    @ApiModelProperty(value = "类型名字",name="name",example = "汽油")
    private String name;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间",name="createDate",example = "2019-12-01 12:23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    /**
     * 是否使用
     */
    @ApiModelProperty(value = "是否使用",name="isOpen",example = "1")
    private Integer isOpen;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间",name="updateDate",example = "2019-12-01 12:23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;


}
