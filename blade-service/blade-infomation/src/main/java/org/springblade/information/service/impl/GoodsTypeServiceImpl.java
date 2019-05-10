package org.springblade.information.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.common.entity.GoodsTypeEntity;
import org.springblade.information.mapper.GoodsTypeDao;
import org.springblade.information.service.GoodsTypeService;
import org.springframework.stereotype.Service;


@Service("goodsTypeService")
public class GoodsTypeServiceImpl extends ServiceImpl<GoodsTypeDao, GoodsTypeEntity> implements GoodsTypeService {


}
