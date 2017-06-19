package cn.ryan.xpath.functions.filter;

import org.jsoup.nodes.Element;

/**
 * 获取元素下面的全部文本
 * 
 * @author RuiStatham Jun 19, 2017 2:38:54 PM
 */
public class AllTextFunction implements FilterFunction {

	@Override
	public String getName() {
		return "allText";
	}

	@Override
	public Object function(Element e) {
		return e.text();
	}

}
