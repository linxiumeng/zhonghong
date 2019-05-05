package org.springblade.bgadmin.modules.sys;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang.StringUtils;
import org.springblade.bgadmin.modules.sys.form.BaseForm;

public class CheckBGListUtils {

    public static <T> void check(QueryWrapper<T> wrapper , BaseForm baseForm, String dateString, String... keywords){

        if (baseForm.getStartDate() != null) {
            wrapper.gt(dateString, baseForm.getStartDate());
        }

        if (baseForm.getEndDate() != null) {
            wrapper.lt(dateString, baseForm.getEndDate());
        }

        if (StringUtils.isNotBlank(baseForm.getKeywords())) {
        //    wrapper.andNew().eq(keywords[0], baseForm.getKeywords()).or().eq(keywords[1], baseForm.getKeywords());
            wrapper.and(i->i.eq(keywords[0], baseForm.getKeywords()).or().eq(keywords[1], baseForm.getKeywords()));
        }
    }
}
