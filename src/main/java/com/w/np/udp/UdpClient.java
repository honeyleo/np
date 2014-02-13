package com.w.np.udp;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class UdpClient implements Runnable {

	private MulticastSocket multicastSocket = null;
	private static int BROADCAST_PORT = 9898;
	private static String BROADCAST_IP = "224.0.0.1";
	InetAddress inetAddress = null;
	Thread thread = null;
	private static String ip;

	public void init() {
		thread = new Thread(this);
		try {
			multicastSocket = new MulticastSocket(BROADCAST_PORT);
			inetAddress = InetAddress.getByName(BROADCAST_IP);
			multicastSocket.joinGroup(inetAddress);
			thread.start();

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void run() {
		byte buf[] = new byte[1024];
		DatagramPacket dp = null;
		dp = new DatagramPacket(buf, buf.length, inetAddress, BROADCAST_PORT);

		while (true) {
			try {
				multicastSocket.receive(dp);
				Thread.sleep(3000);
				ip = new String(buf, 0, dp.getLength());
				System.out.println("检测到服务端IP : " + ip);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		UdpClient client = new UdpClient();
		client.init();
	}
}
