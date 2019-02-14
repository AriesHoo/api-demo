package com.aries.api.demo.util;

import java.util.regex.Pattern;

/**
 * @Author: AriesHoo on 2018/7/23 13:10
 * @E-Mail: AriesHoo@126.com
 * Function: 正则表达式相关工具
 * Description:
 * 1、2018-7-23 13:11:57 新增正则判断
 */
public class ValidatorUtil {

    /**
     * 大小写字母模式
     */
    public static final String REGEX_USERNAME = "^(?![a-z0-9]+$)(?![A-Z0-9]+$)[0-9A-Za-z]{5,20}$";

    /**
     * 正则表达式-验证密码-必须包含大小写字母+数字组合
     */
    public static final String REGEX_PASSWORD = "^(?![a-z0-9]+$)(?![A-Z0-9]+$)(?![A-Za-z]+$)[a-zA-Z0-9]{6,18}$";
    /**
     * 筛选过滤表情
     */
    public static final String REGEX_EMOJI = "^(?![a-z0-9]+$)(?![A-Z0-9]+$)(?![A-Za-z]+$)[a-zA-Z0-9]{6,18}$";
    /**
     * 正则：手机号（精确）截止2018-8月
     * <p>移动：134(0-8)、135、136、137、138、139、147、150、151、152、157、158、159、178、182、183、184、187、188、198</p>
     * <p>联通：130、131、132、145、155、156、175、176、185、186、166</p>
     * <p>电信：133、153、173、177、180、181、189、199</p>
     * <p>全球星：1349</p>
     * <p>虚拟运营商：170</p>
     */
    public static final String REGEX_MOBILE = "^1[3-9][0-9]\\d{8}$";

    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 正则表达式：验证汉字
     */
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";

    /**
     * 正则表达式：验证IP地址
     */
    public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";


    /**
     * 检查表情
     *
     * @param str
     * @return
     */
    public static boolean isEmoji(CharSequence str) {
        return Pattern.matches(REGEX_EMOJI, str);
    }

    /**
     * 校验用户名
     *
     * @param username
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUsername(String username) {
        return Pattern.matches(REGEX_USERNAME, username);
    }

    /**
     * 校验密码
     *
     * @param password
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }

    /**
     * 校验手机号
     *
     * @param value
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String value) {
        if (value != null) {
            return Pattern.matches(REGEX_MOBILE, value);
        }
        return false;
    }

    /**
     * 校验邮箱
     *
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

    /**
     * 校验汉字
     *
     * @param chinese
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }

    /**
     * 校验IP地址
     *
     * @param ipAddr
     * @return
     */
    public static boolean isIPAddr(String ipAddr) {
        return Pattern.matches(REGEX_IP_ADDR, ipAddr);
    }

}
