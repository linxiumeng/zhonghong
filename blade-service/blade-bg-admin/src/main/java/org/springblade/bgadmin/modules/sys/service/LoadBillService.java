package org.springblade.bgadmin.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.bgadmin.modules.sys.entity.LoadBill;
import org.springblade.bgadmin.modules.sys.entity.LoadBillWithOrder;
import org.springblade.bgadmin.modules.sys.form.mybatis.LoadBillCondition;

/**
 * @author hanbin
 */
public interface LoadBillService extends IService<LoadBill> {


    LoadBillWithOrder getLoadBillByIdWithOrder(Integer id);

    IPage listLoadBillWithOrder(IPage page, LoadBillCondition condition);

    LoadBill selectByOrderId(Integer id);

}
