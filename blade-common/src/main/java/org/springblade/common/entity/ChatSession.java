package org.springblade.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author hanbin
 * 聊天会话 设置成 双向的 设置成单向的会导致共享问题
 */
@Data
@TableName("tb_chat_session")
public class ChatSession {

    /**
     * 会话id
     */
    @TableId(type = IdType.INPUT)
    private Long id;

    /**
     * 创建者
     */
    @TableField(value = "`from`")
    private Integer from;

    /**
     * 接受者
     */
    @TableField(value = "`to`")
    private Integer to;

    /**
     * 创建日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createDate;

    /**
     * 更新日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateDate;

    /**
     * 暂时就两个状态 0 - 普通 ， 1 - 置顶
     */
    @TableField(value = "`status`")
    private Integer status;


    /**
     * 未读数
     */
    @TableField(exist = false)
    private int unread;

    /**
     * 最后一条消息
     */
    @TableField(exist = false)
    private ChatMessage lastMessage;

    /**
     * 接受者
     */
    @TableField(exist = false)
    private UserEntity fromUser;


}
