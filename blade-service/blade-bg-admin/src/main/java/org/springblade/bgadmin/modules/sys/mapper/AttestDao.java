package org.springblade.bgadmin.modules.sys.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springblade.bgadmin.modules.sys.entity.AttestEntity;

/**
 * 企业认证信息表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
public interface AttestDao extends BaseMapper<AttestEntity> {

    /**
     * 根据用户id查询实体
     * @param userId
     * @return
     */
    AttestEntity selectByUserId(Integer userId);

}
