package org.springblade.pay.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springblade.common.entity.AccountRepayment;


/**
 * 分歧还款表(TbAccountRepayment)表数据库访问层
 *
 * @author linxiumeng
 * @since 2019-02-18 11:59:29
 */
@Mapper
public interface AccountRepaymentDao extends BaseMapper<AccountRepayment> {

    /**
     * 回填主键的insert
     *
     * @param accountRepayment
     * @return
     */
    @Insert("<script>" +
            "insert into tb_account_repayment\n" +
            "    <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\n" +
            "      <if test=\"id != null\">\n" +
            "        id,\n" +
            "      </if>\n" +
            "      <if test=\"userId != null\">\n" +
            "        user_id,\n" +
            "      </if>\n" +
            "      <if test=\"orderId != null\">\n" +
            "        order_id,\n" +
            "      </if>\n" +
            "      <if test=\"totalAmount != null\">\n" +
            "        total_amount,\n" +
            "      </if>\n" +
            "      <if test=\"waitAmount != null\">\n" +
            "        wait_amount,\n" +
            "      </if>\n" +
            "      <if test=\"paidAmount != null\">\n" +
            "        paid_amount,\n" +
            "      </if>\n" +
            "      <if test=\"currentPeriod != null\">\n" +
            "        current_period,\n" +
            "      </if>\n" +
            "      <if test=\"totalInterest != null\">\n" +
            "        total_interest,\n" +
            "      </if>\n" +
            "      <if test=\"waitInterest != null\">\n" +
            "        wait_interest,\n" +
            "      </if>\n" +
            "      <if test=\"paidInterest != null\">\n" +
            "        paid_interest,\n" +
            "      </if>\n" +
            "      <if test=\"periods != null\">\n" +
            "        periods,\n" +
            "      </if>\n" +
            "      <if test=\"recentRepaymentDate != null\">\n" +
            "        recent_repayment_date,\n" +
            "      </if>\n" +
            "      <if test=\"createDate != null\">\n" +
            "        create_date,\n" +
            "      </if>\n" +
            "      <if test=\"updateDate != null\">\n" +
            "        update_date,\n" +
            "      </if>\n" +
            "    </trim>\n" +
            "    <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">\n" +
            "      <if test=\"id != null\">\n" +
            "        #{id,jdbcType=BIGINT},\n" +
            "      </if>\n" +
            "      <if test=\"userId != null\">\n" +
            "        #{userId,jdbcType=BIGINT},\n" +
            "      </if>\n" +
            "      <if test=\"orderId != null\">\n" +
            "        #{orderId,jdbcType=INTEGER},\n" +
            "      </if>\n" +
            "      <if test=\"totalAmount != null\">\n" +
            "        #{totalAmount,jdbcType=DECIMAL},\n" +
            "      </if>\n" +
            "      <if test=\"waitAmount != null\">\n" +
            "        #{waitAmount,jdbcType=DECIMAL},\n" +
            "      </if>\n" +
            "      <if test=\"paidAmount != null\">\n" +
            "        #{paidAmount,jdbcType=DECIMAL},\n" +
            "      </if>\n" +
            "      <if test=\"currentPeriod != null\">\n" +
            "        #{currentPeriod,jdbcType=INTEGER},\n" +
            "      </if>\n" +
            "      <if test=\"totalInterest != null\">\n" +
            "        #{totalInterest,jdbcType=DECIMAL},\n" +
            "      </if>\n" +
            "      <if test=\"waitInterest != null\">\n" +
            "        #{waitInterest,jdbcType=DECIMAL},\n" +
            "      </if>\n" +
            "      <if test=\"paidInterest != null\">\n" +
            "        #{paidInterest,jdbcType=DECIMAL},\n" +
            "      </if>\n" +
            "      <if test=\"periods != null\">\n" +
            "        #{periods,jdbcType=INTEGER},\n" +
            "      </if>\n" +
            "      <if test=\"recentRepaymentDate != null\">\n" +
            "        #{recentRepaymentDate,jdbcType=TIMESTAMP},\n" +
            "      </if>\n" +
            "      <if test=\"createDate != null\">\n" +
            "        #{createDate,jdbcType=TIMESTAMP},\n" +
            "      </if>\n" +
            "      <if test=\"updateDate != null\">\n" +
            "        #{updateDate,jdbcType=TIMESTAMP},\n" +
            "      </if>\n" +
            "    </trim>" +
            "</script>")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertWithId(AccountRepayment accountRepayment);


}