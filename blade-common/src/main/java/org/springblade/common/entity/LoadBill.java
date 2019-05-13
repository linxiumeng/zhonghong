package org.springblade.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author hanbin
 * 提货单实体
 */
@Data
@TableName("tb_load_bill")
public class LoadBill {

    @TableId(type = IdType.INPUT)
    private Integer id;

    private Integer orderId;

    private String path;

    private Date createTime;

    private Date updateTime;

}
