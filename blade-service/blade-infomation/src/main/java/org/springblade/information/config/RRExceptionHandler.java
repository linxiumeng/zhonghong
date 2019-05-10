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

package org.springblade.information.config;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springblade.common.exception.RRException;
import org.springblade.common.utils.R;
import org.springblade.core.log.error.BladeRestExceptionTranslator;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.log.publisher.ErrorLogPublisher;
import org.springblade.core.secure.exception.SecureException;
import org.springblade.core.tool.api.ResultCode;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.UrlUtil;
import org.springblade.core.tool.utils.WebUtil;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

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
        logger.error(e.getMessage(), e);
        return R.error();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public org.springblade.core.tool.api.R handleError(ConstraintViolationException e) {
       // log.warn("参数验证失败", e.getMessage());
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String path = ((PathImpl) violation.getPropertyPath()).getLeafNode().getName();
        String message = String.format("%s:%s", path, violation.getMessage());
        return org.springblade.core.tool.api.R.fail(ResultCode.PARAM_VALID_ERROR, message);
    }


    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public org.springblade.core.tool.api.R handleError(MissingServletRequestParameterException e) {
      //  log.warn("缺少请求参数", e.getMessage());
        String message = String.format("缺少必要的请求参数: %s", e.getParameterName());
        return org.springblade.core.tool.api.R.fail(ResultCode.PARAM_MISS, message);
    }

    @ExceptionHandler(org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public org.springblade.core.tool.api.R handleError(MethodArgumentTypeMismatchException e) {
     //   log.warn("请求参数格式错误", e.getMessage());
        String message = String.format("请求参数格式错误: %s", e.getName());
        return org.springblade.core.tool.api.R.fail(ResultCode.PARAM_TYPE_ERROR, message);
    }

    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public org.springblade.core.tool.api.R handleError(MethodArgumentNotValidException e) {
      //  log.warn("参数验证失败", e.getMessage());
        return handleError(e.getBindingResult());
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public org.springblade.core.tool.api.R handleError(BindException e) {
    //    log.warn("参数绑定失败", e.getMessage());
        return handleError(e.getBindingResult());
    }

    private org.springblade.core.tool.api.R handleError(BindingResult result) {
        FieldError error = result.getFieldError();
        String message = String.format("%s:%s", error.getField(), error.getDefaultMessage());
        return org.springblade.core.tool.api.R.fail(ResultCode.PARAM_BIND_ERROR, message);
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @Override
    public org.springblade.core.tool.api.R handleError(NoHandlerFoundException e) {
   //     log.error("404没找到请求:{}", e.getMessage());
        return org.springblade.core.tool.api.R.fail(ResultCode.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public org.springblade.core.tool.api.R handleError(HttpMessageNotReadableException e) {
    //    log.error("消息不能读取:{}", e.getMessage());
        return org.springblade.core.tool.api.R.fail(ResultCode.MSG_NOT_READABLE, e.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
   // @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @Override
    public org.springblade.core.tool.api.R handleError(HttpRequestMethodNotSupportedException e) {
  //      log.error("不支持当前请求方法:{}", e.getMessage());
        return org.springblade.core.tool.api.R.fail(ResultCode.METHOD_NOT_SUPPORTED, e.getMessage());
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @Override
    public org.springblade.core.tool.api.R handleError(HttpMediaTypeNotSupportedException e) {
    //    log.error("不支持当前媒体类型:{}", e.getMessage());
        return org.springblade.core.tool.api.R.fail(ResultCode.MEDIA_TYPE_NOT_SUPPORTED, e.getMessage());
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.OK)
    @Override
    public org.springblade.core.tool.api.R handleError(ServiceException e) {
     //   log.error("业务异常", e);
        return org.springblade.core.tool.api.R.fail(e.getResultCode(), e.getMessage());
    }

    @ExceptionHandler(SecureException.class)
   // @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @Override
    public org.springblade.core.tool.api.R handleError(SecureException e) {
    //    log.error("认证异常", e);
        return org.springblade.core.tool.api.R.fail(e.getResultCode(), e.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    @Override
   // @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public org.springblade.core.tool.api.R handleError(Throwable e) {
    //    log.error("服务器异常", e);
        //发送服务异常事件
        ErrorLogPublisher.publishEvent(e, UrlUtil.getPath(WebUtil.getRequest().getRequestURI()));
        return org.springblade.core.tool.api.R.fail(ResultCode.INTERNAL_SERVER_ERROR, (Func.isEmpty(e.getMessage()) ? ResultCode.INTERNAL_SERVER_ERROR.getMessage() : e.getMessage()));
    }



}
