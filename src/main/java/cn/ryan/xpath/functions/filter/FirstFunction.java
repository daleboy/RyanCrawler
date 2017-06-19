package cn.ryan.xpath.functions.filter;

import org.jsoup.nodes.Element;

import cn.ryan.xpath.util.CommonUtil;

/***
 * 判断一个元素是不是同名同胞中的第一个
 * 
 * @author RuiStatham Jun 19, 2017 2:40:19 PM
 */
public class FirstFunction implements FilterFunction {

	@Override
	public String getName() {
		return "first";
	}

	@Override
	public Object function(Element e) {
		return CommonUtil.getElIndexInSameTags(e) == 1;
	}

}
