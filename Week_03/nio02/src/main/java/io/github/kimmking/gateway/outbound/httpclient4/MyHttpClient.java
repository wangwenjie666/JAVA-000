package io.github.kimmking.gateway.outbound.httpclient4;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 *
 *
 * @author wangwenjie
 * @date 2020-11-03
 */
@Slf4j
public class MyHttpClient {

    private String serverUrl;

    public MyHttpClient(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    private Object doGet(String url) {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        String responseMsg = null;
        try {
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            responseMsg = EntityUtils.toString(entity);
            if (entity != null) {
                log.info("contentString = [{}], contentType = [{}]", responseMsg, entity.getContentType());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return responseMsg;
    }

    public void handle(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        String uri = fullRequest.uri();
        Object msg = this.doGet(this.serverUrl + uri);
        ctx.write(msg);
    }
}
