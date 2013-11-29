package com.rocky.qq.test.example;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.nio.IOControl;
import org.apache.http.nio.client.methods.AsyncByteConsumer;
import org.apache.http.nio.client.methods.AsyncCharConsumer;
import org.apache.http.nio.client.methods.HttpAsyncMethods;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.concurrent.Future;

/**
 * <b></b>
 * <br/><br/>
 * Created with IntelliJ IDEA.<br/>
 * User: Rocky<br/>
 * Date: 11/29/13<br/>
 * Time: 11:23 AM<br/>
 * To change this template use File | Settings | File Templates.
 */
public class AsyncClientHttpExchangeStreaming {

    public static void main(final String[] args) throws Exception {
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        try {
            httpclient.start();
            Future<String> future = httpclient.execute(HttpAsyncMethods.createGet("https://ui.ptlogin2.qq.com/cgi-bin/login?daid=164&target=self&style=5&mibao_css=m_webqq&appid=1003903&enable_qlogin=0&no_verifyimg=1&s_url=http%3A%2F%2Fweb2.qq.com%2Floginproxy.html&f_url=loginerroralert&strong_login=1&login_state=10&t=20130723001"),
                    new MyByteResponseConsumer(), null);
            String result = future.get();

//            if (result != null && result.booleanValue()) {
//                System.out.println("Request successfully executed");
//            } else {
//                System.out.println("Request failed");
//            }
            System.out.println("Shutting down : " + result);
        } finally {
            httpclient.close();
        }
        System.out.println("Done");
    }

    static class MyResponseConsumer extends AsyncCharConsumer<Boolean> {


        @Override
        protected void onCharReceived(CharBuffer charBuffer, IOControl ioControl) throws IOException {
            System.out.println("---------------------------onCharReceived------------------------------");
            while (charBuffer.hasRemaining()) {
                System.out.print(charBuffer.get());
            }
        }

        @Override
        protected void onResponseReceived(HttpResponse httpResponse) throws HttpException, IOException {
            //To change body of implemented methods use File | Settings | File Templates.
            System.out.println("---------------------------onResponseReceived------------------------------");
        }

        @Override
        protected Boolean buildResult(HttpContext httpContext) throws Exception {
            System.out.println("---------------------------buildResult------------------------------");
            return Boolean.TRUE;  //To change body of implemented methods use File | Settings | File Templates.
        }
    }

    static class MyByteResponseConsumer extends AsyncByteConsumer {

        @Override
        protected void onByteReceived(ByteBuffer byteBuffer, IOControl ioControl) throws IOException {
            //To change body of implemented methods use File | Settings | File Templates.
            System.out.println("---------------------------onByteReceived------------------------------");
        }

        @Override
        protected void onResponseReceived(HttpResponse httpResponse) throws HttpException, IOException {
            //To change body of implemented methods use File | Settings | File Templates.
            System.out.println("---------------------------onResponseReceived------------------------------");
        }

        @Override
        protected Object buildResult(HttpContext httpContext) throws Exception {
            System.out.println("---------------------------buildResult------------------------------");
            return "Hello Rocky";  //To change body of implemented methods use File | Settings | File Templates.
        }
    }
}
