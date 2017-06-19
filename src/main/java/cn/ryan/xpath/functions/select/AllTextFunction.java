package cn.ryan.xpath.functions.select;

import java.util.LinkedList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.ryan.xpath.model.XpathNode;

/***
 * 递归获取节点内全部的纯文本
 * 
 * @author RuiStatham Jun 19, 2017 2:22:42 PM
 */
public class AllTextFunction implements SelectFunction {

	@Override
	public String getName() {
		return "allText";
	}

	@Override
	public List<XpathNode> function(Elements context) {
		List<XpathNode> res = new LinkedList<XpathNode>();
		if (context != null && context.size() > 0) {
			for (Element e : context) {
				res.add(XpathNode.t(e.text()));
			}
		}
		return res;
	}

}
