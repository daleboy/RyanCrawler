package cn.ryan.xpath.model;

import cn.ryan.xpath.exception.XpathSyntaxErrorException;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * XPath提取后的
 * 
 */
public class XpathNode {

	private Element element;
	private boolean isText;
	private String textVal;

	public Element getElement() {
		return element;
	}

	public XpathNode setElement(Element element) {
		this.element = element;
		return this;
	}

	public boolean isText() {
		return isText;
	}

	public XpathNode setText(boolean text) {
		isText = text;
		return this;
	}

	public String getTextVal() {
		return textVal;
	}

	public XpathNode setTextVal(String textVal) {
		this.textVal = textVal;
		return this;
	}

	public List<XpathNode> sel(String xpath) throws XpathSyntaxErrorException {
		if (element == null) {
			return null;
		}
		XpathDocument doc = XpathDocument.init(new Elements(element));
		return doc.xpath(xpath);
	}

	public static XpathNode e(Element element) {
		XpathNode n = new XpathNode();
		n.setElement(element).setText(false);
		return n;
	}

	public static List<XpathNode> e(Elements element) {
		List<XpathNode> list = new ArrayList<>();
		for (Element e : element) {
			list.add(e(e).setText(false));
		}
		return list;
	}

	public static XpathNode t(String txt) {
		XpathNode n = new XpathNode();
		n.setTextVal(txt).setText(true);
		return n;
	}

	public static List<XpathNode> t(List<String> eList) {
		List<XpathNode> list = new ArrayList<>();
		for (String str : eList) {
			list.add(t(str).setText(false));
		}
		return list;
	}

	@Override
	public String toString() {
		if (isText) {
			return textVal;
		} else {
			return element.toString();
		}
	}
}
