/**
 * 
 */
package tcpchatroom.datagram;

/**
 * @author xuxiaohui
 *
 */
public class ServerDataGramAnalyzer {
	public final static String delimiter = "&&&";//**************************ÓĞÎÊÌâ
	public final static String nameDelimiter = "###";
	
		public static short getType(String datagram){
			String []stringarr = datagram.split(delimiter);
			return Short.parseShort(stringarr[0]);
		}
		public static String getName(String datagram){
			String []stringarr = datagram.split(delimiter);
			return stringarr[1];
		}
		public static String []getNames(String datagram){
			String []stringarr = datagram.split(delimiter);
			return stringarr[1].split(nameDelimiter);
		}
		public static String getState(String datagram){
			String []stringarr = datagram.split(delimiter);
			return stringarr[2];
		}
		public static String getMessage(String datagram){
			String []stringarr = datagram.split(delimiter);
			if (stringarr.length > 2)
				return stringarr[2];
			else
				return "";
		}
}
