package com.w.np.udp;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class UdpServer implements Runnable {

	private static String ip = "192.168.1.225"; // 服务端ip
	private static int BROADCAST_PORT = 9898;
	private static String BROADCAST_IP = "224.0.0.1";
	InetAddress inetAddress = null;
	Thread t = null;
	MulticastSocket multicastSocket = null;
	private volatile boolean isRuning = true;

	public void init() {
		try {
			inetAddress = InetAddress.getByName(BROADCAST_IP);
			multicastSocket = new MulticastSocket(BROADCAST_PORT);
			multicastSocket.setTimeToLive(1);
			multicastSocket.joinGroup(inetAddress);
			Thread thread = new Thread(this);
			thread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		DatagramPacket dataPacket = null;
		byte[] data = ip.getBytes();
		dataPacket = new DatagramPacket(data, data.length, inetAddress,
				BROADCAST_PORT);
		while (true) {
			if (isRuning) {
				try {
					multicastSocket.send(dataPacket);
					Thread.sleep(3000);
					System.out.println("再次发送ip地址广播:.....");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		UdpServer server = new UdpServer();
		server.init();
	}
}
