package org.springblade.forewarduser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springblade.common.entity.Account;

/**
 * 账户系统
 *
 * @author hanbin
 */
 @Mapper
public interface AccountDao extends BaseMapper<Account> {

 /**
  * 根据用户id获取account实体
  * @param userId 用户id
  * @return account实体
  */
 @Select("select * from tb_account where user_id = #{userId}")
  Account selectAccountByUserId(@Param("userId") Integer userId);

}