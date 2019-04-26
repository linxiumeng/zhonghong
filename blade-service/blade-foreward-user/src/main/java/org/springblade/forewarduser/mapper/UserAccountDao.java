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

package org.springblade.forewarduser.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.session.RowBounds;
import org.springblade.common.entity.UserAccount;

import java.util.List;

/**
 * 用户
 *
 * @author hanbin
 */
@Mapper
public interface UserAccountDao extends BaseMapper<UserAccount> {


    /**
     * 分页查询实体
     * @param rowBounds 分页
     * @param wrapper 包装条件
     * @return 包装实体
     */
    @Select("<script>select * from tb_fuser <where>${ew.sqlSegment}</where></script>")
    @Results({
            @Result(id = true,column = "user_id",property = "userId"),
            @Result(column = "mobile",property = "mobile"),
            @Result(column = "password",property = "password"),
            @Result(column = "unified_social_code",property = "unifiedSocialCode"),
            @Result(column = "legal_person",property = "legalPerson"),
            @Result(column = "card1",property = "card1"),
            @Result(column = "card2",property = "card2"),
            @Result(column = "business_licence",property = "businessLicence"),
            @Result(column = "contacts",property = "contacts"),
            @Result(column = "contact_number",property = "contactNumber"),
            @Result(column = "contact_address",property = "contactAddress"),
            @Result(column = "credit_status",property = "creditStatus"),
            @Result(column = "status",property = "status"),
            @Result(column = "register_date",property = "registerDate"),
            @Result(column = "type",property = "type"),
            @Result(column = "companyName",property = "companyName"),
            @Result(column = "mail",property = "mail"),
            @Result(column = "provider_status",property = "providerStatus"),
            @Result(column="user_id",property="account",one=@One(select="io.finepetro.dao.AccountDao.selectAccountByUserId",fetchType= FetchType.EAGER))
    })
    List<UserAccount> selectListWithAccount(IPage iPage, @Param("ew") Wrapper wrapper);

}
