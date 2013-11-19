package com.rocky.qq.test;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;


public class SimpleQQ {

    private CloseableHttpClient httpclient = HttpClients.createDefault();
    private long qq;
    private String password;

    public SimpleQQ(long qq, String password) {
        this.qq = qq;
        this.password = password;
    }

    public String[] checkLogin() {

        //"https://ssl.ptlogin2.qq.com/check?appid=1003903&js_ver=10038&js_type=0&u1=http%3A%2F%2Fweb2.qq.com%2Floginproxy.html&r=0.2664005843145449&uin=" + qq
        HttpGet httpget = new HttpGet("https://ssl.ptlogin2.qq.com/check?appid=1003903&r=0.2664005843145449&uin=" + qq);

        System.out.println("executing request " + httpget.getURI());
        CloseableHttpResponse responseBody = null;
        String[] checks = null;
        try {
            responseBody = httpclient.execute(httpget);
            InputStream input = responseBody.getEntity().getContent();
            byte[] b = new byte[1024];
            StringBuffer sb = new StringBuffer();
            while (input.read(b) != -1) {
                sb.append(new String(b, "UTF-8"));
            }
            String str = sb.toString();
            System.out.print(str);
            str = str.replaceAll("ptui_checkVC\\(", "").replaceAll("'", "").replaceAll("\\);", "");
            checks = str.split(",");
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            return checks;
        }
    }

    public void getImage(String vc_type) {
        HttpGet httpget = new HttpGet("http://captcha.qq.com/getimage?aid=1003903&uin=" + qq + "&vc_type=" + vc_type);
        File image = new File("D:" + File.separator + "myqqtestfile" + File.separator + "check" + System.currentTimeMillis() + ".jpg");

        CloseableHttpResponse res = null;
        try {
            res = httpclient.execute(httpget);
            InputStream input = res.getEntity().getContent();
            byte[] b = new byte[1024];
            FileOutputStream fos = new FileOutputStream(image);
            while (input.read(b) != -1) {
                fos.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public String login(String vc, String vCode) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String str = null;
        QQEncrypt encrypt = new QQEncrypt();
        String md5 = encrypt.encrypt(qq, password, vc);

        HttpGet httpget = new HttpGet("https://ssl.ptlogin2.qq.com/login?from_ui=1&g=1&login2qq=1" +
                "&pttype=1&u1=http%3A%2F%2Fweb.qq.com%2Floginproxy.html%3Flogin2qq%3D1%26webqq_type%3D10&webqq_type=10" +
                "&daid=164&dumy=&ptlang=2052&aid=1003903&h=1&login_sig=I0x6OIpO*21tB4b6gEXrmkvYNgDPkLzYYaBbZon-CjoViwrS7SSVV2JTgzzd1EzQ" +
                "&js_type=0&u=" + qq + "&t=1&p=" + md5 + "&action=4-28-1632882&remember_uin=1" +
                "&verifycode=" + vc + "&mibao_css=m_webqq&js_ver=10038&fp=loginerroralert&ptredirect=0");
        httpget.addHeader("Referer", "http://d.web2.qq.com/proxy.html");
        httpget.addHeader("User-Agent", "Mozilla/5.0 (Windows 8; 6.2; amd64) AppleWebKit/537.36 (KHTML, like Gecko) IQQ App-dev/2.1 Safari/537.36");
        CloseableHttpResponse responseBody = null;
        try {
//            responseBody = httpclient.execute(httpget);
            responseBody = httpclient.execute(httpget);
            System.out.println("cookie:" + responseBody.getFirstHeader("Set-Cookie").getValue());
            InputStream input = responseBody.getEntity().getContent();


            byte[] b = new byte[1024];
            StringBuffer sb = new StringBuffer();
            while (input.read(b) != -1) {
                sb.append(new String(b, "UTF-8"));
            }
            str = sb.toString();
            System.out.print(str);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            return str;
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        long myQQ = 1431444862;
        String mymistic = "hsh123456";
        SimpleQQ qq = new SimpleQQ(myQQ, mymistic);
        String[] checks = qq.checkLogin();
        if (checks != null) {
            qq.getImage(checks[1]);
        }
        try {
            Scanner sc = new Scanner(System.in);
            String vc = sc.nextLine();
            System.out.println("Check value : " + vc);
            qq.login(vc, checks[1]);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}
