package com.rocky.qq.test.responseconsumer;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.nio.IOControl;
import org.apache.http.nio.client.methods.AsyncByteConsumer;
import org.apache.http.protocol.HttpContext;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * <b></b>
 * <br/><br/>
 * Created with IntelliJ IDEA.<br/>
 * User: Rocky<br/>
 * Date: 11/29/13<br/>
 * Time: 2:57 PM<br/>
 * To change this template use File | Settings | File Templates.
 */
public class CaptchaImageUrl extends AsyncByteConsumer {

    File file;
    FileOutputStream out;

    @Override
    protected void onByteReceived(ByteBuffer byteBuffer, IOControl ioControl) throws IOException {
        //To change body of implemented methods use File | Settings | File Templates.
        out.write(byteBuffer.array());
    }

    @Override
    protected void onResponseReceived(HttpResponse httpResponse) throws HttpException, IOException {
        //To change body of implemented methods use File | Settings | File Templates.
        file = new File("D:" + File.separator + "myqqtestfile" + File.separator + System.currentTimeMillis() + ".jpg");
        out = new FileOutputStream(file);
    }

    @Override
    protected Object buildResult(HttpContext httpContext) throws Exception {
        out.close();
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
