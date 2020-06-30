/**
 * 
 */
package tcpchatroom;

/**
 * @author xuxiaohui
 *
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.Socket;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import DES_METHOD.DES;
import RSA.Rsa;
import factory.Test05;
import tcpchatroom.datagram.ServerDataGramAnalyzer;

/**
 * ����TCP�Ĳ������߳�������
 * 
 * �ͻ������߳�
 * ���շ������˷�������Ϣ����ʾ
 *
 */

public class ClientThread extends Thread{
	private Socket socket;
	private BufferedReader in;
	private JTextArea textArea;
	private JTextArea textArea2;
	private JTextArea textArea3;
	String key;
	String server_key;
	public BigInteger server_e;
	public BigInteger server_n;
    Test05 vc=new Test05();	

	
	public Rsa rsa=new Rsa();
	
	public String get_key() {
		return this.key;
	}
	
	public String get_server_key() {
		return this.server_key;
	}
	
	public ClientThread(JTextArea area,JTextArea area2, JTextArea area3,Socket socket,BufferedReader in) throws IOException{
		this.textArea = area;
		this.textArea2 = area2;
		this.socket = socket;
		this.in = in;
		this.textArea3=area3;
	}
	
	 public String C_DES_decry(String msg,String k) throws UnsupportedEncodingException{
	    	
		    msg=msg.substring(4,msg.length());
	    	
			DES des=new DES();
         
			String decrypttext=des.dec(msg,k);
			
			return decrypttext;
	    	
	    }
	 
	 
	 public String C_DES_decry1(String msg,String k) throws UnsupportedEncodingException{
	    	
		    msg=msg.substring(0,msg.length());
	    	
			DES des=new DES();
      
			String decrypttext=des.dec(msg,k);
			
			return decrypttext;
	    	
	    }

