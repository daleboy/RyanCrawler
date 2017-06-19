package cn.ryan.xpath.functions.axis;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/***
 * 取自身
 * @author RuiStatham
 * Jun 19, 20171:39:18 PM
 */
public class SelfAxis implements AxisFunction{

	@Override
	public Elements function(Element e) {
		return new Elements(e);
	}

	@Override
	public String getName() {
		return "self";
	}

}
