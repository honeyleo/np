package com.w.np.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
 
public class NioClient {
	
    private Selector selector;
 
    public void connect(String host, int port) throws IOException {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.connect(new InetSocketAddress(host, port));
 
        selector = Selector.open();
        channel.register(selector, SelectionKey.OP_CONNECT);
    }
 
    public void process() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(
                System.in));
        while (true) {
            int keyCount = selector.select();
            if (keyCount <= 0) {
                continue;
            }
            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIt = readyKeys.iterator();
            while (keyIt.hasNext()) {
                SelectionKey key = keyIt.next();
                keyIt.remove();
                if (key.isConnectable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    if (channel.isConnectionPending()) {
                        channel.finishConnect();
                    }
                    channel.configureBlocking(false);
                    channel.register(selector, SelectionKey.OP_WRITE);// Notice
                    System.out.println("Connected.");
                    break;
                } else if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    String readed = this.read(channel);
                    System.out.println("Server response: " + readed);
                    channel.register(selector, SelectionKey.OP_WRITE);// Notice
                } else if (key.isWritable()) {
                    System.out.print("Input: ");
                    String inputString = input.readLine();
                    SocketChannel channel = (SocketChannel) key.channel();
                    this.write(channel, inputString);
                    channel.register(selector, SelectionKey.OP_READ);// Notice
                }
            }
        }
    }
 
    public void heartBeat(SocketChannel channel) {
    	
    }
    private String read(SocketChannel channel) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer);
        byte[] data = buffer.array();
        String msg = new String(data).trim();
        return msg;
    }
 
    private void write(SocketChannel channel, String str) throws IOException {
        ByteBuffer requestBuffer = ByteBuffer.wrap(str.getBytes());
        while (requestBuffer.hasRemaining()) {
            channel.write(requestBuffer);
        }
    }
 
    public static void main(String[] args) {
        try {
            NioClient client = new NioClient();
            client.connect("127.0.0.1", 9000);
            client.process();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }
}