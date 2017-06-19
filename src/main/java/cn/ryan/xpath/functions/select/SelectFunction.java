package cn.ryan.xpath.functions.select;

import java.util.List;

import org.jsoup.select.Elements;

import cn.ryan.xpath.functions.FunctionName;
import cn.ryan.xpath.model.XpathNode;

/***
 * 所有的函数都是基于该接口，如需扩展按形式添加即可
 * 
 * @author RuiStatham Jun 19, 2017 2:19:16 PM
 */
public interface SelectFunction extends FunctionName {
	List<XpathNode> function(Elements context);
}
