package org.springblade.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author hanbin
 * 聊天消息实体
 */
@Data
@TableName("tb_chat_message")
public class ChatMessage {

    /**
     * 消息id
     */
    @TableId(type = IdType.INPUT)
    private Long id;

    /**
     * 发送者
     */
    @TableField(value = "`from`")
    private Integer from;

    /**
     * 接收者
     */
    @TableField(value = "`to`")
    private Integer to;

    /**
     * 标题
     */
    private String title;

    /**
     * 消息内容，数据库编码使用utf8mb4可使用emoji表情
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改时间
     */
    private Date updateDate;

    /**
     * 消息是否已讀
     */
    @TableField(value = "`read`")
    private Boolean read;



}
