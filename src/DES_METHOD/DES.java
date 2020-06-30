package DES_METHOD;
import java.io.UnsupportedEncodingException;
public class DES {
	 public Rules rules;
	 //秘钥置换1
	 public char[] Key_Replace1(char[]K)
	 {
		 char temp[]=new char[56];
		 for(int i=0;i<56;i++)
		 {
			 temp[i]=K[rules.KEY_Repl[i]-1];
		 }
		 //System.out.println("temp:"+new String(temp));
		 return temp;
	 }
	 //生成子秘钥0
	 private char[] LoopKey(char[]K,int offset)
	 {   
		 String k1=new String();
		 String k2=new String();
		 String k=new String(K);
		 k1=(k.substring(0,28)+k.substring(0,2)).substring(offset, 28+offset);
		 k2=(k.substring(28,56)+k.substring(28,30)).substring(offset, 28+offset);
		 return (k1+k2).toCharArray();
		 
	 }
	 //秘钥置换2
	 public char[] Key_Replace2(char[]K)
	 {
		 char temp[]=new char[48];
		 for(int i=0;i<48;i++)
		 {
			 temp[i]=K[rules.KEY_Rep2[i]-1];
		 }
		 return temp;
	 }
	 //秘钥生成
	 public char[][] Get_Key(char[]M)
	 {   char[]k1=new char[56];
	     char[][]tk=new char[16][56];
	     char[][]tkk=new char[16][48];
		 k1=Key_Replace1(M);
		 tk[0]=LoopKey(k1,rules.KEY_Move[0]);
		 for(int i=1;i<16;i++)
		 {
		 tk[i]=LoopKey(tk[i-1],rules.KEY_Move[i]);
		 //System.out.println("tk====="+new String(tk[i]));
		 }
		 for(int i=0;i<16;i++)
		 {
			 tkk[i]=Key_Replace2(tk[i]);
			 
		 }
		
		 return tkk;
	 }
	 //初始置换
	 public char[] DES_IPinitial_Transform(char M[])
	 {
		 char temp[]=new char[64];
		 for(int i=0;i<64;i++)
		 {
		 temp[i]=M[rules.IP_IniRep[i]-1];
		 }
		 return temp;
	 }
	 //EP扩展
	 public char[] DES_EP_Extend(char[]MR)
	 {
		 char[]tR=new char[48];
		 for(int i=0;i<48;i++)
		 {
			 tR[i]=MR[rules.E_Extend[i]-1];
		 } 
		 //System.out.println(tR);
		 return tR;
	 }
	 //异或操作
	 public char[] DES_XOR(char[]A,char[]B)
	 {
		 String result=new String();
		 for(int i=0;i<A.length;i++)
		 {
			 result+=(A[i]^B[i]);
		 }
		 return result.toCharArray();
	 }
	 //S盒子
	 public char[] DES_Box(char[]xorR)
	 {   
		 char tS[][]=new char[8][6];
		 String sr = new String();
		 for(int i=0;i<8;i++)
		 {  
			 for(int j=0;j<6;j++)
			 { 
				 tS[i][j]=xorR[i*6+j];
			 }
			 //S盒子的行号
			 String col=String.valueOf(tS[i][0])+String.valueOf(tS[i][5]);
			 int p=Integer.parseInt(col,2);
			 //S盒子的列号
			 int q=Integer.parseInt((String.valueOf(tS[i][1])+String.valueOf(tS[i][2])+String.valueOf(tS[i][3])+String.valueOf(tS[i][4])),2);
			 sr+=String.format("%04d",Integer.parseInt(Integer.toBinaryString(rules.DES_Box[i][p][q])));
		 }
		 return sr.toCharArray();
	 }
	 //p表置换
	 public char[] DES_P_Transform(char[]sr)
	 {
		 char[]tsR=new char[32];
		 for(int i=0;i<32;i++)
		 {
			 tsR[i]=sr[rules.P_Repl[i]-1];
		 }
		 return tsR;
	 }
	 //IP逆转
	 public char[] DES_IPFinal_Transform(char R[])
	 {
		 char[] tR=new char[64];
		 for(int i=0;i<64;i++)
		 {
			 tR[i]=R[rules.IP_FinRep[i]-1];
		 }
		 return tR;
	 }
	 //轮置换
	 public char[] DES_Turn_Repl(char[]R,char[]K)
	 {   
		 char[]eR=DES_EP_Extend(R);
		// System.out.println("EP:"+new String(eR));
		 char[]xor=DES_XOR(eR,K);
		 //System.out.println("XOR:"+new String(xor));
		 char[]box=DES_Box(xor);
		// System.out.println("box:"+new String(box));
		 char[]pR=DES_P_Transform(box);
		 //System.out.println("pR:"+new String(pR));
		 return pR;
	 }
	 //加密
	 public String encrypt(char[]M,char[]K)
	 {   
		 M=DES_IPinitial_Transform(M);
		 //System.out.println("IP====:"+new String(M));
		 char[][]key=Get_Key(K);
		 //System.out.println("key:"+new String(K));
		 char[][]left=new char[17][32];
		 char[][]right=new char[17][32];
		 char[]temp=new char[32];
		 char[]res=new char[64];
		 for(int i=0;i<32;i++)
		 {
		 left[0][i]=M[i];
		 right[0][i]=M[i+32];
		 }
		 
		 for(int i=0;i<16;i++)
		 {   
			 temp=right[i];
		     right[i+1]=DES_XOR(left[i],DES_Turn_Repl(right[i],key[i]));
		     left[i+1]=temp;
		 }
		 for (int i = 0; i < 32; i++){
			 res[i] = right[16][i];
	            res[i+32] =left[16][i];
	        }
		 char []ciper=DES_IPFinal_Transform(res);
		 return new String(ciper);
	 }
	 
