package cn.ryan.xpath.functions.select;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.ryan.xpath.model.XpathNode;

/***
 * 抽取节点自有文本中全部数字
 * 
 * @author RuiStatham Jun 19, 2017 2:28:32 PM
 */
public class NumFunction implements SelectFunction {

	@Override
	public String getName() {
		return "num";
	}

	@Override
	public List<XpathNode> function(Elements context) {
		List<XpathNode> res = new LinkedList<XpathNode>();
		if (context != null) {
			Pattern pattern = Pattern.compile("\\d+");
			for (Element e : context) {
				Matcher matcher = pattern.matcher(e.ownText());
				if (matcher.find()) {
					res.add(XpathNode.t(matcher.group()));
				}
			}
		}
		return res;
	}

}
