package io.github.kimmking.gateway.outbound.httpclient4;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * 自定义client，访问后端 serverUrl [8088] 端口服务
 *
 * @author wangwenjie
 * @date 2020-11-03
 */
@Slf4j
public class MyHttpClient {

    private String[] serverUrl;

    private Map<String, String> header;

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

    public MyHttpClient(String[] serverUrl) {
        this.serverUrl = serverUrl;
    }

    private Object doGet(String url) {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);

        //添加请求头
        Iterator<String> iterator = header.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            log.info("==> 网关设置请求头 key = [{}],value = [{}]", key, header.get(key));
            httpGet.setHeader(key, header.get(key));
        }

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

    public void handle(FullHttpRequest fullRequest, ChannelHandlerContext ctx) throws UnsupportedEncodingException {
        String uri = fullRequest.uri();
        Object msg = "";
        if (uri.startsWith("/api")) {
            msg = this.doGet(getRandomServerUrl() + uri);
            log.info("==> 响应为 = {}", msg);
        }

        //输出响应到浏览器
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(msg.toString().getBytes("UTF-8")));
        response.headers().set("Content-Type", "application/json");
        response.headers().setInt("Content-Length", response.content().readableBytes());
        ctx.write(response);
    }

    public String getRandomServerUrl() {
        //负载均衡，取随机服务器访问
        Random random = new Random();
        int index = random.nextInt(this.serverUrl.length);
        String server = this.serverUrl[index];
        log.info("==> 负载均衡跳转服务为 = [{}]", server);
        return server;
    }
}
