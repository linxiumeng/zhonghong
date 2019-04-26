package org.springblade.bgadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.finepetro.modules.sys.dao.LoadBillDao;
import io.finepetro.modules.sys.entity.LoadBill;
import io.finepetro.modules.sys.entity.LoadBillWithOrder;
import io.finepetro.modules.sys.form.mybatis.LoadBillCondition;
import io.finepetro.modules.sys.service.LoadBillService;
import org.springframework.stereotype.Service;

/**
 * @author hanbin
 *
 */
@Service
public class LoadBillServiceImpl extends ServiceImpl<LoadBillDao,LoadBill> implements LoadBillService{
    @Override
    public LoadBillWithOrder getLoadBillByIdWithOrder(Integer id) {
        return baseMapper.selectByIdWithOrder(id);
    }

    @Override
    public Page listLoadBillWithOrder(Page page, LoadBillCondition condition) {
        page.setRecords(baseMapper.selectListWithOrder(page,condition));
        return page;
    }

    @Override
    public LoadBill selectByOrderId(Integer id) {
        return baseMapper.selectByOrderId(id);
    }


}
