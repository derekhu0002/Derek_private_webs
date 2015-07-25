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
	     * ���ܣ�Java��ȡtxt�ļ�������
	     * ���裺1���Ȼ���ļ����
	     * 2������ļ��������������һ���ֽ���������Ҫ��������������ж�ȡ
	     * 3����ȡ������������Ҫ��ȡ�����ֽ���
	     * 4��һ��һ�е������readline()��
	     * ��ע����Ҫ���ǵ����쳣���
	     * @param filePath
	     */
	    public void readTxtFile(String filePath){
	        try {
	                String encoding="GBK";
	                File file=new File(filePath);
	                if(file.isFile() && file.exists()){ //�ж��ļ��Ƿ����
	                    InputStreamReader read = new InputStreamReader(
	                    new FileInputStream(file),encoding);//���ǵ������ʽ
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
			            System.out.println("�Ҳ���ָ�����ļ�");
			        }
	        } catch (Exception e) {
	            System.out.println("��ȡ�ļ����ݳ���");
	            e.printStackTrace();
	        }
	    }
		@Override
		protected void onTextMessage(CharBuffer message) throws IOException {
		// ���ﴦ������ı�����
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
			//�ش��ű�
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