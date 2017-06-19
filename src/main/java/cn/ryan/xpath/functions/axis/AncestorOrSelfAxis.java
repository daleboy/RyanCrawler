package cn.ryan.xpath.functions.axis;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/***
 * 全部祖先节点和自身节点
 * 
 * @author RuiStatham Jun 19, 20171:49:41 PM
 */
public class AncestorOrSelfAxis implements AxisFunction {

	@Override
	public String getName() {
		return "ancestorOrSelf";
	}

	@Override
	public Elements function(Element e) {
		Elements rs = e.parents();
		rs.add(e);
		return rs;
	}

}
