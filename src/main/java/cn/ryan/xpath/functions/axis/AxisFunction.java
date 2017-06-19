package cn.ryan.xpath.functions.axis;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.ryan.xpath.functions.FunctionName;

/***
 * 所有的轴的实现都是基于该接口，如需扩展按形式添加即可
 * 
 * @author RuiStatham Jun 19, 20171:45:24 PM
 */
public interface AxisFunction extends FunctionName {
	Elements function(Element e);
}
