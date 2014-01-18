package com.w.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpServer {

	static final Logger LOG = LoggerFactory.getLogger(HttpServer.class.getName());
	
	private static final int DEFAULT_PORT = 8000;
	
	private ChannelGroup allChannels = new DefaultChannelGroup(new DefaultEventExecutorGroup(1).next());
	
	private ServerBootstrap serverBootstrap;
	
	public void start(int port) {
		LOG.info("start run HttServer port={}", port);
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
				
		try {
			serverBootstrap = new ServerBootstrap();
			
			serverBootstrap.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.option(ChannelOption.SO_BACKLOG, 100)
				.option(ChannelOption.SO_KEEPALIVE, true)
				.handler(new LoggingHandler(LogLevel.INFO))
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline().addLast(new ReadTimeoutHandler(10));  
	                    ch.pipeline().addLast(new WriteTimeoutHandler(1));
	                    ch.pipeline().addLast(new HttpRequestDecoder());   //用于解析http报文的handler  
	                    ch.pipeline().addLast(new HttpObjectAggregator(65536));   //用于将解析出来的数据封装成http对象，httprequest什么的  
	                    ch.pipeline().addLast(new HttpResponseEncoder());   //用于将response编码成httpresponse报文发送
						ch.pipeline().addLast(new HttpChannelInboundHandler(allChannels));
					}
				});
			allChannels.add(serverBootstrap.bind(port).channel());
		} catch(Exception e) {
			LOG.error("start HttpServer error.");
		}
	}
	
	public void start() {
		start(DEFAULT_PORT);
	}
	
	public void stop() {
		allChannels.close();
	}
	public static void main(String[] args) {
		HttpServer httpServer = new HttpServer();
		httpServer.start();
	}
}
