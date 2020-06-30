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
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import AS_Run.AS_Process;
import DES_METHOD.DES;
import DES_METHOD.test;
import RSA.Rsa;
import V_Run.V_Process;
import factory.DaoFactory;
import tcpchatroom.datagram.ClientDataGramAnalyzer;
import tcpchatroom.datagram.ServerDataGram;
import vo.User;

/**
 * ����TCP�Ĳ������߳�������
 * 
 * ���������׽������߳�
 * ��Ӧÿһ���ͻ���

 */

public class ServerThread extends Thread{
	
	public JTextArea tx;
	public JTextArea tx1;
	
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private Rsa rsa;
	private String client_key;
	Lock lock ;
	
	private User user = new User();
	public ServerThread(Socket socket,Rsa rsa,JTextArea tx,JTextArea tx1,Lock lock){
		this.socket = socket;
		this.rsa=rsa;
		this.tx=tx;
		this.tx1=tx1;
		this.lock=lock;
	}
	
	public String Server_DES_encry(String msg,int begin,int end,String k) throws UnsupportedEncodingException{
		msg=msg.substring(begin, end);
		
		test c=new test();
		
		DES des=new DES();
		//����
		String encrypttext=des.enc(msg,k);


		return encrypttext;
	}
	
	
	 public String server_DES_decry(String msg,String k) throws UnsupportedEncodingException{
	    	
		    msg=msg.substring(4,msg.length());
	    	
			DES des=new DES();
			System.out.println(msg+" "+k);
         
			String decrypttext=des.dec(msg,k);
			
			return decrypttext;
	    	
	    }
	 
	 public String server_DES_decry1(String msg,String k) throws UnsupportedEncodingException{
	    	
		    msg=msg.substring(0,msg.length());
	    	
			DES des=new DES();
			System.out.println(msg+" "+k);
      
			String decrypttext=des.dec(msg,k);
			
			return decrypttext;
	    	
	    }
	
	

