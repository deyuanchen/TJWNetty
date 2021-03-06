package com.tianjunwei.netty.codec.serializable.netty;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import com.tianjunwei.netty.codec.pojo.SubscribeReq;
import com.tianjunwei.netty.codec.pojo.SubscribeResp;

/**
 * @author lilinfeng
 * @date 2014年2月14日
 * @version 1.0
 */
@Sharable
public class SubReqServerHandler extends ChannelInboundHandlerAdapter {

    public void channelRead(ChannelHandlerContext ctx, Object msg)
	    throws Exception {
    	SubscribeReq req = (SubscribeReq) msg;
		if ("Lilinfeng".equalsIgnoreCase(req.getUserName())) {
		    System.err.println("Service accept client subscrib req : ["
			    + req.toString() + "]");
		    ctx.writeAndFlush(resp(req.getSubReqID()));
		}
    }

    private SubscribeResp resp(int subReqID) {
		SubscribeResp resp = new SubscribeResp();
		resp.setSubReqID(subReqID);
		resp.setRespCode(0);
		resp.setDesc("Netty book order succeed, 3 days later, sent to the designated address");
		return resp;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
	cause.printStackTrace();
	ctx.close();// 发生异常，关闭链路
    }
}
