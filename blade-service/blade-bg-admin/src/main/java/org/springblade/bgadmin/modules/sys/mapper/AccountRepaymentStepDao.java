package org.springblade.bgadmin.modules.sys.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springblade.bgadmin.modules.sys.entity.AccountRepaymentStepEntity;
import org.springblade.bgadmin.modules.sys.entity.UserAccountRepaymentStepEntity;
import org.springblade.bgadmin.modules.sys.form.mybatis.AccountRepaymentStepDaoCondition;

import java.util.List;

/**
 * 分期还款详情表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
public interface AccountRepaymentStepDao extends BaseMapper<AccountRepaymentStepEntity> {


    /**
     * 查询分期还款的列表
     * @return
     */
    List<UserAccountRepaymentStepEntity> selectUserAccountRepaymentStepsList(IPage iPage, @Param("condition") AccountRepaymentStepDaoCondition condition);

    List<AccountRepaymentStepEntity> selectAccountRepaymentStepList(Integer repaymentId);

    int selectOverDeadLineCount(Integer repaymentId);

    int selectAlreadyPayCount(Integer repaymentId);

}
