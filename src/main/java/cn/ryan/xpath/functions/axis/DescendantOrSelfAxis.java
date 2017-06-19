package cn.ryan.xpath.functions.axis;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/***
 * 全部子代节点和自身
 * 
 * @author RuiStatham Jun 19, 20171:54:09 PM
 */
public class DescendantOrSelfAxis implements AxisFunction {

	@Override
	public String getName() {
		return "descendantOrSelf";
	}

	@Override
	public Elements function(Element e) {
		Elements rs = e.getAllElements();
		rs.add(e);
		return rs;
	}

}
