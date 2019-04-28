package org.springblade.bgadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.bgadmin.modules.sys.mapper.GoodsTypeDao;
import org.springblade.bgadmin.modules.sys.entity.GoodsTypeEntity;
import org.springblade.bgadmin.modules.sys.service.GoodsTypeService;
import org.springframework.stereotype.Service;


@Service("goodsTypeService")
public class GoodsTypeServiceImpl extends ServiceImpl<GoodsTypeDao, GoodsTypeEntity> implements GoodsTypeService {


}
