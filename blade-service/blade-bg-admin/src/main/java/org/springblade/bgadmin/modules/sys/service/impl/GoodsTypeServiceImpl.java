package org.springblade.bgadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.finepetro.modules.sys.dao.GoodsTypeDao;
import io.finepetro.modules.sys.entity.GoodsTypeEntity;
import io.finepetro.modules.sys.service.GoodsTypeService;
import org.springframework.stereotype.Service;


@Service("goodsTypeService")
public class GoodsTypeServiceImpl extends ServiceImpl<GoodsTypeDao, GoodsTypeEntity> implements GoodsTypeService {


}
