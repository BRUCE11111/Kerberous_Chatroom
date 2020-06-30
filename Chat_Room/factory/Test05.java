package factory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class Test05 {
	public  void voice(String msg) throws IOException {
		 ActiveXComponent sap = new ActiveXComponent("Sapi.SpVoice"); 
		 //�����ļ�
		// File srcFile = new File("E:/tmp/testvoice.txt");
		 //ʹ�ð�װ�ַ�����ȡ�ļ�
		// BufferedReader br = new BufferedReader(new FileReader(srcFile));
		// String content = br.readLine();
        try {  
            // ���� 0-100  
            sap.setProperty("Volume", new Variant(100));  
            // �����ʶ��ٶ� -10 �� +10  
            sap.setProperty("Rate", new Variant(-2));  
            // ��ȡִ�ж���  
            Dispatch sapo = sap.getObject();  
            // ִ���ʶ�  
          //  while (msg != null) {
    			Dispatch.call(sapo, "Speak", new Variant(msg));  
    			//content = br.readLine();
    		//}
            // �ر�ִ�ж���  
            sapo.safeRelease();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally { 
        	//br.close();
            // �ر�Ӧ�ó�������  
            sap.safeRelease();  
        }  
	}
}