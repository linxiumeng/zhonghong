package org.springblade.bgadmin.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 银行表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@TableName("tb_bank")
public class BankEntity implements Serializable {
    private static final long serialVersionUID = 1L;

            /**
         * 
         */
                @TableId
            private Integer id;
            /**
         * 银行名称
         */
            private String bankName;
            /**
         * 银行编码
         */
            private String bankCode;
            /**
         * 联行号
         */
            private String lineNumbe;
            /**
         * 省
         */
            private String province;
            /**
         * 市
         */
            private String city;
            /**
         * 区
         */
            private String area;
            /**
         * 支行地址
         */
            private String address;
            /**
         * 银行卡状态
         */
            private Integer status;
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
         * 设置：银行名称
         */
        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        /**
         * 获取：银行名称
         */
        public String getBankName() {
            return bankName;
        }
            /**
         * 设置：银行编码
         */
        public void setBankCode(String bankCode) {
            this.bankCode = bankCode;
        }

        /**
         * 获取：银行编码
         */
        public String getBankCode() {
            return bankCode;
        }
            /**
         * 设置：联行号
         */
        public void setLineNumbe(String lineNumbe) {
            this.lineNumbe = lineNumbe;
        }

        /**
         * 获取：联行号
         */
        public String getLineNumbe() {
            return lineNumbe;
        }
            /**
         * 设置：省
         */
        public void setProvince(String province) {
            this.province = province;
        }

        /**
         * 获取：省
         */
        public String getProvince() {
            return province;
        }
            /**
         * 设置：市
         */
        public void setCity(String city) {
            this.city = city;
        }

        /**
         * 获取：市
         */
        public String getCity() {
            return city;
        }
            /**
         * 设置：区
         */
        public void setArea(String area) {
            this.area = area;
        }

        /**
         * 获取：区
         */
        public String getArea() {
            return area;
        }
            /**
         * 设置：支行地址
         */
        public void setAddress(String address) {
            this.address = address;
        }

        /**
         * 获取：支行地址
         */
        public String getAddress() {
            return address;
        }
            /**
         * 设置：银行卡状态
         */
        public void setStatus(Integer status) {
            this.status = status;
        }

        /**
         * 获取：银行卡状态
         */
        public Integer getStatus() {
            return status;
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