	 //解密
	 public String decrypt(char[]M,char[]K)
	 {
		 M=DES_IPinitial_Transform(M);
		 char[][]key=Get_Key(K);
		 char[][]left=new char[17][32];
		 char[][]right=new char[17][32];
		 char[]temp=new char[32];
		 char[]res=new char[64];
		 for(int i=0;i<32;i++)
		 {
		 left[0][i]=M[i];
		 right[0][i]=M[i+32];
		 }
		 for(int i=0;i<16;i++)
		 {   
			 temp=right[i];
		     right[i+1]=DES_XOR(left[i],DES_Turn_Repl(right[i],key[15-i]));
		     left[i+1]=temp;
		 }
		 for (int i = 0; i < 32; i++){
	            res[i] = right[16][i];
	            res[i+32] =left[16][i];
	        }
		 char []ciper=DES_IPFinal_Transform(res);
		 return new String(ciper);
	 }
	 
	 /************加密************/
	 public String enc(String s,String k) throws UnsupportedEncodingException
	 {  
	    String encodedText = Base64.encode(s);
	    String temp="";
	    String ciphertext="";
	    String key=toBinary(k);
	    String s1=toBinary(encodedText);
	    
		if((s1.length()%64)!=0)
		{   
		    for(int m=s1.length()%64;m<64;m++)
		    {
			  s1=s1+"0";
		    }
		}
		for(int j=0;j<s1.length();j=j+64)
		{   
			   for(int n=0;n<64;n++)
				{
			      temp+=s1.charAt(j+n);
				}
				ciphertext+=encrypt(temp.toCharArray(),key.toCharArray());
		        temp="";
		}

		String s2=two2hex(ciphertext);
	    return s2;
	 }
	
	 /************解密************/
	 public String dec(String s,String k) throws UnsupportedEncodingException
	 {  
	    String s1=hexString2binaryString(s);
	    //String s1= NewToBinary(s); 
		String key=toBinary(k);
		String temp=""; 
	    String ciphertext=""; 
		for(int j=0;j<s1.length();j=j+64)
		{   
			   for(int n=0;n<64;n++)
				{
			      temp+=s1.charAt(j+n);
				}
				ciphertext+=decrypt(temp.toCharArray(),key.toCharArray());
		        temp="";
		}
		
		ciphertext=binaryToStr(ciphertext);
		ciphertext=Base64.decode(ciphertext);
	    return ciphertext;
	 }
	 public static String two2hex(String bString){
	        if (bString == null || bString.equals("") || bString.length() % 8 != 0)  
	            return null;  
	        StringBuffer tmp = new StringBuffer();  
	        int iTmp = 0;  
	        for (int i = 0; i < bString.length(); i += 4)  
	        {  
	            iTmp = 0;  
	            for (int j = 0; j < 4; j++)  
	            {  
	                iTmp += Integer.parseInt(bString.substring(i + j, i + j + 1)) << (4 - j - 1);  
	            }  
	            tmp.append(Integer.toHexString(iTmp));  
	        }  
	        return tmp.toString();  
	    }  
	 
	 public static String hexString2binaryString(String hexString)
	    {
	        if (hexString == null || hexString.length() % 2 != 0)
	            return null;
	        String bString = "", tmp;
	        for (int i = 0; i < hexString.length(); i++)
	        {
	            tmp = "0000"
	                    + Integer.toBinaryString(Integer.parseInt(hexString
	                            .substring(i, i + 1), 16));
	            bString += tmp.substring(tmp.length() - 4);
	        }
	        return bString;
	    }
	 
	 public String toBinary(String str) throws UnsupportedEncodingException{
		    char[] strChar=str.toCharArray();
		    String result="";
		    for(int i=0;i<strChar.length;i++){
		        result +=String.format("%08d",Integer.parseInt(Integer.toBinaryString(strChar[i])));
		    }
		    return result;
		}
	 
     public String binaryToStr(String binary){
			String temp="";
			String result="";
			for(int i=0;i<binary.length();i++){
				if(((i%8==0)&&(i!=0)))
				{   if(Integer.parseInt(temp,2)!=0)
					result+=((char)Integer.parseInt(temp,2));
					temp="";
				}
				else if(i==binary.length()-1)
				{
					temp+=binary.charAt(i);
					if(Integer.parseInt(temp,2)!=0)
					result+=((char)Integer.parseInt(temp,2));
				}
				temp+=binary.charAt(i);
		    }
	        return String.valueOf(result);  
	    }  
}
