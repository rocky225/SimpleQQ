package com.rocky.qq.test.responseconsumer;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.nio.IOControl;
import org.apache.http.nio.client.methods.AsyncCharConsumer;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <b></b>
 * <br/><br/>
 * Created with IntelliJ IDEA.<br/>
 * User: Rocky<br/>
 * Date: 11/29/13<br/>
 * Time: 2:02 PM<br/>
 * To change this template use File | Settings | File Templates.
 */
public class LoginSigConsumer extends AsyncCharConsumer<String> {

    private String str;

    @Override
    protected void onCharReceived(CharBuffer charBuffer, IOControl ioControl) throws IOException {
        //To change body of implemented methods use File | Settings | File Templates.
        Pattern pt = Pattern.compile("var g_login_sig=encodeURIComponent\\(\"(.*?)\"\\);");
        Matcher mc = pt.matcher(charBuffer.toString());
        if (mc.find()) {
            str = mc.group(1);
        }
    }

    @Override
    protected void onResponseReceived(HttpResponse httpResponse) throws HttpException, IOException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected String buildResult(HttpContext httpContext) throws Exception {
        return str;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
