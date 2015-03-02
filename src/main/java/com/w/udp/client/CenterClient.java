package com.w.udp.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.huizhi.car.pb.player.FlashAdverAck_Protocol.FlashAdverAckPro;
import cn.huizhi.car.pb.player.PBMessageProto.PBMessage;

public class CenterClient {

	private static Logger logger = LoggerFactory.getLogger(CenterClient.class);
	
	private String ip;
	private int port;
	private Bootstrap bootstrap;
	private Channel channel;
	
	public CenterClient(String ip, int port) {
		this.ip = ip;
		this.port = port;
		init();
	}
	
	public void init() {
		EventLoopGroup workerGroup = new NioEventLoopGroup(2);
		
		try {
			bootstrap = new Bootstrap();
			
			bootstrap.group(workerGroup)
				.channel(NioDatagramChannel.class)
				.option(ChannelOption.TCP_NODELAY, true)
				.option(ChannelOption.SO_BROADCAST, true)
				.option(ChannelOption.SO_KEEPALIVE, true)
				.option(ChannelOption.MAX_MESSAGES_PER_READ, 4096)
				.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
				.handler(new ChannelInitializer<DatagramChannel>() {
					@Override
					protected void initChannel(DatagramChannel ch) throws Exception {
						ch.pipeline()
							.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(10240, 0, 2, 0, 2))
							.addLast("protobufDecoder", new ProtobufDecoder(PBMessage.getDefaultInstance()))
							.addLast(new CenterClientChannelInboundHandler())
							.addLast("frameEncoder", new LengthFieldPrepender(2))
							.addLast("protobufEncoder", new ProtobufEncoder())
							;
					}
				});
			logger.info("初始化中心服务器【IP={},Port={}】", ip, port);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	public void connect() {
		try {
			channel = bootstrap.connect(ip, port).sync().channel();
			logger.info("连接上中心服务器【IP={},Port={}】", ip, port);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	/**
	 * 发送数据
	 * @param pbMessage
	 */
	public void send(PBMessage pbMessage) {
		if(!isAvailable()) {
			connect();
		}
		if(channel == null) {
			return;
		}
		channel.writeAndFlush(pbMessage);
	}
	
	public boolean isAvailable() {
		if(channel == null || !channel.isActive()) {
			return false;
		}
		return true;
	}
	
	public void shutdown() {
		if(channel != null) {
			logger.info("关闭与中心服务器【ip={}，port={}】的连接", ip, port);
			channel.close();
		}
	}
	public static void main(String[] args) {
		CenterClient client = new CenterClient("127.0.0.1", 8000);
		PBMessage.Builder builder = PBMessage.newBuilder();
		FlashAdverAckPro.Builder b = FlashAdverAckPro.newBuilder();
		builder.setCmd(0x11).setData(b.build().toByteString());
		client.send(builder.build());
		client.send(builder.build());
		client.shutdown();
	}
}