	public void run(){
		try {
			
			String st8=kerberos();
			
			if(st8!="") {
				System.out.println("");
			}else {
				return;
			}
			
			test c=new test();
			client_key=c.genRandomNum();
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
			while(!this.socket.isClosed()){
				
				String clientdatagram = in.readLine();
				//System.out.println("��½�û�:"+this.user.getUserName()+"	"+"�û�����:"+ this.user.getPasswd() + "	online state:" + ClientDataGramAnalyzer.getState(clientdatagram));
				tx.append(clientdatagram+"\n");
	
				switch (ClientDataGramAnalyzer.getType(clientdatagram)){
				
					case(0)://������Ϣ��
						lock.lock();
						System.out.println(clientdatagram);
						
						String msg_client=server_DES_decry(clientdatagram,client_key);
						tx.append(msg_client+"\n");
						
						clientdatagram="0&&&"+msg_client;
						
						String accepters[] = ClientDataGramAnalyzer.getAccepters(clientdatagram);	
//						System.out.println("���յ���Ϣ:" + user.getUserName()+":"+" "+ ClientDataGramAnalyzer.getMessage(clientdatagram));
						
						for(int i = 1; i< accepters.length;i++){//�׸��ַ��� һ����null ���Բ���
								PrintWriter toclient = new PrintWriter(OnlineUsers.ssm.get(accepters[i]).getOutputStream(),true);
								///����������Ϣ  **************************************
								ServerDataGram message = new ServerDataGram((short)0,this.user.getUserName(),ClientDataGramAnalyzer.getMessage(clientdatagram));
								System.out.println(message.toString()+"    haha");
								String send=Server_DES_encry(message.toString(),4,message.toString().length(),OnlineUsers.name_passwd.get(accepters[i]));
								send="0&&&"+send;
								toclient.println(send);
						}
						lock.unlock();
						break;		
					case(1)://login
						this.user.setUserName(ClientDataGramAnalyzer.getName(clientdatagram));
//						System.out.println("login name : " + this.user.getUserName());
						this.user.setPasswd(ClientDataGramAnalyzer.getPassword(clientdatagram));
//						System.out.println("login password : " + ClientDataGramAnalyzer.getPassword(clientdatagram));
						try {
							
							if(OnlineUsers.onlineUsers.contains(this.user.getUserName())){
								ServerDataGram logdulp = new ServerDataGram((short)3);//���û��Ѿ���¼^^^^^^^^^^^^^^^
								out.println(logdulp.toString());
								this.in.close();
								this.out.close();
								this.socket.close();
								break;
							}
								
							this.user = DaoFactory.getIUserInstance().findByUserNameandPasswd(this.user.getUserName(), this.user.getPasswd());
							if(this.user==null){
								ServerDataGram loginfailedmessage = new ServerDataGram((short)2);//�û������������
								out.println(loginfailedmessage.toString());
								this.in.close();
								this.out.close();
								this.socket.close();
							}
							else{
								//�������ߺ����б�
								//rsa.begin();
								//String pubs=rsa.e.toString()+"&"+rsa.n.toString();
								
								
								String keypass=c.genRandomNum();//�ͻ�����Կ
								
								
								lock.lock();
								
								String[] friends = null ;
								friends  = (String[]) OnlineUsers.onlineUsers.toArray(new String[OnlineUsers.onlineUsers.size()]);
								
								ServerDataGram loginsuccessedmessage = new ServerDataGram((short)1,friends);//��¼�ɹ�  ^^^^^^^^^^^^^^^^^
								
//								System.out.println("server datagram: "+  loginsuccessedmessage.toString());
								out.println(loginsuccessedmessage.toString());
								
								//out.println(pubs);
								
								
								//���ͷ�������Կ
								out.println(client_key);
								
								//�������ѣ���������������
								//if(ClientDataGramAnalyzer.getState(clientdatagram).equals("online")){
								
								
								
		
								ServerDataGram onlineinform = new ServerDataGram((short)8,this.user.getUserName());//����������Ϣ^^^^^^^^^^
								for(Socket client : OnlineUsers.clientList){
									PrintWriter messagetofriend = new PrintWriter(client.getOutputStream(),true);
									//for(String name: OnlineUsers.onlineUsers )
									//{
										//ServerDataGram onlineinform = new ServerDataGram((short)8,name);//����������Ϣ^^^^^^^^^^
										messagetofriend.println(onlineinform.toString());
									//}
									}
								//}
								OnlineUsers.clientList.add(this.socket);
								OnlineUsers.onlineUsers.add(this.user.getUserName());//*****************	
								OnlineUsers.ssm.put(this.user.getUserName(),this.socket);
								OnlineUsers.sskey.put(keypass, this.socket);
								OnlineUsers.name_passwd.put(this.user.getUserName(),keypass);
								
								
								
								tx1.append(this.user.getUserName()+"\n");
								
								out.println(keypass);//������Կ
								lock.unlock();
//								
						//		System.out.print("���ߺ�����"+ OnlineUsers.onlineUsers.size() + "    online users: ");
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}break;
						
					case(2)://�û��˳�				
						
						
						lock.lock();
						//OnlineUsers.clientList.remove(this.socket);
						
                 OnlineUsers.ssm.remove(OnlineUsers.onlineUsers.indexOf(this.user.getUserName()));
					
					OnlineUsers.clientList.remove(OnlineUsers.clientList.indexOf(this.socket));
					
					
						OnlineUsers.onlineUsers.remove(OnlineUsers.onlineUsers.indexOf(this.user.getUserName()));// ***************δ������½���û� �˳�ʱ�׳��쳣
						
						
						for(String socket :OnlineUsers.onlineUsers){
							tx1.append(socket+"\n");
						}
						
						
						
						for(Socket socket :OnlineUsers.clientList){
							PrintWriter toclient= new PrintWriter(socket.getOutputStream(),true);
							toclient.println(new ServerDataGram((short)6,this.user.getUserName()).toString());
						}
					
					
					
						
						this.in.close();
						this.out.close();
						this.socket.close();
						lock.unlock();
						break;
					case(3)://��������״̬
						break;
					case(4)://ע��
						
						
					
						
						
						
						this.user.setUserName(ClientDataGramAnalyzer.getName(clientdatagram));
//						System.out.println("regist name : " + this.user.getUserName());
						this.user.setPasswd(ClientDataGramAnalyzer.getPassword(clientdatagram));
//						System.out.println("regist password : " + ClientDataGramAnalyzer.getPassword(clientdatagram));
						try {
							user.setRegisterTime(new Date());
							if(DaoFactory.getIUserInstance().findByUserName(this.user.getUserName()) != null){
								ServerDataGram logdulp = new ServerDataGram((short)5);//�û����Ѿ�ע��
								out.println(logdulp.toString());
								break;
							}
							
							DaoFactory.getIUserInstance().doCreate(user);//�������ݿ���
							//�������ߺ����б�
							String[] friends = (String[]) OnlineUsers.onlineUsers.toArray(new String[OnlineUsers.onlineUsers.size()]);
								
							ServerDataGram registermessage = new ServerDataGram((short)4,friends);//ע��ɹ�
								
							out.println(registermessage.toString());
							//�������ѣ���������������
							//if(ClientDataGramAnalyzer.getState(clientdatagram).equals("online")){
		
							ServerDataGram onlineinform = new ServerDataGram((short)8,this.user.getUserName());//**************
							for(Socket client : OnlineUsers.clientList){
									PrintWriter messagetofriend = new PrintWriter(client.getOutputStream(),true);
									messagetofriend.println(onlineinform.toString());
							}
							//}
							OnlineUsers.clientList.add(this.socket);
							OnlineUsers.onlineUsers.add(this.user.getUserName());//*****************	
							OnlineUsers.ssm.put(this.user.getUserName(),this.socket);
							
//							for(String user: OnlineUsers.onlineUsers)
//								System.out.print("���ߺ�����" + OnlineUsers.onlineUsers.size()+ "online users: " + user);
							
						}
						 catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}break;
						
					case 9:
						
						final short datanum=9;
						String[] file_arr=clientdatagram.split("&&&");
						String filename=file_arr[1];//��ȡ�ļ���
						
						System.out.println(filename+" ���յ��ļ���");
						
						String clientdata = in.readLine();//���տͻ����ύ��Ҫ�����û�������Ϣ
						String ac[] = ClientDataGramAnalyzer.getAccepters(clientdata);
						
						System.out.println("��ʼ�����ļ�");
						//�������Ƚ��ܲ��洢�ļ�
						//BufferedReader bufr=new BufferedReader(new InputStreamReader(socket.getInputStream()));
				        
				        BufferedWriter bufw=new BufferedWriter(new FileWriter("F:\\Desktop\\������\\"+filename));
				        
				        String line=null;
				        
				        while((line=in.readLine())!=null)//�ļ�����
				        {
				        	
				        	System.out.println(line);
				        	
				        	line=server_DES_decry1(line,client_key);
				        	
				        	if(line.equals("complet")) {
				        		break;
				        	}
				        	
				            bufw.write(line);
				            bufw.newLine();
				            bufw.flush();
				        }
				        
				        bufw.close();
				        
				        System.out.println("���������ļ�");
				        
				        String endmsg="10&&&a";
				        out.println(endmsg);
				        
				        
				       // bufr.close();
				        
				        //������ȡ����
				        BufferedReader bufrfile=new BufferedReader(new FileReader("F:\\Desktop\\������\\"+filename));
				        

				        System.out.println("��ʼ�����ļ�");
				      //�����ļ�
						for(int i = 1; i< ac.length;i++){//�׸��ַ��� һ����null ���Բ���
							PrintWriter toclient = new PrintWriter(OnlineUsers.ssm.get(ac[i]).getOutputStream(),true);
							///  **************************************
							System.out.println(OnlineUsers.ssm.get(ac[i]).getOutputStream()+" ����");
							toclient.println(Short.toString(datanum)+"&&&"+filename);//�����ļ�����

					        //����д�����

					        while((line=bufrfile.readLine())!=null)
					        {
					        	line=Server_DES_encry(line,0,line.length(),OnlineUsers.name_passwd.get(ac[i]));
					        	 toclient.println(line);//�����ļ�����
					           
					        }
					        
					        String mmm="complect";
					        mmm=Server_DES_encry(mmm,0,mmm.length(),OnlineUsers.name_passwd.get(ac[i]));
					        
					        toclient.println(mmm);
      

					}
						
						bufrfile.close();

						// PrintWriter pw=new PrintWriter(socket.getOutputStream(),true);
					        
					       // out.println("���");
					        System.out.println("�����ļ����");
					        
					        
					        
					      //  pw.close();
						
						break;		
						
				}
//				String clientSentence = in.readLine();
//				//��ʾ�ͻ��˷�������Ϣ
//				textArea.append(clientSentence + "\n");
//				//Ⱥ�������пͻ���
//				for(Socket client : OnlineUsers.clientList){
//					PrintWriter out = new PrintWriter(client.getOutputStream(),true);
//					out.println(clientSentence);
//				}
			
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(user.getUserName()+"exit!");
	}

	public String kerberos() throws Exception {
		 InputStream in = socket.getInputStream();
         DataInputStream din = new DataInputStream(in);
         String st7  = din.readUTF();
         System.out.println("7�Ű��յ�!!!!!!!!!!!!!");
         System.out.println(st7);
         V_Process V=new V_Process();
         
         String st8=V.process(st7);
         System.out.println("8�Ű�����!!!!!!!!!!!!!");
         System.out.println(st8);
         OutputStream out = socket.getOutputStream();
         DataOutputStream dos = new DataOutputStream(out);
         dos.writeUTF(st8);
       return st8;
        
         
        
	}
	
}