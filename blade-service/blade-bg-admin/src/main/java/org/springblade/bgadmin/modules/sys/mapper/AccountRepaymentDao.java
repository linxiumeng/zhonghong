package org.springblade.bgadmin.modules.sys.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springblade.bgadmin.modules.sys.entity.AccountRepaymentEntity;
import org.springblade.bgadmin.modules.sys.entity.AccountRepaymentWithStepEntity;
import org.springblade.bgadmin.modules.sys.entity.FuserEntity;

import java.util.List;

/**
 * 分期还款表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
public interface AccountRepaymentDao extends BaseMapper<AccountRepaymentEntity> {


    FuserEntity selectByRepaymentId(Integer id);

    AccountRepaymentWithStepEntity selectByOrderIdWithStep(Integer id);

    List<AccountRepaymentWithStepEntity> selectByOrderIdWithStepList(IPage page, @Param("ew") Wrapper wrapper);

}
