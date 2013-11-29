package com.rocky.qq.test;

import com.rocky.qq.test.http.HttpTools;
import com.rocky.qq.test.responseconsumer.CaptchaImageUrl;
import com.rocky.qq.test.responseconsumer.CheckVerifyConsumer;
import com.rocky.qq.test.responseconsumer.LoginSigConsumer;
import com.rocky.qq.test.responseconsumer.WebLoginConsumer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * <b></b>
 * <br/><br/>
 * Created with IntelliJ IDEA.<br/>
 * User: Rocky<br/>
 * Date: 11/29/13<br/>
 * Time: 10:59 AM<br/>
 * To change this template use File | Settings | File Templates.
 */
public class QQTest {

    private final static String getLoginSig = "https://ui.ptlogin2.qq.com/cgi-bin/login?daid=164&target=self&style=5&mibao_css=m_webqq&appid=1003903&enable_qlogin=0&no_verifyimg=1&s_url=http%3A%2F%2Fweb2.qq.com%2Floginproxy.html&f_url=loginerroralert&strong_login=1&login_state=10&t=20130723001";
    private final static String qq = "1431444862";
    private final static String p = "hsh123456";
    private static String log_sig;

    public static void main(String[] args) {
        HttpTools tools = new HttpTools();
        try {
            log_sig = (String) tools.get(getLoginSig, new LoginSigConsumer(),null);
            System.out.println("log_sig : " + log_sig);
            String checkVerify = (String) tools.get(getCheckVerifyUrl(qq, log_sig), new CheckVerifyConsumer(),null);
            System.out.println("CheckVerify : " + checkVerify);
            if ("0".equals(checkVerify)) {

            } else {
                tools.get(getCaptchaImageUrl(qq), new CaptchaImageUrl(),null);
                System.out.println("Please input verify:");
                Scanner sc = new Scanner(System.in);
                String vc = sc.nextLine();
                Map<String, String> headMap = new HashMap<String, String>();
                headMap.put("Referer","http://d.web2.qq.com/proxy.html?v=20110331002&callback=1&id=3");
                headMap.put("User-Agent","Mozilla/5.0 (Windows 8; 6.2; amd64) AppleWebKit/537.36 (KHTML, like Gecko) IQQ App-dev/2.1 Safari/537.36");
                tools.get(getWebLoginUrl(vc),new WebLoginConsumer(),headMap);
                System.out.println("WebLoginConsumer OK!");
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public static String getCaptchaImageUrl(String qq) {
        StringBuffer sb = new StringBuffer("http://captcha.qq.com/getimage?r=");
        sb.append(Math.random());
        sb.append("&uin=");
        sb.append(qq);
        sb.append("&aid=1003903");
        return sb.toString();
    }

    public static String getCheckVerifyUrl(String qq, String login_sig) {
        StringBuffer sb = new StringBuffer("https://ssl.ptlogin2.qq.com/check?uin=");
        sb.append(qq);
        sb.append("&appid=1003903&js_ver=10038&js_type=0&login_sig=");
        sb.append(login_sig);
        sb.append("&u1=http%3A%2F%2Fweb2.qq.com%2Floginproxy.html&r=");
        sb.append(Math.random());
        return sb.toString();
    }

    public static String getWebLoginUrl(String verifyCode) {
        StringBuffer sb = new StringBuffer("https://ssl.ptlogin2.qq.com/login" );
        sb.append("?u=");
        sb.append(qq);
        sb.append("&p=");
        sb.append(QQEncrypt.encrypt(Long.decode(qq), p, verifyCode));
        sb.append("&verifycode=");
        sb.append(verifyCode.toUpperCase())   ;
        sb.append("&webqq_type=10");
        sb.append("&remember_uin=1");
        sb.append("&login2qq=1");
        sb.append("&aid=1003903");
        sb.append("&u1=http%3A%2F%2Fweb.qq.com%2Floginproxy.html%3Flogin2qq%3D1%26webqq_type%3D10");
        sb.append("&h=1");
        sb.append("&ptredirect=0");
        sb.append("&ptlang=2052");
        sb.append("&daid=164");
        sb.append("&from_ui=1");
        sb.append("&pttype=1");
        sb.append("&dumy=");
        sb.append("&fp=loginerroralert");
        sb.append("&action=4-28-1632882");
        sb.append("&mibao_css=m_webqq");
        sb.append("&t=1");
        sb.append("&g=1");
        sb.append("&js_type=0");
        sb.append("&js_ver=10038");
        sb.append("&login_sig=");
        sb.append(log_sig);

        return sb.toString();
    }
}
