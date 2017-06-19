package cn.ryan.xpath.functions.filter;

import org.jsoup.nodes.Element;

import cn.ryan.xpath.util.CommonUtil;

/***
 * 判断一个元素是不是最后一个同名同胞中的
 * 
 * @author RuiStatham Jun 19, 2017 2:39:57 PM
 */
public class LastFunction implements FilterFunction {

	@Override
	public String getName() {
		return "last";
	}

	@Override
	public Object function(Element e) {
		return CommonUtil.getElIndexInSameTags(e) == CommonUtil.sameTagElNums(e);
	}

}
