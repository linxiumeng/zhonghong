package org.springblade.bgadmin.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 聊天记录
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@TableName("tb_chat_message")
public class ChatMessageEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private Integer id;
    /**
     *
     */
    private Integer from;
    /**
     *
     */
    private Integer to;
    /**
     *
     */
    private String title;
    /**
     *
     */
    private String content;
    /**
     *
     */
    private Integer read;
    /**
     *
     */
    private Date createDate;
    /**
     *
     */
    private Date updateDate;

    /**
     * 设置：主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置：
     */
    public void setFrom(Integer from) {
        this.from = from;
    }

    /**
     * 获取：
     */
    public Integer getFrom() {
        return from;
    }

    /**
     * 设置：
     */
    public void setTo(Integer to) {
        this.to = to;
    }

    /**
     * 获取：
     */
    public Integer getTo() {
        return to;
    }

    /**
     * 设置：
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取：
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置：
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取：
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置：
     */
    public void setRead(Integer read) {
        this.read = read;
    }

    /**
     * 获取：
     */
    public Integer getRead() {
        return read;
    }

    /**
     * 设置：
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取：
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置：
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取：
     */
    public Date getUpdateDate() {
        return updateDate;
    }
}
