package org.springblade.bgadmin.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 广告图表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@TableName("tb_banner")
public class BannerEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Integer id;
    /**
     * logo
     */
    private String logo;
    /**
     * banner地址
     */
    private String path;
    /**
     * 排序编号
     */
    private Integer sort;
    /**
     * 是否打开 0-隐藏， 1-可见
     */
    private Integer isOpen;
    /**
     * 常见时间
     */
    private Date createDate;
    /**
     * 更新时间
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
     * 设置：
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * 获取：
     */
    public String getLogo() {
        return logo;
    }

    /**
     * 设置：
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取：
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置：
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取：
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置：
     */
    public void setIsOpen(Integer isOpen) {
        this.isOpen = isOpen;
    }

    /**
     * 获取：
     */
    public Integer getIsOpen() {
        return isOpen;
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
