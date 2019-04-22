package org.springblade.information.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.common.entity.Goods;
import org.springblade.common.enums.goods.GoodsAuditStatusEnum;
import org.springblade.common.enums.goods.GoodsStatusEnum;
import org.springblade.common.form.PageForm;
import org.springblade.information.mapper.GoodsDao;
import org.springblade.information.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 商品表(TbGoods)表服务实现类
 *
 * @author linxiumeng
 * @since 2019-02-13 15:23:01
 */
@Service("goodsService")
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, Goods> implements GoodsService {

    @Resource
    GoodsDao goodsDao;

    /**
     * 删减商品库存
     *
     * @param goodsId
     * @param amount
     * @return
     */
    @Override
    public boolean decrGoodsStock(long goodsId, int amount) {
        return amount > 0 && goodsDao.decrGoodsStock(goodsId, amount) > 0;
    }

    /**
     * 返回上线的产品分页
     *
     * @return
     */
    @Override
    public IPage<Goods> listGoodsByOnline(PageForm pageForm) {

        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        //审核通过 + 上架 + 创建时间倒序排列
        wrapper.eq(Goods.GOODS_STATUS_COLUMN, GoodsStatusEnum.ON.getCode()).
                eq(Goods.GOODS_AUDIT_STATUS_COLUMN, GoodsAuditStatusEnum.OK.getCode()).
                orderBy(true,false,Goods.CREATE_TIME_COLUMN);
        Page<Goods> page = new Page<>(pageForm.getPage(), pageForm.getSize());
        return this.page(page, wrapper);
    }

    @Override
    public IPage<Goods> listGoodsByUserId(PageForm pageForm, Long userId) {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        // 过滤用户id + 创建时间倒叙
        wrapper.eq(Goods.USER_ID_COLUMN, userId).orderBy(true,false,Goods.CREATE_TIME_COLUMN);
        Page<Goods> page = new Page<>(pageForm.getPage(), pageForm.getSize());
        return this.page(page, wrapper);
    }

    @Override
    public boolean updateGoodsByIdAndUserId(Goods goods, Long userId) {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.eq(Goods.USER_ID_COLUMN, userId).eq(Goods.ID_COLUMN, goods.getId());
        //修改之后需要将认证状态修改成默认 然后在审核一遍
        goods.setAuditStatus(GoodsAuditStatusEnum.DEFAULT);
        return this.update(goods, wrapper);
    }

    @Override
    public boolean updateGoodsStatusByIdAndUserId(GoodsStatusEnum goodsStatusEnum, Long goodsId, Long userId) {
        Goods goods = new Goods();
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.eq(Goods.USER_ID_COLUMN, userId).eq(Goods.ID_COLUMN, goodsId);
        goods.setGoodsStatus(goodsStatusEnum);
        return this.update(goods, wrapper);
    }

    @Override
  //  @Cacheable(value = "goods", key = "#goodsId", unless = "#result == null")
    public Goods getGoodsFromCache(Long goodsId) {
        return this.getById(goodsId);
    }
}