package org.springblade.bgadmin.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 新闻表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@TableName("tb_news")
public class NewsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Integer id;
    /**
     * 标题
     */
    private String title;
    /**
     * 编辑人
     */
    private String editor;
    /**
     * 内容
     */
    private String details;
    /**
     * 标签
     */
    private String tab;
    /**
     * 阅读数
     */
    private Integer views;
    /**
     * 新闻来源
     */
    private String origin;
    /**
     *
     */
    private Date createDate;
    /**
     *
     */
    private Date updateDate;

    /**
     * 设置：
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置：标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取：标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置：编辑人
     */
    public void setEditor(String editor) {
        this.editor = editor;
    }

    /**
     * 获取：编辑人
     */
    public String getEditor() {
        return editor;
    }

    /**
     * 设置：内容
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * 获取：内容
     */
    public String getDetails() {
        return details;
    }

    /**
     * 设置：标签
     */
    public void setTab(String tab) {
        this.tab = tab;
    }

    /**
     * 获取：标签
     */
    public String getTab() {
        return tab;
    }

    /**
     * 设置：阅读数
     */
    public void setViews(Integer views) {
        this.views = views;
    }

    /**
     * 获取：阅读数
     */
    public Integer getViews() {
        return views;
    }

    /**
     * 设置：新闻来源
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * 获取：新闻来源
     */
    public String getOrigin() {
        return origin;
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
