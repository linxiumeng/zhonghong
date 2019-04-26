package org.springblade.bgadmin.modules.sys.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springblade.bgadmin.modules.sys.entity.LoadBill;
import org.springblade.bgadmin.modules.sys.entity.LoadBillWithOrder;
import org.springblade.bgadmin.modules.sys.form.mybatis.LoadBillCondition;

import java.util.List;

/**
 * @author hanbin
 * 提货单
 */
public interface LoadBillDao extends BaseMapper<LoadBill> {

    LoadBillWithOrder selectByIdWithOrder(Integer id);

    List<LoadBill> selectListWithOrder(IPage iPage, @Param("condition") LoadBillCondition condition);

    LoadBill selectByOrderId(Integer orderId);
}
