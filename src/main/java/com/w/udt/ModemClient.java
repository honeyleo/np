////////////////////////////////////////////////////////////////////
//                            _ooOoo_                             //
//                           o8888888o                            //    
//                           88" . "88                            //    
//                           (| ^_^ |)                            //    
//                           O\  =  /O                            //
//                        ____/`---'\____                         //                        
//                      .'  \\|     |//  `.                       //
//                     /  \\|||  :  |||//  \                      //    
//                    /  _||||| -:- |||||-  \                     //
//                    |   | \\\  -  /// |   |                     //
//                    | \_|  ''\---/''  |   |                     //        
//                    \  .-\__  `-`  ___/-. /                     //        
//                  ___`. .'  /--.--\  `. . ___                   //    
//                ."" '<  `.___\_<|>_/___.'  >'"".                //
//              | | :  `- \`.;`\ _ /`;.`/ - ` : | |               //    
//              \  \ `-.   \_ __\ /__ _/   .-` /  /               //
//        ========`-.____`-.___\_____/___.-`____.-'========       //    
//                             `=---='                            //
//        ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^      //         
//                       佛祖镇楼                  BUG辟易						  //
//          	佛曰:          									  //
//                  	写字楼里写字间，写字间里程序员；                  				  //
//                  	程序人员写程序，又拿程序换酒钱。						  //
//                  	酒醒只在网上坐，酒醉还来网下眠；						  //
//                  	酒醉酒醒日复日，网上网下年复年。                                                                            //
//                  	但愿老死电脑间，不愿鞠躬老板前；                                                                            //
//                  	奔驰宝马贵者趣，公交自行程序员。                                                                            //
//                  	别人笑我忒疯癫，我笑自己命太贱；                                                                            //
//                  	不见满街漂亮妹，哪个归得程序员？                                                                            //
//                                                                //
////////////////////////////////////////////////////////////////////
package com.w.udt;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.udt.nio.NioUdtProvider;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.util.concurrent.ThreadFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @copyright SHENZHEN RONG WANG HUI ZHI TECHNOLOGY CORP
 * @author Lyon.liao
 * 创建时间：2015年2月3日
 * 类说明：
 * 
 * 最后修改时间：2015年2月3日
 * 修改内容： 新建此类
 *************************************************************
 *                                    .. .vr       
 *                                qBMBBBMBMY     
 *                              8BBBBBOBMBMv    
 *                            iMBMM5vOY:BMBBv        
 *            .r,             OBM;   .: rBBBBBY     
 *            vUL             7BB   .;7. LBMMBBM.   
 *           .@Wwz.           :uvir .i:.iLMOMOBM..  
 *            vv::r;             iY. ...rv,@arqiao. 
 *             Li. i:             v:.::::7vOBBMBL.. 
 *             ,i7: vSUi,         :M7.:.,:u08OP. .  
 *               .N2k5u1ju7,..     BMGiiL7   ,i,i.  
 *                :rLjFYjvjLY7r::.  ;v  vr... rE8q;.:,, 
 *               751jSLXPFu5uU@guohezou.,1vjY2E8@Yizero.    
 *               BB:FMu rkM8Eq0PFjF15FZ0Xu15F25uuLuu25Gi.   
 *             ivSvvXL    :v58ZOGZXF2UUkFSFkU1u125uUJUUZ,   
 *           :@kevensun.      ,iY20GOXSUXkSuS2F5XXkUX5SEv.  
 *       .:i0BMBMBBOOBMUi;,        ,;8PkFP5NkPXkFqPEqqkZu.  
 *     .rqMqBBMOMMBMBBBM .           @Mars.KDIDS11kFSU5q5   
 *   .7BBOi1L1MM8BBBOMBB..,          8kqS52XkkU1Uqkk1kUEJ   
 *   .;MBZ;iiMBMBMMOBBBu ,           1OkS1F1X5kPP112F51kU   
 *     .rPY  OMBMBBBMBB2 ,.          rME5SSSFk1XPqFNkSUPZ,.
 *            ;;JuBML::r:.:.,,        SZPX0SXSP5kXGNP15UBr.
 *                L,    :@huhao.      :MNZqNXqSqXk2E0PSXPE .
 *            viLBX.,,v8Bj. i:r7:,     2Zkqq0XXSNN0NOXXSXOU 
 *          :r2. rMBGBMGi .7Y, 1i::i   vO0PMNNSXXEqP@Secbone.
 *          .i1r. .jkY,    vE. iY....  20Fq0q5X5F1S2F22uuv1M;
 *
 ***************************************************************
 */
public class ModemClient {
	private static final Logger log = LoggerFactory.getLogger(ModemClient.class
			.getName());

			private final String host;
			private final int port;
			private final int messageSize;

			public ModemClient(final String host, final int port, final int messageSize) {
			this.host = host;
			this.port = port;
			this.messageSize = messageSize;
			}

			public void run()  {
			// Configure the client.
			final ThreadFactory connectFactory = new UtilThreadFactory("connect");
			final NioEventLoopGroup connectGroup = new NioEventLoopGroup(1,
			connectFactory, NioUdtProvider.BYTE_PROVIDER);
			try {
			final Bootstrap boot = new Bootstrap();
			boot.group(connectGroup).channelFactory(NioUdtProvider.BYTE_CONNECTOR).handler(new ChannelInitializer() {
			@Override
			protected void initChannel(Channel ch) throws Exception {
				ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO),new ModemClientHandler());
			}
			});
			// Start the client.
			final ChannelFuture f = boot.connect(host, port).sync();
			f.channel().closeFuture().sync();
			}catch(Exception e){
			e.printStackTrace();
			}finally {
			// Shut down the event loop to terminate all threads.
			connectGroup.shutdownGracefully();
			}
			}

			public static void main(final String[] args)  {
			log.info("init");

			final String host = "127.0.0.1"; 
			final int port = 8888;

			final int messageSize = 64 * 1024;

			new ModemClient(host, port, messageSize).run();

			log.info("done");
			}

			}
