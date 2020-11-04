package io.github.kimmking.gateway.inbound;

import io.github.kimmking.gateway.outbound.httpclient4.HttpOutboundHandler;
import io.github.kimmking.gateway.outbound.httpclient4.MyHttpClient;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Random;

@Slf4j
public class HttpInboundHandler extends ChannelInboundHandlerAdapter {

    private static Logger logger = LoggerFactory.getLogger(HttpInboundHandler.class);
    private final String[] proxyServer;
    private HttpOutboundHandler handler;

    private MyHttpClient myHttpClient;

    public HttpInboundHandler(String[] proxyServer) {
        this.proxyServer = proxyServer;



        myHttpClient = new MyHttpClient(proxyServer);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            FullHttpRequest fullRequest = (FullHttpRequest) msg;

            //设置请求头
            myHttpClient.setHeader(Collections.singletonMap("nio", "wangwenjie"));

            myHttpClient.handle(fullRequest, ctx);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }


}
