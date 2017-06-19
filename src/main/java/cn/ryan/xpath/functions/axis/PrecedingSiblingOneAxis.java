package cn.ryan.xpath.functions.axis;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/***
 * 返回前一个同胞节点（扩展），语法 preceding-sibling-one
 * 
 * @author RuiStatham Jun 19, 20171:56:02 PM
 */
public class PrecedingSiblingOneAxis implements AxisFunction {

	@Override
	public String getName() {
		return "precedingSiblingOne";
	}

	@Override
	public Elements function(Element e) {
		Elements rs = new Elements();
		if (e.previousElementSibling() != null) {
			rs.add(e.previousElementSibling());
		}
		return rs;
	}

}
