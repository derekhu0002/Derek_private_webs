package com.arrdu.calculator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import javax.servlet.http.HttpServletRequest;

import java.util.Set;


import java.util.concurrent.CopyOnWriteArraySet;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WsOutbound;
import org.apache.catalina.websocket.WebSocketServlet;



@SuppressWarnings("deprecation")
public class calcWebSocket extends WebSocketServlet {
	private static final long serialVersionUID = 1L;
	public final Set<calcWebSocketHandler> users = new CopyOnWriteArraySet<calcWebSocketHandler>();
	
	public static int USERNUMBER = 1;
	@Override
	protected StreamInbound createWebSocketInbound(String arg0,
	HttpServletRequest arg1) {
		// TODO Auto-generated method stub
		return new calcWebSocketHandler(users);
	}
	public class calcWebSocketHandler extends MessageInbound {
		
		public calcWebSocketHandler() {
		
		}
		
		public calcWebSocketHandler(Set<calcWebSocketHandler> users) {
			System.out.println("calcWebSocketHandler created");
		}
	    /**
	     * 功能：Java读取txt文件的内容
	     * 步骤：1：先获得文件句柄
	     * 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
	     * 3：读取到输入流后，需要读取生成字节流
	     * 4：一行一行的输出。readline()。
	     * 备注：需要考虑的是异常情况
	     * @param filePath
	     */
	    public void readTxtFile(String filePath){
	        try {
	                String encoding="GBK";
	                File file=new File(filePath);
	                if(file.isFile() && file.exists()){ //判断文件是否存在
	                    InputStreamReader read = new InputStreamReader(
	                    new FileInputStream(file),encoding);//考虑到编码格式
	                    BufferedReader bufferedReader = new BufferedReader(read);
	                    String lineTxt = null;
	                    while((lineTxt = bufferedReader.readLine()) != null){
	            			try {
	            				CharBuffer temp=CharBuffer.wrap(lineTxt);
	            				getWsOutbound().writeTextMessage(temp);
	            			} catch (IOException e) {
	            				// TODO Auto-generated catch block
	            				e.printStackTrace();
	            			}
	                        System.out.println(lineTxt);
	                    }
	                    read.close();
			        }else{
			            System.out.println("找不到指定的文件");
			        }
	        } catch (Exception e) {
	            System.out.println("读取文件内容出错");
	            e.printStackTrace();
	        }
	    }
		@Override
		protected void onTextMessage(CharBuffer message) throws IOException {
		// 这里处理的是文本数据
			System.out.println("onTextMessage "+ message.toString());
			String[] val1 = message.toString().split("_");
			System.out.println("onTextMessage splitted:"+ val1[0] + " " + val1[1] + " " + val1[2]);
            String lineTxt = "sdfsadafasdfasdfsadfasfsdf";
    			try {
    				CharBuffer temp=CharBuffer.wrap(lineTxt);
    				getWsOutbound().writeTextMessage(temp);
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}			
			//回传脚本
			//String filepath = "E:\\derek\\Projects\\webapps_sources\\calculator\\WebContent\\functionscripts\\statRBS.py";
			//readTxtFile(filepath);
		}
		
		public void onMessage(String data) {
			String[] val1 = data.split("_");
			if(val1[0].equals("MSG"))
			{
				try {
					CharBuffer temp=CharBuffer.wrap(data);
					getWsOutbound().writeTextMessage(temp);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
			else
			{
				System.out.println("onMessage ERROR");
			}
			System.out.println("onMessage");
		}
		
		@Override
		protected void onOpen(WsOutbound outbound) {
			System.out.println("onOpen");
		}
		
		@Override
		protected void onClose(int status) {
			System.out.println("onClose");
		}
		
		@Override
		protected void onBinaryMessage(ByteBuffer arg0) throws IOException {
			System.out.println("onBinaryMessage");
		}
		
	}

}