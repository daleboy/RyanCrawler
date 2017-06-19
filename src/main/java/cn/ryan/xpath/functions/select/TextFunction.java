package cn.ryan.xpath.functions.select;

import java.util.LinkedList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.ryan.xpath.model.XpathNode;

/***
 * 只获取节点自身的子文本
 * 
 * @author RuiStatham Jun 19, 2017 2:21:50 PM
 */
public class TextFunction implements SelectFunction {

	@Override
	public String getName() {
		return "text";
	}

	@Override
	public List<XpathNode> function(Elements context) {
		List<XpathNode> res = new LinkedList<XpathNode>();
		if (context != null && context.size() > 0) {
			for (Element e : context) {
				if (e.nodeName().equals("script")) {
					res.add(XpathNode.t(e.data()));
				} else {
					res.add(XpathNode.t(e.ownText()));
				}
			}
		}
		return res;
	}

}
