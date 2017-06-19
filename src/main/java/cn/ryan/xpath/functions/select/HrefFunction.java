package cn.ryan.xpath.functions.select;

import java.util.List;

import org.jsoup.select.Elements;

import cn.ryan.xpath.model.XpathNode;

/***
 * 获取节点内全部绝对地址超链接
 * @author RuiStatham
 * Jun 19, 2017 2:18:57 PM
 */
public class HrefFunction implements SelectFunction {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "href";
	}

	@Override
	public List<XpathNode> function(Elements context) {
		 return XpathNode.e(context.absLinks());
	}

}
