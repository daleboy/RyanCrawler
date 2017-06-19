package cn.ryan.xpath.functions.filter;

import org.jsoup.nodes.Element;

import cn.ryan.xpath.functions.FunctionName;

/***
 * 所有的谓语过滤的实现都是基于该接口，如需扩展按形式添加即可
 * 
 * @author RuiStatham Jun 19, 2017 2:31:39 PM
 */
public interface FilterFunction extends FunctionName {
	Object function(Element e);
}
