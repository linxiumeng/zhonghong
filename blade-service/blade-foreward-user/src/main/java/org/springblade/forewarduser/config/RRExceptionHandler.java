/**
 * Copyright 2018 首页 http://www.finepetro.com
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.springblade.forewarduser.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springblade.common.exception.RRException;
import org.springblade.common.utils.R;
import org.springblade.core.log.error.BladeRestExceptionTranslator;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年10月27日 下午10:16:19
 */
@Primary
@RestControllerAdvice
public class RRExceptionHandler extends BladeRestExceptionTranslator {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(RRException.class)
    public R handleRRException(RRException e) {
        R r = new R();
        r.put("code", e.getCode());
        r.put("msg", e.getMessage());
        return r;
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public R handleDuplicateKeyException(DuplicateKeyException e) {
        logger.error(e.getMessage(), e);
        return R.error("数据库中已存在该记录");
    }

    @ExceptionHandler(Exception.class)
    public R handleException(Exception e) {
        logger.error("服务器出现异常，请联系管理员", e);
        return R.error();
    }
}
