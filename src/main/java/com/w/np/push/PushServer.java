package com.w.np.push;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.ChannelGroupFuture;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.w.np.netty.SimpleChannelOutboundHandler;
import com.w.np.netty.SingleDecoderHandler;

public class PushServer {

	static ServerBootstrap serverBootstrap;

	private static ChannelGroup allChannels = new DefaultChannelGroup(new DefaultEventExecutorGroup(1).next());
	private static Logger logger = LoggerFactory.getLogger(PushServer.class);

	public static void start(int port) {
		// Configure the server.
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
						ch.pipeline().addLast(new SingleDecoderHandler(allChannels)).addLast(new SimpleChannelOutboundHandler());
					}
				});
			allChannels.add(serverBootstrap.bind(port).channel());
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("开始监听 {} 端口", port);
	}

	public static void shutdown() throws Exception {
		try {
			/**
			 * 主动关闭服务器
			 */
			ChannelGroupFuture future = allChannels.close();
			future.awaitUninterruptibly();// 阻塞，直到服务器关闭
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} finally {
			logger.info("server is shutdown on port ");
			System.exit(1);
		}
	}
	
	public static ChannelGroup getChannelGroup() {
		return allChannels;
	}

	public static void main(String[] args) {
		PushManager.getInstance().initWorkers();
		start(9000);
	}
}
