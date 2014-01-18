package com.w.http;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpHeaders.Values;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.nio.charset.Charset;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpChannelInboundHandler extends
		SimpleChannelInboundHandler<FullHttpRequest> {

	private ChannelGroup allChannels;
	
	public HttpChannelInboundHandler(ChannelGroup allChannels) {
		this.allChannels = allChannels;
	}
	static final Logger LOG = LoggerFactory.getLogger(HttpChannelInboundHandler.class
			.getName());

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		allChannels.add(ctx.channel());
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx,
			FullHttpRequest request) throws Exception {

		LOG.info("{}", request);
		boolean keepAlive = false;
		String content = request.content().toString(Charset.forName("UTF-8"));
		LOG.info("{}", content);
		QueryStringDecoder query = new QueryStringDecoder(content, Charset.forName("UTF-8"),
				false);
		LOG.info("query = {}", query);

		List<String> list = query.parameters().get("score");
		LOG.info("list = {}", list);
		ByteBuf b = ctx.alloc().buffer();

		b.writeBytes("大家好才是真的好！".getBytes());

		FullHttpResponse response = new DefaultFullHttpResponse(
				HttpVersion.HTTP_1_1, HttpResponseStatus.OK, b);

		response.headers().set(HttpHeaders.Names.CONTENT_TYPE,
				"text/html; charset=UTF-8");
		response.headers().set(HttpHeaders.Names.CONTENT_LENGTH,
				b.readableBytes());
		if (!keepAlive) {
			ctx.writeAndFlush(response)
					.addListener(ChannelFutureListener.CLOSE);
		} else {
			response.headers().set(HttpHeaders.Names.CONNECTION,
					Values.KEEP_ALIVE);
			ctx.writeAndFlush(response);
		}
	}

}
