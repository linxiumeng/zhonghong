package org.springblade.order.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;
import org.springblade.common.entity.Quotation;
import org.springblade.common.respond.QuotationResp;

import java.util.List;

/**
 * 报价单表
(Quotation)表数据库访问层
 *
 * @author linxiumeng
 * @since 2019-03-10 11:59:55
 */
 @Mapper
public interface QuotationDao extends BaseMapper<Quotation> {

 /**
  * 查詢
  * @param wrapper
  * @return
  */
 @Select("<script>" +
         "select quo.*,user.company_name as `provider_name`,user.contacts as `provider_contacts`,user.contact_number as `provider_contact_number`,user.contact_address as `provider_contact_address`,user.mail as `provider_mail`  from tb_quotation quo join tb_fuser user on quo.user_id = user.user_id" +
         "<where>${ew.sqlSegment}</where></script>")
 List<QuotationResp> selectQuotationRespList(@Param("ew") Wrapper wrapper);


 /**
  * 查詢
  * @param wrapper
  * @return
  */
 @Select("<script>" +
         "select * from tb_quotation a where create_date = (select max(create_date) from tb_quotation b where a.demand_id = b.demand_id and user_id = #{userId} )" +
         "<where>${ew.sqlSegment}</where></script>")
 List<Quotation> selectQuotationListByDemand(IPage rowBounds, @Param("ew") Wrapper wrapper, @Param("userId") Long userId);


 /**
  * 根据demandId查询列表
  * @param demandId 需求id
  * @return
  */
 @Select("select * from tb_quotation where demand_id = #{demandId}")
 List<Quotation> selectQuotationListByDemandId(@Param("demandId") Integer demandId);

}