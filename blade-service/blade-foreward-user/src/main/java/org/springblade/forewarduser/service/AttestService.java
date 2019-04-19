package org.springblade.forewarduser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.common.entity.Attest;

/**
 * (Attest)表服务接口
 *
 * @author linxiumeng
 * @since 2019-03-07 16:00:29
 */
public interface AttestService extends IService<Attest> {

    /**
     * 创建认证
     * @param attest
     * @return
     */
    boolean createAttest(Attest attest);
    
}