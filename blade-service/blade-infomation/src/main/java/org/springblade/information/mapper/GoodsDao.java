package org.springblade.information.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springblade.common.entity.Goods;

import java.util.List;

/**
 * 商品表(Goods)表数据库访问层
 *
 * @author linxiumeng
 * @since 2019-02-13 15:23:01
 */
 @Mapper
public interface GoodsDao extends BaseMapper<Goods> {

    
    /**
     * 通过条件查询记录数
     *
     * @param tbGoods 查询条件
     * @return 记录数
     */
     int getTotal(Goods tbGoods);
    
    
    /**
     * 条件分页查询
     *
     * @param tbGoods 查询条件
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Goods> queryAllByLimit(@Param("param") Goods tbGoods, @Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tbGoods 实例对象
     * @return 对象列表
     */
    List<Goods> queryAll(Goods tbGoods);


    /**
     * 减库存
     * @param goodsId
     * @param amount
     * @return
     */
    @Update(" update tb_goods set goods_stock = goods_stock - #{amount} where id = #{goodsId} and (goods_stock - #{amount} ) >= 0 ")
    int decrGoodsStock(@Param("goodsId") long goodsId, @Param("amount") int amount);

}