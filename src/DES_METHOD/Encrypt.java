package DES_METHOD;

public class Encrypt {
 public Rules rules;

 //√ÿ‘ø÷√ªª1
 public char[] Key_Replace1(char[]K)
 {
	 char temp[]=new char[56];
	 for(int i=0;i<56;i++)
	 {
		 temp[i]=K[rules.KEY_Repl[i]-1];
	 }
	 return temp;
 }
 //…˙≥…◊”√ÿ‘ø0
 private char[] LoopKey(char[]K,int offset)
 {
	 String k1=new String();
	 String k2=new String();
	 String k=new String(K);
	 k1=(k.substring(0,28)+k.substring(0,2)).substring(offset, 28+offset);
	 k2=(k.substring(28,56)+k.substring(28,30)).substring(offset, 28+offset);
	 return (k1+k2).toCharArray();
 }
 //√ÿ‘ø÷√ªª2
 public char[] Key_Replace2(char[]K)
 {
	 char temp[]=new char[48];
	 for(int i=0;i<48;i++)
	 {
		 temp[i]=K[rules.KEY_Rep2[i]-1];
	 }
	 return temp;
 }
 //√ÿ‘ø…˙≥…
 public char[][] Get_Key(char[]M)
 {   char[]k1=new char[56];
     char[][]tk=new char[16][56];
     char[][]tkk=new char[16][48];
	 k1=Key_Replace1(M);
	 tk[0]=LoopKey(k1,rules.KEY_Move[0]);
	 for(int i=1;i<16;i++)
	 {
	 tk[i]=LoopKey(tk[i-1],rules.KEY_Move[i]);
	 }
	 for(int i=0;i<16;i++)
	 {
		 tkk[i]=Key_Replace2(tk[i]);
		 
	 }
	 return tkk;
 }
 
}
