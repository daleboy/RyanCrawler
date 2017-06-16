package cn.ryan.xpath.core;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/***
 * 轴选择（扩展）
 * 
 * @author RuiStatham
 *
 */
public class AxisSelectorExpand extends AxisSelectorTrunk {

	/**
	 * 返回下一个同胞节点(扩展) 语法 following-sibling-one
	 * 
	 * @param e
	 * @return
	 */
	public Elements followingSiblingOne(Element e) {
		Elements rs = new Elements();
		if (e.nextElementSibling() != null) {
			rs.add(e.nextElementSibling());
		}
		return rs;
	}

	/**
	 * 全部同胞（扩展）
	 * 
	 * @param e
	 * @return
	 */
	public Elements sibling(Element e) {
		return e.siblingElements();
	}

}
