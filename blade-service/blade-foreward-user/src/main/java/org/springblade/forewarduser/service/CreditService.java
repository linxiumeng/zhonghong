package org.springblade.forewarduser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.common.entity.Credit;
import org.springblade.common.entity.UserEntity;

/**
 * 授信表(Credit)表服务接口
 *
 * @author linxiumeng
 * @since 2019-03-07 16:15:37
 */
public interface CreditService extends IService<Credit> {

    /**
     * 创建授信
     * @param credit
     * @param userEntity
     * @return
     */
    boolean createCredit(Credit credit, UserEntity userEntity);
    
}