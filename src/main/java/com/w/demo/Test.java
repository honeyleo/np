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
package com.w.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.huizhi.car.pb.player.ArenaReq_Protocol.ArenaReqPro;

import com.google.protobuf.ByteString;

/**
 * @copyright SHENZHEN RONG WANG HUI ZHI TECHNOLOGY CORP
 * @author Lyon.liao
 * 创建时间：2014年9月28日
 * 类说明：
 * 
 * 最后修改时间：2014年9月28日
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
public class Test {

	public static void main(String[] args) {
//		ArenaReqPro.Builder builder = ArenaReqPro.newBuilder();
//		InputStream is = Test.class.getClassLoader().getResourceAsStream("Desert.jpg");
//		
//		try {
//			builder.setType(1).setData(ByteString.readFrom(is));
//			ArenaReqPro arenaReqPro = builder.build();
//			byte[] data = arenaReqPro.getData().toByteArray();
//			FileOutputStream out = new FileOutputStream(new File("D:\\test2\\Desert.jpg"));
//			out.write(data);
//			out.close();
//			System.out.println();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		Test test = new Test();
		List<Integer> list = test.fun1(2, 1);
		System.out.println(list);
		int j = test.fun2(1234);
		System.out.println(j);
	}
	
	public List<Integer> fun1(int n, int k) {
		byte[] d = new byte[n];
		for(int i = 0; i < n; i ++) {
			d[i] = 1;
		}
		int c = 2;
		while(c <= k) {
			for(int i = 0; i < n; i ++) {
				int ii = i + 1;
				int m = ii % c;
				if(m == 0) {
					d[i] = d[i] == 1 ? (byte)0 : (byte)1;
				}
			}
			c ++;
		}
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < n ; i ++) {
			if(d[i] == 1) {
				list.add(i + 1);
			}
		}
		return list;
	}
	
	public int fun2(int n) {
		String s = String.valueOf(n);
		char[] cc = s.toCharArray();
		List<Character> list = new ArrayList<Character>();
		for(char c : cc) {
			list.add(c);
		}
		Collections.sort(list);
		char[] g = new char[list.size()];
		char[] d = new char[list.size()];
		for(int i = 0; i < list.size(); i ++) {
			g[i] = list.get(list.size() - i - 1);
			if(!(i == 0 && list.get(i) == 0)) {
				d[i] = list.get(i);
			}
		}
		int j = Integer.parseInt(String.valueOf(g)) - Integer.parseInt(String.valueOf(d));
		if(String.valueOf(j).equals(s)) {
			return j;
		} else {
			return fun2(j);
		}
	}
	
	
}
