package io.kimmking.rpcfx.netty;


import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpMethod;

import java.io.IOException;
import java.net.URI;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;


/**
 * 自定义client，访问后端 serverUrl [8088] 端口服务
 *
 * @author wangwenjie
 * @date 2020-11-03
 */
public class MyHttpClient {

    public Object handle(String url, HttpMethod httpMethod, ChannelHandlerContext ctx) {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpRequestBase request = new HttpRequestBase() {
            @Override
            public String getMethod() {
                return httpMethod.name();
            }
        };
        request.setURI(URI.create(url));

        CloseableHttpResponse response = null;
        String responseMsg = null;
        try {
            response = client.execute(request);
            HttpEntity entity = response.getEntity();
            responseMsg = EntityUtils.toString(entity);
            //输出响应到浏览器
            FullHttpResponse res = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(responseMsg.toString().getBytes("UTF-8")));
            res.headers().set("Content-Type", "application/json");
            res.headers().setInt("Content-Length", res.content().readableBytes());
            ctx.write(response);
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
}
