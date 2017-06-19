package cn.ryan.xpath.functions.axis;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/***
 * 节点后面的全部同胞节点following-sibling
 * 
 * @author RuiStatham Jun 19, 20171:57:20 PM
 */
public class FollowingSiblingAxis implements AxisFunction {

	@Override
	public String getName() {
		return "followingSibling";
	}

	@Override
	public Elements function(Element e) {
		Elements rs = new Elements();
		Element tmp = e.nextElementSibling();
		while (tmp != null) {
			rs.add(tmp);
			tmp = tmp.nextElementSibling();
		}
		return rs;
	}

}
