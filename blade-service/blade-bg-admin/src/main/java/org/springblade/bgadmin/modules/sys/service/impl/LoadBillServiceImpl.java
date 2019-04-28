package org.springblade.bgadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.bgadmin.modules.sys.mapper.LoadBillDao;
import org.springblade.bgadmin.modules.sys.entity.LoadBill;
import org.springblade.bgadmin.modules.sys.entity.LoadBillWithOrder;
import org.springblade.bgadmin.modules.sys.form.mybatis.LoadBillCondition;
import org.springblade.bgadmin.modules.sys.service.LoadBillService;
import org.springframework.stereotype.Service;

/**
 * @author hanbin
 *
 */
@Service
public class LoadBillServiceImpl extends ServiceImpl<LoadBillDao, LoadBill> implements LoadBillService {
    @Override
    public LoadBillWithOrder getLoadBillByIdWithOrder(Integer id) {
        return baseMapper.selectByIdWithOrder(id);
    }

    @Override
    public IPage listLoadBillWithOrder(IPage page, LoadBillCondition condition) {
        page.setRecords(baseMapper.selectListWithOrder(page,condition));
        return page;
    }

    @Override
    public LoadBill selectByOrderId(Integer id) {
        return baseMapper.selectByOrderId(id);
    }


}
