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

package org.springblade.common.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登录表单
 *
 * @author Mark sunlightcs@gmail.com
 * @since 3.1.0 2018-01-25
 */
@Data
public class LoginForm {

    @NotBlank(message = "手机号不能为空")
    private String mobile;


    @NotBlank(message = "密码不能为空")
    private String password;


//    @NotBlank(message = "账号类型不能为空")
    private Integer type;


 //   @NotBlank(message = "验证码不能为空")
    private String captcha;

    @NotBlank(message = "标记码为空")
    private String markCode;
}
