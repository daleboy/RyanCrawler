package cn.ryan.xpath.core;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public interface AxisSelector {
	Elements self(Element e);

	Elements parent(Element e);

	Elements child(Element e);

	Elements ancestor(Element e);

	Elements ancestorOrSelf(Element e);

	Elements descendant(Element e);

	Elements descendantOrSelf(Element e);

	Elements precedingSibling(Element e);

	Elements precedingSiblingOne(Element e);

	Elements followingSibling(Element e);

	Elements followingSiblingOne(Element e);

	Elements sibling(Element e);

}
