package org.springblade.bgadmin.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 帮助配置表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@TableName("tb_article")
public class ArticleEntity implements Serializable {
    private static final long serialVersionUID = 1L;

            /**
         * 
         */
                @TableId
            private Integer id;
            /**
         * 标签名
         */
            private String tabName;
            /**
         * 标签内容
         */
            private String tabDetails;
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
         * 设置：标签名
         */
        public void setTabName(String tabName) {
            this.tabName = tabName;
        }

        /**
         * 获取：标签名
         */
        public String getTabName() {
            return tabName;
        }
            /**
         * 设置：标签内容
         */
        public void setTabDetails(String tabDetails) {
            this.tabDetails = tabDetails;
        }

        /**
         * 获取：标签内容
         */
        public String getTabDetails() {
            return tabDetails;
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
