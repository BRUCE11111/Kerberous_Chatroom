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
		 //输入文件
		// File srcFile = new File("E:/tmp/testvoice.txt");
		 //使用包装字符流读取文件
		// BufferedReader br = new BufferedReader(new FileReader(srcFile));
		// String content = br.readLine();
        try {  
            // 音量 0-100  
            sap.setProperty("Volume", new Variant(100));  
            // 语音朗读速度 -10 到 +10  
            sap.setProperty("Rate", new Variant(-2));  
            // 获取执行对象  
            Dispatch sapo = sap.getObject();  
            // 执行朗读  
          //  while (msg != null) {
    			Dispatch.call(sapo, "Speak", new Variant(msg));  
    			//content = br.readLine();
    		//}
            // 关闭执行对象  
            sapo.safeRelease();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally { 
        	//br.close();
            // 关闭应用程序连接  
            sap.safeRelease();  
        }  
	}
}