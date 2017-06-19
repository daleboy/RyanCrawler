package cn.ryan.xpath.functions.axis;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/***
 * 获取父节点
 * @author RuiStatham
 * Jun 19, 20171:38:51 PM
 */
public class ParentAxis implements AxisFunction {

	@Override
	public Elements function(Element e) {
		return new Elements(e.parent());
	}

	@Override
	public String getName() {
		return "parent";
	}
}
