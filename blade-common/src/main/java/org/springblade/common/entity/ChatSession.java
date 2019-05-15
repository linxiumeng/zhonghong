package org.springblade.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author hanbin
 * 聊天会话 设置成 双向的 设置成单向的会导致共享问题
 */
@Data
@TableName("tb_chat_session")
@ApiModel("会话实体类")
public class ChatSession {

    /**
     * 会话id
     */
    @TableId(type = IdType.INPUT)
    @ApiModelProperty(value = "会话id",name="id",example = "1")
    private Long id;

    /**
     * 创建者
     */
    @TableField(value = "`from`")
    @ApiModelProperty(value = "创建者",name="from",example = "1")
    private Integer from;

    /**
     * 接受者
     */
    @TableField(value = "`to`")
    @ApiModelProperty(value = "接受者",name="to",example = "1")
    private Integer to;

    /**
     * 创建日期
     */
    @ApiModelProperty(value = "创建日期",name="createDate",example = "2019-12-01 12:23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createDate;

    /**
     * 更新日期
     */
    @ApiModelProperty(value = "更新日期",name="updateDate",example = "2019-12-01 12:23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateDate;

    /**
     * 暂时就两个状态 0 - 普通 ， 1 - 置顶
     */
    @ApiModelProperty(value = "暂时就两个状态 0 - 普通 ， 1 - 置顶",name="status",example = "1")
    @TableField(value = "`status`")
    private Integer status;


    /**
     * 未读数
     */
    @ApiModelProperty(value = "未读数",name="unread",example = "23")
    @TableField(exist = false)
    private int unread;

    /**
     * 最后一条消息
     */
    @ApiModelProperty(value = "最后一条消息",name="lastMessage",example = "晚安")
    @TableField(exist = false)
    private ChatMessage lastMessage;

    /**
     * 接受者
     */
    @ApiModelProperty(value = "接受者",name="fromUser",example = "泽泽")
    @TableField(exist = false)
    private UserEntity fromUser;


}
