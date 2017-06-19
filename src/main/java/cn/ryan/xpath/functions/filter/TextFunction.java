package cn.ryan.xpath.functions.filter;

import org.jsoup.nodes.Element;

/***
 * 获取元素自己的子文本
 * 
 * @author RuiStatham Jun 19, 2017 2:37:51 PM
 */
public class TextFunction implements FilterFunction {

	@Override
	public String getName() {
		return "text";
	}

	@Override
	public Object function(Element e) {
		return e.ownText();
	}

}
