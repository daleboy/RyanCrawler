package cn.ryan.xpath.functions.axis;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/***
 * 全部子代节点 儿子，孙子，孙子的儿子...
 * 
 * @author RuiStatham Jun 19, 20171:51:00 PM
 */
public class DescendantAxis implements AxisFunction {

	@Override
	public String getName() {
		return "descendant";
	}

	@Override
	public Elements function(Element e) {
		return e.getAllElements();
	}

}
