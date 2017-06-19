package cn.ryan.xpath.functions.axis;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/***
 * 全部同胞（扩展）
 * 
 * @author RuiStatham Jun 19, 2017 2:05:23 PM
 */
public class SiblingAxis implements AxisFunction {

	@Override
	public String getName() {
		return "sibling";
	}

	@Override
	public Elements function(Element e) {
		return e.siblingElements();
	}

}
