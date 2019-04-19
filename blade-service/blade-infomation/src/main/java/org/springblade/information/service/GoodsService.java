package org.springblade.information.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.common.entity.Goods;
import org.springblade.common.enums.goods.GoodsStatusEnum;
import org.springblade.common.form.PageForm;

/**
 * 商品表(TbGoods)表服务接口
 *
 * @author linxiumeng
 * @since 2019-02-13 15:23:01
 */
public interface GoodsService extends IService<Goods> {

    /**
     * 删减库存
     * @param goodsId
     * @param amount
     * @return
     */
    boolean decrGoodsStock(long goodsId, int amount);

    /**
     * 返回上线的产品列表
     * @param pageForm
     * @return
     */
    IPage<Goods> listGoodsByOnline(PageForm pageForm);

    /**
     * 返回用户自己上线的产品（供应商用）
     * @param pageForm
     * @param userId
     * @return
     */
    IPage<Goods> listGoodsByUserId(PageForm pageForm, Long userId);

    /**
     * 修改商品属性（这里只能让拥有者修改属性）
     * @param goods
     * @param userId
     * @return
     */
    boolean updateGoodsByIdAndUserId(Goods goods, Long userId);

    /**
     * 这里仅允许修改产品的状态
     * @param goodsStatusEnum
     * @param goodsId
     * @param userId
     * @return
     */
    boolean updateGoodsStatusByIdAndUserId(GoodsStatusEnum goodsStatusEnum, Long goodsId, Long userId);

    /**
     * 从缓存中获取实体
     * @param goodsId
     * @return
     */
    Goods getGoodsFromCache(Long goodsId);

}