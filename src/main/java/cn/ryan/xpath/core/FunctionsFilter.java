package cn.ryan.xpath.core;

import org.jsoup.nodes.Element;

import cn.ryan.xpath.util.CommonUtil;

/***
 * 函数过滤器
 * 
 * @author RuiStatham
 *
 */
public class FunctionsFilter extends FunctionsTrunk {
	/**
	 * 获取元素自己的子文本
	 * 
	 * @param e
	 * @return
	 */
	public String text(Element e) {
		return e.ownText();
	}

	/**
	 * 获取元素下面的全部文本
	 * 
	 * @param e
	 * @return
	 */
	public String allText(Element e) {
		return e.text();
	}

	/**
	 * 判断一个元素是不是最后一个同名同胞中的
	 * 
	 * @param e
	 * @return
	 */
	public boolean last(Element e) {
		return CommonUtil.getElIndexInSameTags(e) == CommonUtil.sameTagElNums(e);
	}

	/**
	 * 判断一个元素是不是同名同胞中的第一个
	 * 
	 * @param e
	 * @return
	 */
	public boolean first(Element e) {
		return CommonUtil.getElIndexInSameTags(e) == 1;
	}

	/**
	 * 返回一个元素在同名兄弟节点中的位置
	 * 
	 * @param e
	 * @return
	 */
	public int position(Element e) {
		return CommonUtil.getElIndexInSameTags(e);
	}

	/**
	 * 判断是否包含
	 * 
	 * @param left
	 * @param right
	 * @return
	 */
	public boolean contains(String left, String right) {
		return left.contains(right);
	}
}
