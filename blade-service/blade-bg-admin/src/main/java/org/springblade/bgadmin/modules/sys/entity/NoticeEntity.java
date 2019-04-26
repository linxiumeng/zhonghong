package org.springblade.bgadmin.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 通知表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@TableName("tb_notice")
public class NoticeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

            /**
         * 
         */
                @TableId
            private Integer id;
            /**
         * 发布人
         */
            private String issuer;
            /**
         * 
         */
            private String title;
            /**
         * 
         */
            private String desc;
            /**
         * 
         */
            private Date createDate;
            /**
         * 
         */
            private Date updateDate;

            private Integer isOpen;

    public Integer getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Integer isOpen) {
        this.isOpen = isOpen;
    }

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
         * 设置：发布人
         */
        public void setIssuer(String issuer) {
            this.issuer = issuer;
        }

        /**
         * 获取：发布人
         */
        public String getIssuer() {
            return issuer;
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
        public void setDesc(String desc) {
            this.desc = desc;
        }

        /**
         * 获取：
         */
        public String getDesc() {
            return desc;
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
