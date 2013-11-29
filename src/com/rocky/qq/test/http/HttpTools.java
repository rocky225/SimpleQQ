package com.rocky.qq.test.http;

import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.DefaultHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.nio.client.methods.HttpAsyncMethods;
import org.apache.http.nio.protocol.AbstractAsyncResponseConsumer;
import org.apache.http.nio.protocol.BasicAsyncRequestProducer;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * <b></b>
 * <br/><br/>
 * Created with IntelliJ IDEA.<br/>
 * User: Rocky<br/>
 * Date: 11/29/13<br/>
 * Time: 9:51 AM<br/>
 * To change this template use File | Settings | File Templates.
 */
public class HttpTools {

    private DefaultHttpAsyncClient asyncHttpClient;

    public Object post(String url, AbstractAsyncResponseConsumer myConsumer, String json) throws IOException {
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        Object obj = null;
        try {
            httpclient.start();

            Future<Object> future = httpclient.execute(HttpAsyncMethods.createPost(url, json, ContentType.APPLICATION_JSON),
                    myConsumer, null);
            obj = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ExecutionException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            httpclient.close();
            return obj;
        }
    }

    public Object get(String url, AbstractAsyncResponseConsumer myConsumer,Map<String, String> headerMap) throws IOException {
        URI uri = URI.create(url);
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        Object obj = null;
        try {
            httpclient.start();
            HttpGet httpget = new HttpGet(uri);
            HttpHost httphost = URIUtils.extractHost(uri);
            if(headerMap != null) {
                for(String key: headerMap.keySet()){
                    httpget.addHeader(key, headerMap.get(key));
                }
            }
            BasicAsyncRequestProducer requset = new BasicAsyncRequestProducer(httphost,httpget);

            Future<Object> future = httpclient.execute(requset,myConsumer, null);
            obj = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ExecutionException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            httpclient.close();
            return obj;
        }
    }

}
