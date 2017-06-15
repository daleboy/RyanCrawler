package cn.ryan.xpath.model;

import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import cn.ryan.xpath.core.XpathEvaluator;
import cn.ryan.xpath.exception.NoSuchAxisException;
import cn.ryan.xpath.exception.NoSuchFunctionException;

public class XpathDocument {
	private Elements elements;
	private XpathEvaluator xpathEva = new XpathEvaluator();

	public XpathDocument(Document doc) {
		elements = doc.children();
	}

	public XpathDocument(String html) {
		elements = Jsoup.parse(html).children();
	}

	public XpathDocument(Elements els) {
		elements = els;
	}

	public List<Object> sel(String xpath) {
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

	public List<XpathNode> selN(String xpath) {
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

	public Object selOne(String xpath) {
		XpathNode jxNode = selNOne(xpath);
		if (jxNode != null) {
			if (jxNode.isText()) {
				return jxNode.getTextVal();
			} else {
				return jxNode.getElement();
			}
		}
		return null;
	}

	public XpathNode selNOne(String xpath) {
		List<XpathNode> jxNodeList = selN(xpath);
		if (jxNodeList != null && jxNodeList.size() > 0) {
			return jxNodeList.get(0);
		}
		return null;
	}
}
