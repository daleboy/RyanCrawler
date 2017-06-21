package cn.ryan.xpath.model;

import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import cn.ryan.xpath.core.XpathEvaluator;
import cn.ryan.xpath.exception.NoSuchAxisException;
import cn.ryan.xpath.exception.NoSuchFunctionException;

public class XpathDocument {
	private Elements elements;
	private XpathEvaluator xpathEva = new XpathEvaluator();;

	private XpathDocument(Object o) {
		if (o instanceof Document) {
			elements = ((Document) o).children();
		} else if (o instanceof String) {
			elements = Jsoup.parse((String) o).children();
		} else if (o instanceof Elements) {
			elements = (Elements) o;
		} else {
			Validate.isNotExists("Unsupported type");
		}
	}

	private XpathDocument() {
	}

	public static XpathDocument init(Object o) {
		return new XpathDocument(o);
	}

	public List<Object> xpathList(String xpath) {
		List<Object> res = new LinkedList<Object>();
		try {
			List<XpathNode> jns = xpathEva.xpathParser(xpath, elements);
			for (XpathNode j : jns) {
				if (j.isText()) {
					res.add(j.getTextVal());
				} else {
					res.add(j.getElement());
				}
			}
		} catch (Exception e) {
			String msg = "please check the xpath syntax";
			if (e instanceof NoSuchAxisException || e instanceof NoSuchFunctionException) {
				msg = e.getMessage();
			}
			throw new IllegalArgumentException(msg);
		}
		return res;
	}

	public List<XpathNode> xpath(String xpath) {
		try {
			return xpathEva.xpathParser(xpath, elements);
		} catch (Exception e) {
			String msg = "please check the xpath syntax";
			if (e instanceof NoSuchAxisException || e instanceof NoSuchFunctionException) {
				msg = e.getMessage();
			}
			throw new IllegalArgumentException(msg);
		}
	}

	public Object xpathObj(String xpath) {
		XpathNode jxNode = xpathNode(xpath);
		if (jxNode != null) {
			if (jxNode.isText()) {
				return jxNode.getTextVal();
			} else {
				return jxNode.getElement();
			}
		}
		return null;
	}

	public XpathNode xpathNode(String xpath) {
		List<XpathNode> jxNodeList = xpath(xpath);
		if (jxNodeList != null && jxNodeList.size() > 0) {
			return jxNodeList.get(0);
		}
		return null;
	}
}
