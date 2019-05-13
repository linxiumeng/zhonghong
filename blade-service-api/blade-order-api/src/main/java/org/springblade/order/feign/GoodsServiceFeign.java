package org.springblade.order.feign;

import org.springblade.common.entity.Goods;
import org.springblade.common.entity.GoodsTypeEntity;
import org.springblade.common.entity.UserEntity;
import org.springblade.core.tool.api.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

/**
 * @author hanbin
 */
@FeignClient(
        value = "blade-information"
)
public interface GoodsServiceFeign {


    String GOODS_API_PREFIX = "/api/goods";

    /**
     * 获取用户详情
     * @param id
     * @return
     */
    @GetMapping(GOODS_API_PREFIX + "/detail")
    R<Goods> getGoodsById(@RequestParam("goodsId") Long id);

    /**
     * 删减库存
     * @param goodsId
     * @param count
     * @return
     */
    @GetMapping(GOODS_API_PREFIX+"/decr_goods_stock")
    R<Boolean> decrGoodsStock(@RequestParam("goodsId")Long goodsId,@RequestParam("count")Integer count);

    /**
     * 获取用户详情
     * @param typeIds
     * @return
     */
    @GetMapping(GOODS_API_PREFIX + "/batchGetListByIds")
    R<Collection<GoodsTypeEntity>> batchGetGoodsType(@RequestParam("ids") Collection<Long> typeIds);

    /**
     * 增加库存
     * @param goodsId
     * @param count
     * @return
     */
    @GetMapping(GOODS_API_PREFIX+"/incr_goods_stock")
    R<Boolean> incrGoodsStock(@RequestParam("goodsId")Long goodsId,@RequestParam("count")Integer count);

}
