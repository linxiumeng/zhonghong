package org.springblade.forewarduser.feign;

import org.springblade.common.entity.AccountDetail;
import org.springblade.core.launch.constant.AppConstant;
import org.springblade.core.tool.api.R;
import org.springblade.desk.entity.Notice;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * accountdetail Feign接口类
 *
 * @author Chill
 */
@RestController
public class AccountDetailServiceFeignImpl implements AccountDetailServiceFeign  {


    @Override
    @PostMapping("/accountDetail/saveAccountDetail")
    public R<Boolean> save(AccountDetail detail){
        return null;
    }



}
