package org.springblade.bgadmin.modules.sys;


import io.finepetro.modules.sys.form.BaseForm;
import org.apache.commons.lang.StringUtils;

public class CheckBGListUtils {

    public static void check(Wrapper wrapper ,BaseForm baseForm, String dateString, String... keywords){

        if (baseForm.getStartDate() != null) {
            wrapper.gt(dateString, baseForm.getStartDate());
        }

        if (baseForm.getEndDate() != null) {
            wrapper.lt(dateString, baseForm.getEndDate());
        }

        if (StringUtils.isNotBlank(baseForm.getKeywords())) {
            wrapper.andNew().eq(keywords[0], baseForm.getKeywords()).or().eq(keywords[1], baseForm.getKeywords());
        }
    }
}
