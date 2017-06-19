package cn.ryan.xpath.functions.axis;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/***
 * 节点前面的全部同胞节点，preceding-sibling
 * @author RuiStatham
 * Jun 19, 20171:54:59 PM
 */
public class PrecedingSiblingAxis implements AxisFunction{

	@Override
	public String getName() {
		return "precedingSibling";
	}

	@Override
	public Elements function(Element e) {
		Elements rs = new Elements();
		Element tmp = e.previousElementSibling();
		while (tmp != null) {
			rs.add(tmp);
			tmp = tmp.previousElementSibling();
		}
		return rs;
	}

}
