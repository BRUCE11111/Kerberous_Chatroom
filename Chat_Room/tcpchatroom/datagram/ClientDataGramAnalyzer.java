/**
 * 
 */
package tcpchatroom.datagram;

/**
 * @author xuxiaohui
 *
 */
public class ClientDataGramAnalyzer {
	//分析数据
	public final static String delimiter = "&&&";//**************************有问题
	public final static String nameDelimiter = "###";
	
		public static short getType(String datagram){
			String []stringarr = datagram.split(delimiter);
			return Short.parseShort(stringarr[0]);
		}
		public static String getName(String datagram){
			String []stringarr = datagram.split(delimiter);
			return stringarr[1];
		}
		public static String getPassword(String datagram){
			String []stringarr = datagram.split(delimiter);
			return stringarr[2];
		}
		public static String getState(String datagram){
			String []stringarr = datagram.split(delimiter);
			return stringarr[3];
		}
		public static String getMessage(String datagram){
			String []stringarr = datagram.split(delimiter);
			if(stringarr.length < 3)// 防止用户输入的内容是空
				return "";
			return stringarr[2];
		}
		public static String[] getAccepters(String datagram){
			String []stringarr = datagram.split(delimiter);
			return stringarr[1].split(nameDelimiter);
		}
}
