package org.springblade.bgadmin.modules.sys.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springblade.bgadmin.modules.sys.entity.AccountRepaymentDetailLogEntity;

import java.util.List;

/**
 * 流水表操作
 *
 * @author hanbin
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
public interface AccountRepaymentDetailLogDao extends BaseMapper<AccountRepaymentDetailLogEntity> {


    List<AccountRepaymentDetailLogEntity> selectAccountRepaymentDetailLogList(IPage iPage, @Param("ew") Wrapper wrapper);
}
