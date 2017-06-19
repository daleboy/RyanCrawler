package cn.ryan.xpath.functions.axis;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/***
 * 全部祖先节点 父亲，爷爷 ， 爷爷的父亲...
 * @author RuiStatham
 * Jun 19, 20171:41:51 PM
 */
public class AncestorAxis implements AxisFunction {

	@Override
	public String getName() {
		return "ancestor";
	}

	@Override
	public Elements function(Element e) {
		return e.parents();
	}

}
