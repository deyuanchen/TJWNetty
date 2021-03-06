package com.tianjunwei.netty.codec.serializable.netty;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import com.tianjunwei.netty.codec.pojo.SubscribeReq;

/**
 * @author lilinfeng
 * @date 2014年2月14日
 * @version 1.0
 */
public class SubReqClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * Creates a client-side handler.
     */
    public SubReqClientHandler() {
    }

    public void channelActive(ChannelHandlerContext ctx) {
		for (int i = 0; i < 10; i++) {
		    ctx.write(subReq(i));
		}
		ctx.flush();
		System.err.println("send finished");
    }

    private SubscribeReq subReq(int i) {
	SubscribeReq req = new SubscribeReq();
	req.setAddress("南京市雨花台区软件大道101号华为基地");
	req.setPhoneNumber("138xxxxxxxxx");
	req.setProductName("Netty 最佳实践和原理分析");
	req.setSubReqID(i);
	req.setUserName("Lilinfeng");
	return req;
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg)
	    throws Exception {
	System.err.println("Receive server response : [" + msg + "]");
    }

    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
	ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
	cause.printStackTrace();
	ctx.close();
    }
}
