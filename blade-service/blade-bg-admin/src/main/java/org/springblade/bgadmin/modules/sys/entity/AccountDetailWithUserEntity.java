package org.springblade.bgadmin.modules.sys.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 余额详情表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@Data
public class AccountDetailWithUserEntity extends AccountDetailEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private FuserEntity userEntity;

}
