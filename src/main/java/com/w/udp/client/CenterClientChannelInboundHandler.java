package com.w.udp.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import cn.huizhi.car.pb.player.PBMessageProto.PBMessage;

public class CenterClientChannelInboundHandler extends SimpleChannelInboundHandler<PBMessage> {

	private final static Logger LOG = LoggerFactory.getLogger(CenterClientChannelInboundHandler.class.getName());
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, PBMessage pbMessage)
			throws Exception {
		LOG.info("PbMessage = {}", pbMessage);
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		LOG.info("断开和中心服的连接");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		ctx.close();
		LOG.info("channelInactive");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		LOG.info("断开和中心服的连接");
		LOG.error(cause.getMessage());
	}


}
