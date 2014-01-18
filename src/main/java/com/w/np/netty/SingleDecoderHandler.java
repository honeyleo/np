package com.w.np.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 客户端直接连接到服务器
 * @author Leo.liao
 *
 */
public class SingleDecoderHandler extends LengthFieldBasedFrameDecoder {
    private MessageWorker worker;
    private final ChannelGroup channelGroup;
    private Channel channel;
    
    public SingleDecoderHandler(ChannelGroup _channelGroup) {
        super(1200, 0, 2, -2, 2);
        this.channelGroup = _channelGroup;
    }

    @Override
	protected ByteBuf extractFrame(ChannelHandlerContext ctx, ByteBuf buffer,
			int index, int length) {
    	
    	try {
    		
    		ByteBuf buf = buffer.slice(index, length);
    		worker.messageReceived(buf);
    	} catch (Exception e) {
    		System.err.println("error.");
    	}
		return super.extractFrame(ctx, buffer, index, length);
	}

    @Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
    	channel = ctx.channel();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		channelGroup.add(channel);
        worker = new MessageWorker(channel);
	}
	
}