	public void run(){
		try {
			//ѭ����ȡ
			int times=0;
			while(!this.socket.isClosed()){
				
				String stocdatagram = in.readLine();//************************�׳��쳣
				switch(ServerDataGramAnalyzer.getType(stocdatagram)){
				
				
				case(0)://�յ���ͨ��Ϣ
					times++;
//					System.out.println("���յ�server ��Ϣ:" + stocdatagram);
					//textArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
				    textArea3.append("		��ʷ��Ϣ\n\n");
					textArea3.append("���ģ� "+stocdatagram+"\n");
					
					
					
					String mid=C_DES_decry(stocdatagram,key);
				    stocdatagram="0&&&"+mid;
				    
				    textArea3.append("���ģ� "+stocdatagram+"\n");
				    
				    //textArea3.append("\n");
				    
				    if(times==6) {
				    	textArea3.append(null);
				    	textArea3.append("       ��ʷ��Ϣ\n");
				    }
				   
				    String name1=ServerDataGramAnalyzer.getName(stocdatagram);
				    String[] msg=ServerDataGramAnalyzer.getMessage(stocdatagram).split("%%%");
				    stocdatagram=msg[0];
				    String hash=msg[1];
				    String sign=msg[2];
				    
				    boolean flag=rsa.confirm_sign_neww(sign, msg[0]);
				    if(flag==false) {
				    	 JOptionPane pane = new JOptionPane("��Ϣ����");
				         JDialog dialog  = pane.createDialog("����");
				         dialog.show();
				    }
				    
				    textArea3.append("���ģ� "+stocdatagram+"\n");
				    textArea3.append("ǩ�����ݣ� "+sign+"\n");
				    textArea3.append("\n");
				    
				    vc.voice(msg[0]);
					textArea.append(name1+ ":"  + msg[0]+"\n");
					break;
				
				case(4)://������Կ
					
					String[] key_des=stocdatagram.split("&&&");
				    key=key_des[1];
					
					
					
				case(1)://��½�ɹ� ���ܺ����б�	
					
					rsa.begin();
					
				    System.out.println(stocdatagram);
				
					String [] usernames = ServerDataGramAnalyzer.getNames(stocdatagram);
					for(int i = 1 ;i < usernames.length;i++){
						OnlineUsers.onlineUsers.add(usernames[i]);
						textArea2.append(usernames[i]+"\n");
//						System.out.println("at client thread ���ܺ����б�:" + OnlineUsers.onlineUsers.get(i-1));
					}
					
//					String server_pubs=in.readLine();
//					String str[]=server_pubs.split("&");
//					server_e=new BigInteger(str[0]);
//					server_n=new BigInteger(str[1]);
//					
					server_key=in.readLine();
					
					key=in.readLine();
					
					 JOptionPane pane1 = new JOptionPane("��Կ�����ɹ�");
				      JDialog dialog1  = pane1.createDialog("��ϲ");
				      dialog1.show();
					
					
					
					break;
					//��ʾ��Ӵ�б�
				case(2)://��½ʧ��
					JOptionPane.showMessageDialog(null,"��¼ʧ��", "��Ϣ����",JOptionPane.ERROR_MESSAGE);break;
				case(3)://�ظ���½
					System.out.println(stocdatagram);
					JOptionPane.showMessageDialog(null,"�Ѿ�������", "��Ϣ����",JOptionPane.ERROR_MESSAGE);break;
				case(5)://ע���ظ�
					JOptionPane.showMessageDialog(null,"�û����Ѿ�ע���", "��Ϣ����",JOptionPane.ERROR_MESSAGE);break;
				case(6):// �û��˳� ������ʾ�б�
					OnlineUsers.onlineUsers.remove(ServerDataGramAnalyzer.getName(stocdatagram));//�б���ʾ***************************************
					
				System.out.println(stocdatagram);
				
				vc.voice(ServerDataGramAnalyzer.getName(stocdatagram)+"������");
				
				textArea.append(ServerDataGramAnalyzer.getName(stocdatagram)+"  ������"+ "\n");
					textArea2.setText(null);
					textArea2.append("		�����б�\n");
					
					for (String name : OnlineUsers.onlineUsers){
						System.out.println("******" + name);
						textArea2.append(name + "\n");
					}
					
				break;
				case(7)://��������״̬
					break;//**********************************
				case(8)://�û���������
					
					OnlineUsers.onlineUsers.add(ServerDataGramAnalyzer.getName(stocdatagram));
					//textArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
					//ÿ�ν��յ���Ϣ ��Ҫ��ӡһ�º����б�:
					vc.voice(ServerDataGramAnalyzer.getName(stocdatagram)+"������");
				textArea.append(ServerDataGramAnalyzer.getName(stocdatagram)+"  ������"+ "\n");
					textArea2.append(ServerDataGramAnalyzer.getName(stocdatagram)+ "\n");
					break;
				case(9)://�����ļ�
					
					String[] file_arr=stocdatagram.split("&&&");
				String filename=file_arr[1];//��ȡ�ļ���
					
				System.out.println(filename+"   &&&&&&%%%%%%");
				//�������Ƚ��ܲ��洢�ļ�
			//	BufferedReader bufr=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		        
		        BufferedWriter bufw=new BufferedWriter(new FileWriter("F:\\Desktop\\����\\"+filename));
		        
		        String line=null;
		        
		        while((line=in.readLine())!=null)//�ļ�����
		        {
		        	textArea3.append(line+"\n");
		        	line=C_DES_decry1(line,key);
		        	textArea3.append(line+"\n");
		        	if(line.equals("complect")) {
		        	
		        	break;
		        	}
		        	
		            bufw.write(line);
		            bufw.newLine();
		            bufw.flush();
		        }
		        
		        bufw.close();
		       // bufr.close();
				
		        JOptionPane pane = new JOptionPane("���յ��ļ��� "+filename);
		   	      JDialog dialog  = pane.createDialog("��ϲ");
		   	      dialog.show();
					
					
					
					break;	
					
				case(10)://���ձ����ͷ�����Կ
					
					String[] file_arr1=stocdatagram.split("&&&");
					String text=file_arr1[1];
					 // String text=in.readLine();
			      //  System.out.println(text);
			        System.out.println(text);
			        String endmsg="a";
			        
			        if(text.equals(endmsg)) {//����ļ����ͳɹ��������ѶԻ���
			        	 JOptionPane pane11 = new JOptionPane("���ͳɹ�");
			   	      JDialog dialog11  = pane11.createDialog("��ϲ");
			   	      dialog11.show();
		
			        }
					
					break;//**********************************	
					
					
					
				}
				
			
//				System.out.println("���ߺ�����:"+OnlineUsers.onlineUsers.size() + "   �����б�:\n");
//				for (String  username: OnlineUsers.onlineUsers){
//					System.out.println( "  "+ username + "��");
//				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}