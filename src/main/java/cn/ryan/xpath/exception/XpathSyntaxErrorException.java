package cn.ryan.xpath.exception;
 
public class XpathSyntaxErrorException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7630443312812669478L;

	public XpathSyntaxErrorException(String msg){
        super(msg);
    }
}
