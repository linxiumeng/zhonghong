package org.springblade.order.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.session.RowBounds;
import org.springblade.common.entity.Demand;
import org.springblade.common.respond.DemandResp;

import java.util.List;

/**
 * (Demand)表数据库访问层
 *
 * @author linxiumeng
 * @since 2019-03-07 10:57:43
 */
@Mapper
public interface DemandDao extends BaseMapper<Demand> {

    @Select("<script>select * from tb_demand <where>${ew.sqlSegment}</where></script>")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "creat_userid", property = "creatUserid"),
            @Result(column = "f_type", property = "fType"),
            @Result(column = "f_name", property = "fName"),
            @Result(column = "f_unit", property = "fUnit"),
            @Result(column = "num", property = "num"),
            @Result(column = "trade_clause", property = "tradeClause"),
            @Result(column = "payment_by", property = "paymentBy"),
            @Result(column = "pricing_manner", property = "pricingManner"),
            @Result(column = "contractual_value_date", property = "contractualValueDate"),
            @Result(column = "premiums_discounts", property = "premiumsDiscounts"),
            @Result(column = "pay_day", property = "payDay"),
            @Result(column = "deadline", property = "deadline"),
            @Result(column = "remark", property = "remark"),
            @Result(column = "place_of_origin", property = "placeOfOrigin"),
            @Result(column = "oil_type", property = "oilType"),
            @Result(column = "api", property = "api"),
            @Result(column = "sulphur_content", property = "sulphurContent"),
            @Result(column = "acid_value", property = "acidValue"),
            @Result(column = "carbon_residue", property = "carbonResidue"),
            @Result(column = "nickel", property = "nickel"),
            @Result(column = "vanadium", property = "vanadium"),
            @Result(column = "shrink", property = "shrink"),
            @Result(column = "creat_time", property = "creatTime"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "creat_userid", property = "creatUserid"),
         //   @Result(column = "creat_userid", property = "createUser", one = @One(select = "io.finepetro.dao.UserDao.selectById", fetchType = FetchType.EAGER)),
            @Result(column = "id", property = "quotationList", many = @Many(select = "io.finepetro.dao.QuotationDao.selectQuotationListByDemandId", fetchType = FetchType.EAGER))


    })
    List<DemandResp> selectDemandListWithQuotationList(IPage iPage, @Param("ew") Wrapper wrapper);

}