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
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;
import org.springblade.common.entity.UserAccount;
import org.springblade.common.entity.UserEntity;

import java.util.List;

/**
 * 用户
 *
 * @author hanbin
 */
public interface UserDao extends BaseMapper<UserEntity> {


    /**
     * 获取连表查询的数据
     *
     * @param page
     * @param wrapper
     * @return
     */
    @Select("SELECT account.*,user.mobile,user.company_name,user.credit_status,user.contacts,user.contact_number,legal_person FROM tb_account account JOIN tb_fuser user ON user.user_id = account.user_id")
    List<UserAccount> listUserAccount(Page page, Wrapper wrapper);

    /**
     * 查询供应商
     * <p>
     * sql ：
     * SELECT account.*,user.mobile,user.company_name,user.credit_status,user.contacts,user.contact_number,legal_person,
     * <p>
     * (select count(1) from tb_goods where user_id = user.user_id) as `upper_good_count`
     * <p>
     * FROM tb_account account JOIN tb_fuser user ON user.user_id = account.user_id
     *
     * @param var1
     * @param var2
     * @return
     */
    @Select("<script>" +
            "SELECT account.*,user.mobile,user.company_name,user.credit_status,user.contacts,user.contact_number,legal_person, \n" +
            "\n" +
            "(select count(1) from tb_goods where user_id = user.user_id) as `upper_good_count`\n" +
            "\n" +
            "FROM tb_account account JOIN tb_fuser user ON user.user_id = account.user_id" +
            " <where>\n" +
            "  ${ew.sqlSegment}\n" +
            " </where>" +
            "</script>")
    List<UserAccount> listProvider(RowBounds var1, @Param("ew") Wrapper var2);

    /**
     * 查询采购商
     *
     * @param var1
     * @param var2
     * @return
     */
    @Select("<script>" +
            "SELECT account.*,user.mobile,user.company_name,user.credit_status,user.contacts,user.contact_number,legal_person, 0 as overdue_count \n" +
            "FROM tb_account account JOIN tb_fuser user ON user.user_id = account.user_id " +
            " <where>\n" +
            "  ${ew.sqlSegment}\n" +
            " </where>" +
            "</script>")
    List<UserAccount> listPurchase(RowBounds var1, @Param("ew") Wrapper var2);

}
