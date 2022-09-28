package com.example.demo.utils;

import org.apache.commons.lang.StringUtils;

/**
 * @className:关键信息脱敏工具类
 * @author:hhs
 * @date:2021-07-07 16:51
 */
public class DesensitizedUtil {

    /**
     * 地址脱敏
     *
     * @param address
     * @return
     */
    public static String desensitizedAddress(String address) {
        if (!StringUtils.isNotEmpty(address)) {
            return StringUtils.left(address, 3).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(address, address.length() - 11), StringUtils.length(address), "*"), "***"));
        }
        return address;
    }

    /**
     * 地址脱敏v2
     *
     * @param address
     * @return
     */
    public static String desensitizedAddressV2(String address) {
        return address.replaceAll(String.format("[0-9]"), "*");
    }

    /**
     * 电话脱敏
     */
    public static String desensitizedPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() < 11) {
            return phoneNumber.replaceAll("(\\w{2})\\w*(\\w{3})", "$1****$2");
        }
        if (StringUtils.isNotEmpty(phoneNumber)) {
            phoneNumber = phoneNumber.replaceAll("(\\w{3})\\w*(\\w{4})", "$1****$2");
        }
        return phoneNumber;
    }

    /**
     * 姓名脱敏
     */
    public static String desensitizedName(String name) {
        String retName = name.charAt(0) + "";
        if (StringUtils.isNotEmpty(name)) {
            for (int i = 1; i < name.length(); i++) {
                retName += "*";
            }
        }
        return retName;
    }

    public static void main(String[] args) {
        String s = desensitizedAddressV2("address_desc -> {TextNode@13407} \"\"重庆市江北区石马河街道城区宏帆曦城2栋13层13-8\"\"");
        System.out.println(s);
    }
}

