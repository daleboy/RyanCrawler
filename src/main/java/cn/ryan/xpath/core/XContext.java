package cn.ryan.xpath.core;
import java.util.LinkedList;

import cn.ryan.xpath.model.Node;

public class XContext {
    public LinkedList<Node> xpathTr;
    public XContext(){
        if (xpathTr==null){
            xpathTr = new LinkedList<Node>();
        }
    }
}
