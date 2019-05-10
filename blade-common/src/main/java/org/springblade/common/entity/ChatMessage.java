package org.springblade.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author hanbin
 * 聊天消息实体
 */
@Data
@TableName("tb_chat_message")
@ApiModel("聊天消息实体类")
public class ChatMessage {

    /**
     * 消息id
     */
    @ApiModelProperty(value = "消息id",name="id",example = "1")
    @TableId(type = IdType.INPUT)
    private Long id;

    /**
     * 发送者
     */
    @ApiModelProperty(value = "发送者",name="from",example = "1")
    @TableField(value = "`from`")
    private Integer from;

    /**
     * 接收者
     */
    @ApiModelProperty(value = "接收者",name="to",example = "2")
    @TableField(value = "`to`")
    private Integer to;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题",name="title",example = "我开花")
    private String title;

    /**
     * 消息内容，数据库编码使用utf8mb4可使用emoji表情
     */
    @ApiModelProperty(value = "消息内容，数据库编码使用utf8mb4可使用emoji表情",name="content",example = "不懂")
    private String content;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间",name="createDate",example = "2019-12-01 12:23")
    private Date createDate;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间",name="updateDate",example = "2019-12-01 12:23")
    private Date updateDate;

    /**
     * 消息是否已讀
     */
    @ApiModelProperty(value = "消息是否已讀",name="read",example = "true")
    @TableField(value = "`read`")
    private Boolean read;



}
