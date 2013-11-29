package com.rocky.qq.test.responseconsumer;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.nio.IOControl;
import org.apache.http.nio.client.methods.AsyncCharConsumer;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.nio.CharBuffer;

/**
 * <b></b>
 * <br/><br/>
 * Created with IntelliJ IDEA.<br/>
 * User: Rocky<br/>
 * Date: 11/29/13<br/>
 * Time: 3:21 PM<br/>
 * To change this template use File | Settings | File Templates.
 */
public class WebLoginConsumer extends AsyncCharConsumer<String> {
    @Override
    protected void onCharReceived(CharBuffer charBuffer, IOControl ioControl) throws IOException {
        //To change body of implemented methods use File | Settings | File Templates.
        System.out.println(charBuffer);
    }

    @Override
    protected void onResponseReceived(HttpResponse httpResponse) throws HttpException, IOException {
        //To change body of implemented methods use File | Settings | File Templates.
        System.out.println(httpResponse);
    }

    @Override
    protected String buildResult(HttpContext httpContext) throws Exception {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
