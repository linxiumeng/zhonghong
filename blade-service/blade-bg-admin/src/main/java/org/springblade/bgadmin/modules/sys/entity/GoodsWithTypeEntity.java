package org.springblade.bgadmin.modules.sys.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class GoodsWithTypeEntity extends GoodsEntity implements Serializable {

    private GoodsTypeEntity goodsTypeEntity;

}
