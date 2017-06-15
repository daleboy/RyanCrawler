package cn.ryan.processor;

import java.io.IOException;

import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;

public class Demo implements Processor {
	private CrawlerSite cr = CrawlerSite.create()
			.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36")
			.ignoreContentType(true).timeOut(1000);
	
	public CrawlerSite getSite() {
		return cr;
	}

	/***
	 	* 函数： 
	 	* href() 节点内所有a标签的绝对地址
	 	* text() 提取节点的自有文本
		* node() 提取所有节点
		* position() 返回当前节点所处在同胞中的位置
		* last() 返回同级节点中的最后那个节点
		* first() 返回同级节点中的第一个节点
		* 
		* 扩展函数：
		* allText() 提取节点下全部文本
		* html()获取全部节点的内部的html
		* outerHtml()获取全部节点的 包含节点本身在内的全部html
		* num()抽取节点自有文本中全部数字
		* 
		* 轴：
		* self 节点自身
		* parent 父节点
		* child 直接子节点
		* ancestor 全部祖先节点 父亲，爷爷 ， 爷爷的父亲...
		* ancestor-or-self全部祖先节点和自身节点
		* descendant 全部子代节点 儿子，孙子，孙子的儿子...
		* descendant-or-self 全部子代节点和自身
		* preceding-sibling 节点前面的全部同胞节点
		* following-sibling 节点后面的全部同胞节点
		* preceding-sibling-one 前一个同胞节点（扩展）
		* following-sibling-one 返回下一个同胞节点(扩展) 语法
		* sibling 全部同胞（扩展）
		* 
		* 操作符：
		* a+b 加 返回数值结果
		* a-b 减 返回数值结果
		* a=b 判断是否相等返回Boolean
		* a!=b 不等于 返回Boolean
		* a>b 大于 返回Boolean
		* a>=b 大于等于 返回Boolean
		* a<b 小于 返回Boolean
		* a<=b 小于等于 返回Boolean
		* 
		* 操作符扩展：
		* a^=b 字符串a是否以字符串b开头 a startwith b 返回Boolean
		* a*=b a是否包含b, a contains b 返回Boolean
		* a$=b a是否以b结尾 a endwith b 返回Boolean
		* a~=b a的内容是否符合 正则表达式b 返回Boolean
		* 
		* 支持标准xpath语法
		* 支持谓语嵌套
		* 参考：http://www.w3school.com.cn/xpath/index.asp
	 */
	public static void main(String[] args) throws IOException {
		Response res = PageProcessor.create(new Demo())
				.url("https://www.baidu.com/s?wd=Java")// Url
				.execute();// 执行请求
		Document doc = res.parse();// 格式化
		System.out.println(doc.xpath("//div[@id='content_left']/div/h3/a/href()"));
	}

}
