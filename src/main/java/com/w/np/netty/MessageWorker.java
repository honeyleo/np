package com.w.np.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.w.np.push.PushManager;

/**
 * 每个客户端连接都会附带, 处理收到的消息
 * 
 * @author Leo.liao
 * 
 */
public class MessageWorker{
    private static final Logger logger = LoggerFactory
            .getLogger(MessageWorker.class);

    static PushManager pushManager = PushManager.getInstance();
    private final String loginIp;
    private final Channel channel;

    private volatile Object attachment;
    private volatile Executor taskExec;//线�1�7�处理同丄1�7个用户的请求
    
    public MessageWorker(Channel _channel){
        if (_channel == null){
            throw new NullPointerException("新建MessageWorker, channel为null");
        }
        this.channel = _channel;
        loginIp = ((InetSocketAddress) channel.remoteAddress()).getAddress()
                .getHostAddress();
        logger.info("connection ip = {}",loginIp);
    }
    
    public void messageReceived(ByteBuf buffer) {
    	if(buffer.readableBytes() < 1){
            System.err.println("message length error......");
            channel.close();
            return;
        }
    	short msgId = buffer.readShort();
    	if(msgId == 1) {
    		processLogin(buffer);
    	} else {
    		if(attachment != null) {
    			JSONObject user = (JSONObject) attachment;
    			logger.info("user[id={}] online ",user.getIntValue("id"));
    			taskExec.execute(new Runnable() {
					
					public void run() {
						System.out.println("connected...");
						channel.write("hello world");
					}
				});
    		}
    	}
    	
    }
    
    private void processLogin(ByteBuf buffer) {
    	JSONObject user = new JSONObject();
    	int id = buffer.readInt();
    	taskExec = pushManager.getExecutorService(id);
    	user.put("id", id);
    	attachment = user;
    	channel.write(user);
    	
    }
    
    private AtomicBoolean offlineProcessed = new AtomicBoolean(false);
    
    public void processDisconnection() {
    	if (offlineProcessed.compareAndSet(false, true)){

            if (attachment == null){
                return;
            }
            attachment = null;
    	}
    }
    
}