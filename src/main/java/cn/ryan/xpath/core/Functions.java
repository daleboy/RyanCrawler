package cn.ryan.xpath.core;
import cn.ryan.xpath.model.XpathDocument;
import cn.ryan.xpath.model.XpathNode;
import cn.ryan.xpath.util.CommonUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * xpath解析器的支持的全部函数集合，如需扩展按形式添加即可
 */
public class Functions {
	
	/***
	 * 获取节点内全部绝对地址超链接
	 * @param context
	 * @return
	 */
	public List<XpathNode> href(Elements context) {
		return XpathNode.e(context.absLinks());
	}
	
	/***
	 * 获取节点内全部绝对地址src
	 * @param context
	 * @return
	 */
	public List<XpathNode> src(Elements context) {
		List<String> srcList = new ArrayList<>();
		for (Element element : context) {
			srcList.add(element.attr("abs:src"));
		}
		return XpathNode.t(srcList);
	}
	
	
    /**
     * 只获取节点自身的子文本
     * @param context
     * @return
     */
    public List<XpathNode> text(Elements context){
        List<XpathNode> res = new LinkedList<XpathNode>();
        if (context!=null&&context.size()>0){
            for (Element e:context){
                if (e.nodeName().equals("script")){
                    res.add(XpathNode.t(e.data()));
                }else {
                    res.add(XpathNode.t(e.ownText()));
                }
            }
        }
        return res;
    }

    /**
     * 递归获取节点内全部的纯文本
     * @param context
     * @return
     */
    public List<XpathNode> allText(Elements context){
        List<XpathNode> res = new LinkedList<XpathNode>();
        if (context!=null&&context.size()>0){
            for (Element e:context){
                res.add(XpathNode.t(e.text()));
            }
        }
        return res;
    }

    /**
     * 获取全部节点的内部的html
     * @param context
     * @return
     */
    public List<XpathNode> html(Elements context){
        List<XpathNode> res = new LinkedList<XpathNode>();
        if (context!=null&&context.size()>0){
            for (Element e:context){
                res.add(XpathNode.t(e.html()));
            }
        }
        return res;
    }

    /**
     * 获取全部节点的 包含节点本身在内的全部html
     * @param context
     * @return
     */
    public List<XpathNode> outerHtml(Elements context){
        List<XpathNode> res = new LinkedList<XpathNode>();
        if (context!=null&&context.size()>0){
            for (Element e:context){
                res.add(XpathNode.t(e.outerHtml()));
            }
        }
        return res;
    }

    /**
     * 获取全部节点
     * @param context
     * @return
     */
    public List<XpathNode> node(Elements context){
        return html(context);
    }

    /**
     * 抽取节点自有文本中全部数字
     * @param context
     * @return
     */
    public List<XpathNode> num(Elements context){
        List<XpathNode> res = new LinkedList<XpathNode>();
        if (context!=null){
            Pattern pattern = Pattern.compile("\\d+");
            for (Element e:context){
                Matcher matcher = pattern.matcher(e.ownText());
                if (matcher.find()){
                    res.add(XpathNode.t(matcher.group()));
                }
            }
        }
        return res;
    }
    
    
    /**
     * =====================
     * 下面是用于过滤器的函数
     */

    /**
     * 获取元素自己的子文本
     * @param e
     * @return
     */
    public String text(Element e){
        return e.ownText();
    }

    /**
     * 获取元素下面的全部文本
     * @param e
     * @return
     */
    public String allText(Element e){
        return e.text();
    }

    /**
     * 判断一个元素是不是最后一个同名同胞中的
     * @param e
     * @return
     */
    public boolean last(Element e){
        return CommonUtil.getElIndexInSameTags(e)==CommonUtil.sameTagElNums(e);
    }
    /**
     * 判断一个元素是不是同名同胞中的第一个
     * @param e
     * @return
     */
    public boolean first(Element e){
        return CommonUtil.getElIndexInSameTags(e)==1;
    }

    /**
     * 返回一个元素在同名兄弟节点中的位置
     * @param e
     * @return
     */
    public int position(Element e){
        return CommonUtil.getElIndexInSameTags(e);
    }

    /**
     * 判断是否包含
     * @param left
     * @param right
     * @return
     */
    public boolean contains(String left,String right){
       return left.contains(right);
    }

}
