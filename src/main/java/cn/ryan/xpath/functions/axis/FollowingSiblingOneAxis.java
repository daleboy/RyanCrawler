package cn.ryan.xpath.functions.axis;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/***
 * 返回下一个同胞节点(扩展) 语法 following-sibling-one
 * 
 * @author RuiStatham Jun 19, 2017 2:03:37 PM
 */
public class FollowingSiblingOneAxis implements AxisFunction {

	@Override
	public String getName() {
		return "followingSiblingOne";
	}

	@Override
	public Elements function(Element e) {
		Elements rs = new Elements();
		if (e.nextElementSibling() != null) {
			rs.add(e.nextElementSibling());
		}
		return rs;
	}

}
