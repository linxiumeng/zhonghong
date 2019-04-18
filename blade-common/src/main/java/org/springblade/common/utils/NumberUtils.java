/**
 * Copyright (C), 2019-2019, 上海诺奚有限公司
 * FileName: NumberUtils
 * Author:   nuoee
 * Date:     2019/1/29 11:00
 * Description: 数字工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.springblade.common.utils;

import java.util.Random;

/**
 * 〈一句话功能简述〉<br>
 * 〈数字工具类〉
 *
 * @author nuoee
 * @create 2019/1/29
 * @since 1.0.0
 */
public class NumberUtils {

    public static String getRandCode() {
        String sources = "0123456789";
        Random rand = new Random();
        StringBuffer flag = new StringBuffer();
        for (int j = 0; j < 6; j++) {
            flag.append(sources.charAt(rand.nextInt(9)) + "");
        }
        return flag.toString();
    }

}
