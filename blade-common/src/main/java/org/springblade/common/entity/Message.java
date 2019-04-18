package org.springblade.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author hanbin
 * 站内信实体类
 */
@Data
@TableName("tb_message")
public class Message {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 发送人
     */
    private String from;

    /**
     * 接收人
     */
    private String to;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 状态 0 - 未读 ， 1 - 已读
     */
    private Integer status;

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

}
