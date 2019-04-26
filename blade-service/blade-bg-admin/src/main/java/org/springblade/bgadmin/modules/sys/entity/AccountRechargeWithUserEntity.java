package org.springblade.bgadmin.modules.sys.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountRechargeWithUserEntity extends AccountRechargeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private FuserEntity userEntity;


}
