package cn.ryan.xpath.functions.select;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.ryan.xpath.model.XpathNode;

/***
 * 获取节点内全部绝对地址src
 * 
 * @author RuiStatham Jun 19, 2017 2:21:06 PM
 */
public class SrcFunction implements SelectFunction {

	@Override
	public String getName() {
		return "src";
	}

	@Override
	public List<XpathNode> function(Elements context) {
		List<String> srcList = new ArrayList<>();
		for (Element element : context) {
			srcList.add(element.attr("abs:src"));
		}
		return XpathNode.t(srcList);
	}

}
