# RyanCrawler
>以Jsoup为请求/下载模块，重构部分底层，重构请求载入结构，重写参数注入方式，嵌入部分增强功能，优化I/O流，可对单个请求定制化，并支持下载功能。

>以JsoupXpath为解析模块，重构解析结构，重写函数、轴、操作符解析方式提高性能和易读性，缓存语法树，移除部分华而不实的功能，增强Xpath语法模块，且支持在筛选结果中继续XPath筛选 `doc.xpath`("xpath").`xpath`("xpath") 

## 快速开始
  	public class Demo implements Processor {
		private CrawlerSite cr = CrawlerSite.create()
			.userAgent("Mozilla/5.0  (Macintosh;Intel Mac OS X 10_12_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36")
			.ignoreContentType(true)
			.timeOut(1000)//超时时间
			.setFilePath("/Users/Rui_Statham/Downloads");// 如果填写该路径，则会默认下载访问过的页面到该页面
		public CrawlerSite getSite() {
			return cr;
		} 
		public static void main(String[] args) throws IOException {
			Response res = PageProcessor.create(new Demo()).url("https://www.baidu.com/s?wd=Java").execute();// 执行请求
			Document doc = res.parse();// 格式化doc							  doc.xpath("//div[@id='content_left']/div[position()<4]/h3/a/@href");
		}
	}
## 请求模块
> 因请求模块基于Jsoup改造，为兼容原生Jsoup保留大部分操作方式，固可参考Jsoup API，理论上保留95%；
	
## 解析模块
### 语法
>支持标准Xpath语法（支持谓语嵌套），支持全部常用函数，支持全部常用轴，去掉了一些标准里面华而不实的函数和轴，下面会具体介绍。语法可以参考http://www.w3school.com.cn/xpath/index.asp

**关于使用Xpath的一些注意事项**

>非常不建议直接粘贴Firefox或chrome里生成的Xpath，这些浏览器在渲染页面会根据标准自动补全一些标签，如table标签会自动加上tbody标签，这样生成的Xpath路径显然不是最通用的，所以很可能就取不到值。所以，要使用Xpath并感受Xpath的强大以及他所带来便捷与优雅最好就是学习下Xpath的标准语法，这样应对各种问题才能游刃有余，享受Xpath的真正威力！

### 特别说明
> RyanCrawler解析模块支持两种写法：
> 
> 原生Jsoup写法：`doc.select("div#id div.class:last");`
> 
> **现在的写法：**`doc.xpath("//*[@id='id']/div[class='class']/div[last()]");`
### Xpath部分示例
	http://www.cnblogs.com/ 为例
	//a/@href;
	//div[@id='paging_block']/div/a[text()='Next >']/@href;
	//div[@id='paging_block']/div/a[text()*='Next']/@href;
	//h1/text();
	//h1/allText();
	//h1//text();
	//div/a;
	//div[@id='post_list']/div[position()<3]/div/h3/allText();
	//div[@id='post_list']/div[first()]/div/h3/allText();
	//div[@id='post_list']/div[1]/div/h3/allText();
	//div[@id='post_list']/div[last()]/div/h3/allText();//查找评论大于1000的条目（当然只是为了演示复杂xpath了，谓语中可以各种嵌套，这样才能测试的更全面嘛）
	//div[@id='post_list']/div[./div/div/span[@class='article_view']/a/num()>1000]/div/h3/allText();//轴支持
	//div[@id='post_list']/div[self::div/div/div/span[@class='article_view']/a/num()>1000]/div/h3/allText();
	//div[@id='post_list']/div[2]/div/p/preceding-sibling::h3/allText();
	//div[@id='post_list']/div[2]/div/p/preceding-sibling::h3/allText()|//div[@id='post_list']/div[1]/div/h3/allText();
### **函数**
|名称|描述|
|------:|:------|
|href()|节点内所有a标签的绝对地址|
|text()|提取节点的自有文本|
|node()|提取所有节点|
|||
|position()|返回当前节点所处在同胞中的位置|
|last()|返回同级节点中的最后那个节点|
|first()|返回同级节点中的第一个节点|
#### **解析器扩展函数**
|名称|描述|
|------:|:------|
|allText()|提取节点下全部文本，取代类似 //div/h3//text()这种递归取文本用法|
|html()|获取全部节点的内部的html|
|outerHtml()|获取全部节点的包含节点本身在内的全部html|
|num()|抽取节点自有文本中全部数字，如果知道节点的自有文本(即非子代节点所包含的文本)中只存在一个数字，如阅读数，评论数，价格等那么直接可以直接提取此数字出来。如果有多个数字将提取第一个匹配的连续数字。|
**其他说明**
> contains(arga,argb)这个函数暂时不支持，可以用\*=取代contains() 例：//div[text()\*='next']
### **轴**
|名称|描述|
|------:|:------|
|self|节点自身|
|parent|父节点|
|child|子节点|
|ancestor|全部祖先节点 父亲，爷爷 ， 爷爷的父亲...|
|ancestor-or-self|全部祖先节点和自身节点|
|descendant|全部子代节点 儿子，孙子，孙子的儿子...|
|descendant-or-self|全部子代节点和自身|
|preceding-sibling|节点前面的全部同胞节点|
|following-sibling|节点后面的全部同胞节点|
#### **扩展轴**
|名称|描述|
|------:|:------|
|preceding-sibling-one|前一个同胞节点|
|following-sibling-one|返回下一个同胞节点|
|sibling|全部同胞|  
### **操作符**
|名称|描述|
|------:|:------|
|a+b|返回数值相加结果|
|a-b|返回数值相减结果|
|a=b|判断是否相等返回Boolean|
|a!=b|不等于 返回Boolean|
|a&gt;b|大于 返回Boolean|
|a>=b|大于等于 返回Boolean|
|a&lt;b|小于 返回Boolean|
|a<=b|小于等于 返回Boolean|  
#### **操作符扩展**
|名称|描述|
|------:|:------|
|a^=b|字符串a以字符串b开头 a startwith b|
|a*=b|a包含b, a contains b|
|a$=b|a以b结尾 a endwith b|
|a~=b|a的内容符合 正则表达式b|
|a!~b|a的内容不符合 正则表达式b|  

