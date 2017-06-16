package cn.ryan.utils;

import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.ryan.xpath.model.XpathNode;

public interface Functions {
	List<XpathNode> href(Elements context);

	List<XpathNode> src(Elements context);

	List<XpathNode> text(Elements context);

	List<XpathNode> allText(Elements context);

	List<XpathNode> html(Elements context);

	List<XpathNode> outerHtml(Elements context);

	List<XpathNode> node(Elements context);

	List<XpathNode> num(Elements context);

	String text(Element e);

	String allText(Element e);

	boolean last(Element e);

	boolean first(Element e);

	int position(Element e);

	boolean contains(String left,String right);
}
