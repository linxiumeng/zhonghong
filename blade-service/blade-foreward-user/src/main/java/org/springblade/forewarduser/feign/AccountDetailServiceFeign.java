package org.springblade.forewarduser.feign;

import org.springblade.common.entity.AccountDetail;
import org.springblade.core.launch.constant.AppConstant;
import org.springblade.core.tool.api.R;
import org.springblade.desk.entity.Notice;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * accountdetail Feign接口类
 *
 * @author Chill
 */
@FeignClient(
        value = "blade-pay"
)
public interface AccountDetailServiceFeign {


    @PostMapping("/accountDetail/saveAccountDetail")
    R<Boolean> save(AccountDetail detail);



}
