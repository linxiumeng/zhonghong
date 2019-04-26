package org.springblade.bgadmin.modules.sys.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springblade.bgadmin.modules.sys.entity.QuotationEntity;

/**
 * 报价单表

 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:04
 */
public interface QuotationDao extends BaseMapper<QuotationEntity> {


    /**
     * 根据需求单id查询报价单个数
     * @param demandId
     * @return
     */
    int selectQuotationCountByDemandId(Integer demandId);

}
