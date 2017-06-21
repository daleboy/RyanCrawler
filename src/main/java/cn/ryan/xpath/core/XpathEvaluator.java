package cn.ryan.xpath.core;

import cn.ryan.utils.StringUtils;
import cn.ryan.xpath.exception.NoSuchAxisException;
import cn.ryan.xpath.exception.NoSuchFunctionException;
import cn.ryan.xpath.functions.RegisterEvaluator;
import cn.ryan.xpath.model.XpathNode;
import cn.ryan.xpath.model.Node;
import cn.ryan.xpath.model.Predicate;
import cn.ryan.xpath.util.CommonUtil;
import cn.ryan.xpath.util.ScopeEm;

import java.util.LinkedList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class XpathEvaluator {

	public XpathEvaluator() {
	}

	/**
	 * xpath解析器的总入口，同时预处理，如‘|’
	 *
	 * @param xpath
	 * @param root
	 * @return
	 * @throws NoSuchFunctionException
	 * @throws NoSuchAxisException
	 */
	public List<XpathNode> xpathParser(String xpath, Elements root) throws NoSuchAxisException, NoSuchFunctionException {
		if (xpath.contains("|")) {
			List<XpathNode> rs = new LinkedList<XpathNode>();
			String[] chiXpaths = xpath.split("\\|");
			for (String chiXp : chiXpaths) {
				if (chiXp.length() > 0) {
					rs.addAll(evaluate(chiXp.trim(), root));
				}
			}
			return rs;
		} else {
			return evaluate(xpath, root);
		}
	}

	/**
	 * 获取xpath解析语法树
	 *
	 * @param xpath
	 * @return
	 */
	public List<Node> getXpathNodeTree(String xpath) {
		NodeTreeBuilderStateMachine st = new NodeTreeBuilderStateMachine();
		while (st.state != NodeTreeBuilderStateMachine.BuilderState.END) {
			st.state.parser(st, xpath.toCharArray());
		}
		return st.context.xpathTr;
	}

	/**
	 * 根据xpath求出结果
	 *
	 * @param xpath
	 * @param root
	 * @return
	 * @throws NoSuchAxisException
	 * @throws NoSuchFunctionException
	 */
	public List<XpathNode> evaluate(String xpath, Elements root) throws NoSuchAxisException, NoSuchFunctionException {
		try {
			List<XpathNode> res = new LinkedList<XpathNode>();
			Elements context = root;
			List<Node> xpathNodes = getXpathNodeTree(xpath);
			for (int i = 0; i < xpathNodes.size(); i++) {
				Node n = xpathNodes.get(i);
				LinkedList<Element> contextTmp = new LinkedList<Element>();
				if (n.getScopeEm() == ScopeEm.RECURSIVE || n.getScopeEm() == ScopeEm.CURREC) {
					if (n.getTagName().startsWith("@")) {
						for (Element e : context) {
							// 处理上下文自身节点
							String key = n.getTagName().substring(1);
							if (key.equals("*")) {
								res.add(XpathNode.t(e.attributes().toString()));
							} else {
								String value = e.attr(key);
								if (StringUtils.isNotBlank(value)) {
									res.add(XpathNode.t(value));
								}
							}
							// 处理上下文子代节点
							for (Element dep : e.getAllElements()) {
								if (key.equals("*")) {
									res.add(XpathNode.t(dep.attributes().toString()));
								} else {
									String value = dep.attr(key);
									if (StringUtils.isNotBlank(value)) {
										res.add(XpathNode.t(value));
									}
								}
							}
						}
					} else if (n.getTagName().endsWith("()")) {
						// 递归执行方法默认只支持text()
						res.add(XpathNode.t(context.text()));
					} else {
						Elements searchRes = context.select(n.getTagName());
						for (Element e : searchRes) {
							Element filterR = filter(e, n);
							if (filterR != null) {
								contextTmp.add(filterR);
							}
						}
						context = new Elements(contextTmp);
						if (i == xpathNodes.size() - 1) {
							for (Element e : contextTmp) {
								res.add(XpathNode.e(e));
							}
						}
					}

				} else {
					if (n.getTagName().startsWith("@")) {
						for (Element e : context) {
							String key = n.getTagName().substring(1);
							if (key.equals("*")) {
								res.add(XpathNode.t(e.attributes().toString()));
							} else {
								String value = e.attr(key);
								if (StringUtils.isNotBlank(value)) {
									res.add(XpathNode.t(value));
								}
							}
						}
					} else if (n.getTagName().endsWith("()")) {
						res = (List<XpathNode>) callFunc(n.getTagName().substring(0, n.getTagName().length() - 2), context);
					} else {
						for (Element e : context) {
							Elements filterScope = e.children();
							if (StringUtils.isNotBlank(n.getAxis())) {
								filterScope = getAxisScopeEls(n.getAxis(), e);
							}
							for (Element chi : filterScope) {
								Element fchi = filter(chi, n);
								if (fchi != null) {
									contextTmp.add(fchi);
								}
							}
						}
						context = new Elements(contextTmp);
						if (i == xpathNodes.size() - 1) {
							for (Element e : contextTmp) {
								res.add(XpathNode.e(e));
							}
						}
					}
				}
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 元素过滤器
	 *
	 * @param e
	 * @param node
	 * @return
	 * @throws NoSuchFunctionException
	 * @throws NoSuchAxisException
	 */
	public Element filter(Element e, Node node) throws NoSuchFunctionException, NoSuchAxisException {
		if (node.getTagName().equals("*") || node.getTagName().equals(e.nodeName())) {
			if (node.getPredicate() != null && StringUtils.isNotBlank(node.getPredicate().getValue())) {
				Predicate p = node.getPredicate();
				if (p.getOpEm() == null) {
					if (p.getValue().matches("\\d+") && getElIndex(e) == Integer.parseInt(p.getValue())) {
						return e;
					} else if (p.getValue().endsWith("()") && (Boolean) callFilterFunc(p.getValue().substring(0, p.getValue().length() - 2), e)) {
						return e;
					} else if (p.getValue().startsWith("@") && e.hasAttr(StringUtils.substringAfter(p.getValue(), "@"))) {
						return e;
					}
					// todo p.value ~= contains(./@href,'renren.com')
				} else {
					if (p.getLeft().matches("[^/]+\\(\\)")) {
						Object filterRes = p.getOpEm().excute(callFilterFunc(p.getLeft().substring(0, p.getLeft().length() - 2), e).toString(), p.getRight());
						if (filterRes instanceof Boolean && (Boolean) filterRes) {
							return e;
						} else if (filterRes instanceof Integer && e.siblingIndex() == Integer.parseInt(filterRes.toString())) {
							return e;
						}
					} else if (p.getLeft().startsWith("@")) {
						String lValue = e.attr(p.getLeft().substring(1));
						Object filterRes = p.getOpEm().excute(lValue, p.getRight());
						if ((Boolean) filterRes) {
							return e;
						}
					} else {
						// 操作符左边不是函数、属性默认就是xpath表达式了
						List<Element> eltmp = new LinkedList<Element>();
						eltmp.add(e);
						List<XpathNode> rstmp = evaluate(p.getLeft(), new Elements(eltmp));
						if ((Boolean) p.getOpEm().excute(StringUtils.join(rstmp, ""), p.getRight())) {
							return e;
						}
					}
				}
			} else {
				return e;
			}
		}
		return null;
	}

	/**
	 * 调用轴选择器
	 *
	 * @param axis
	 * @param e
	 * @return
	 * @throws NoSuchAxisException
	 */
	public Elements getAxisScopeEls(String axis, Element e) throws NoSuchAxisException {
		try {
			String functionName = CommonUtil.getJMethodNameFromStr(axis);
			return RegisterEvaluator.getAxisFunction(functionName).function(e);
		} catch (Throwable e1) {
			throw new NoSuchAxisException("this axis is not supported,plase use other instead of '" + axis + "'");
		}
	}

	/**
	 * 调用xpath主干上的函数
	 *
	 * @param funcname
	 * @param context
	 * @return
	 * @throws NoSuchFunctionException
	 */
	public Object callFunc(String funcname, Elements context) throws NoSuchFunctionException {
		try {
			return RegisterEvaluator.getSelectFunction(funcname).function(context);
		} catch (Throwable e) {
			throw new NoSuchFunctionException("This function is not supported");
		}
	}

	/**
	 * 调用谓语中函数
	 *
	 * @param funcname
	 * @param e
	 * @return
	 * @throws NoSuchFunctionException
	 */
	public Object callFilterFunc(String funcname, Element e) throws NoSuchFunctionException {
		try {
			return RegisterEvaluator.getFilterFunction(funcname).function(e);
		} catch (Throwable e1) {
			throw new NoSuchFunctionException("This function is not supported");
		}
	}

	public int getElIndex(Element e) {
		if (e != null) {
			return CommonUtil.getElIndexInSameTags(e);
		}
		return 1;
	}

	private String renderFuncKey(String funcName, Class[] params) {
		return funcName + "|" + StringUtils.join(params, ",");
	}
}
