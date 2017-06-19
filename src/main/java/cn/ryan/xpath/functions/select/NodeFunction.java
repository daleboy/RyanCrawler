package cn.ryan.xpath.functions.select;

import java.util.LinkedList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.ryan.xpath.model.XpathNode;

/***
 * 获取全部节点
 * 
 * @author RuiStatham Jun 19, 2017 2:27:07 PM
 */
public class NodeFunction implements SelectFunction {

	@Override
	public String getName() {
		return "node";
	}

	@Override
	public List<XpathNode> function(Elements context) {
		List<XpathNode> res = new LinkedList<XpathNode>();
		if (context != null && context.size() > 0) {
			for (Element e : context) {
				res.add(XpathNode.t(e.html()));
			}
		}
		return res;
	}

}
