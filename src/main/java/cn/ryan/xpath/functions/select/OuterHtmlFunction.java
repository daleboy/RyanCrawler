package cn.ryan.xpath.functions.select;

import java.util.LinkedList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.ryan.xpath.model.XpathNode;

/***
 * 获取全部节点的 包含节点本身在内的全部html
 * 
 * @author RuiStatham Jun 19, 2017 2:26:24 PM
 */
public class OuterHtmlFunction implements SelectFunction {

	@Override
	public String getName() {
		return "outerHtml";
	}

	@Override
	public List<XpathNode> function(Elements context) {
		List<XpathNode> res = new LinkedList<XpathNode>();
		if (context != null && context.size() > 0) {
			for (Element e : context) {
				res.add(XpathNode.t(e.outerHtml()));
			}
		}
		return res;
	}

}
