package com.gblau.webx.web.module.tools.common;

import com.alibaba.citrus.service.pull.impl.PullServiceImpl;

/**
 * @author gblau
 * @date 2017-05-31
 */
public class CommonTools extends PullServiceImpl {

    private static String domain;			// 本服务域名
    private static String jsAndCssDomain;	// js 和 css 服务域名
    private static String imageDomian;		// 图片 服务域名
    private static boolean ifAliYun;		// 是否是阿里云服务器


    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        CommonTools.domain = domain;
    }

    public String getJsAndCssDomain() {
        return jsAndCssDomain;
    }

    public void setJsAndCssDomain(String jsAndCssDomain) {
        CommonTools.jsAndCssDomain = jsAndCssDomain;
    }

    public String getImageDomian() {
        return imageDomian;
    }

    public void setImageDomian(String imageDomian) {
        CommonTools.imageDomian = imageDomian;
    }

    public static boolean isIfAliYun() {
        return ifAliYun;
    }

    public static void setIfAliYun(boolean ifAliYun) {
        CommonTools.ifAliYun = ifAliYun;
    }
}
