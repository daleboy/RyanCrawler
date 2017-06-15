package cn.ryan.xpath.exception;
/**
 * 使用不存在的轴语法则抛出此异常
 */
public class NoSuchAxisException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3475691435715757473L;

	public NoSuchAxisException(String msg){
        super(msg);
    }
}
