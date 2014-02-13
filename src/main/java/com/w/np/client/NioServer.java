package com.w.np.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
 
public class NioServer {// No thread need here
    private static final int NIO_SERVER_PORT = 9000;
 
    private Selector selector;
    private ServerSocketChannel serverChannel;
    private ByteBuffer buffer;
 
    public void init() throws IOException {
        selector = Selector.open();
 
        serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);// Must be non-blocking
 
        ServerSocket serverSocket = serverChannel.socket();
        serverSocket.bind(new InetSocketAddress(NIO_SERVER_PORT));
 
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
 
        buffer = ByteBuffer.allocate(1024);
    }
 
    public void process() throws IOException {
        while (true) {
            int keys = selector.select();// Blocked
            if (keys <= 0) {
                continue;
            }
            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> it = readyKeys.iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();
                it.remove();// If not, loop 0
                if (key.isAcceptable()) {
                    this.accept(key);
                    System.out.println("Client connected");
                } else if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    String receivedString = this.read(channel);
                    System.out.println("Server Receive: " + receivedString);
                    String response = "--->" + receivedString;
                    this.write(channel, response);
                }
            }
        }
    }
 
    private void accept(final SelectionKey key) throws IOException {
        SocketChannel clientChannel = ((ServerSocketChannel) key.channel())
                .accept();
        if (clientChannel != null) {
            clientChannel.configureBlocking(false);
            clientChannel.register(selector, SelectionKey.OP_READ);
        }
    }
 
    private String read(final SocketChannel channel) throws IOException {
        buffer.clear();// not actually erase the data.
        int numRead = channel.read(buffer);// Once
        byte[] newBytes = new byte[numRead];
        System.arraycopy(buffer.array(), 0, newBytes, 0, numRead);
        String receivedString = new String(newBytes);
        return receivedString;
    }
 
    private void write(final SocketChannel channel, final String resp)
            throws IOException {
        ByteBuffer responseBuffer = ByteBuffer.wrap(resp.getBytes());
        while (responseBuffer.hasRemaining()) {
            channel.write(responseBuffer);
        }
    }
 
    public static void main(String[] args) {
        try {
            NioServer server = new NioServer();
            server.init();
            server.process();// Single thread
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
}
