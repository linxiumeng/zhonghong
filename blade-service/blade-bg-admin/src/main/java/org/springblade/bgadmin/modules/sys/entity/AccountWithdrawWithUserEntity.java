package org.springblade.bgadmin.modules.sys.entity;

import lombok.Data;

import java.io.Serializable;


@Data
public class AccountWithdrawWithUserEntity extends AccountWithdrawEntity implements Serializable {

    private FuserEntity userEntity;

}
