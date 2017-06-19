package cn.ryan.xpath.functions.filter;

import org.jsoup.nodes.Element;

import cn.ryan.xpath.util.CommonUtil;

/***
 * 返回一个元素在同名兄弟节点中的位置
 * 
 * @author RuiStatham Jun 19, 2017 2:41:12 PM
 */
public class PositionFunction implements FilterFunction {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "position";
	}

	@Override
	public Object function(Element e) {
		return CommonUtil.getElIndexInSameTags(e);
	}

}
