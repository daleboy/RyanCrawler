package cn.ryan.xpath.functions.axis;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/***
 * 取子节点
 * @author RuiStatham
 * Jun 19, 20171:40:24 PM
 */
public class ChildAxis implements AxisFunction {

	@Override
	public String getName() {
		return "child";
	}

	@Override
	public Elements function(Element e) {
		return e.children();
	}

}
