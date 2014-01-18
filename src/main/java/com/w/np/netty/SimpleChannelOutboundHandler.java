package com.w.np.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class SimpleChannelOutboundHandler extends ChannelOutboundHandlerAdapter {

	@Override
	public void write(ChannelHandlerContext ctx, Object msg,
			ChannelPromise promise) throws Exception {
		ByteBuf buf = ctx.alloc().buffer();
		String str = msg.toString();
		buf.writeShort(str.length());
		buf.writeBytes(str.getBytes());
		ctx.writeAndFlush(buf, promise);
	}
	
}
