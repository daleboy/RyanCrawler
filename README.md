# RyanCrawler
以`Jsoup`为请求模块，重构部分底层，优化I/O流，支持下载功能，嵌入`Xpath`2.0语法模块，增强`Xpath`语法模块。  
<br>
###语法
支持标准xpath语法（支持谓语嵌套），支持全部常用函数，支持全部常用轴，去掉了一些标准里面华而不实的函数和轴，下面会具体介绍。语法可以参考http://www.w3school.com.cn/xpath/index.asp

**关于使用Xpath的一些注意事项**

非常不建议直接粘贴Firefox或chrome里生成的Xpath，这些浏览器在渲染页面会根据标准自动补全一些标签，如table标签会自动加上tbody标签，这样生成的Xpath路径显然不是最通用的，所以很可能就取不到值。所以，要使用Xpath并感受Xpath的强大以及他所带来便捷与优雅最好就是学习下Xpath的标准语法，这样应对各种问题才能游刃有余，享受Xpath的真正威力！

|函数名称|描述|
|------:|:------|
|href()|节点内所有a标签的绝对地址|
|text()|提取节点的自有文本|
|node()|提取所有节点|
|position()|返回当前节点所处在同胞中的位置|
|last()|返回同级节点中的最后那个节点|
|first()|返回同级节点中的第一个节点|
|扩展函数||
|allText()|提取节点下全部文本|
|html()|获取全部节点的内部的html|
|outerHtml()|获取全部节点的包含节点本身在内的全部html|
|num()|抽取节点自有文本中全部数字|  

**其他说明**

- contains(arga,argb)这个函数暂时不支持，可以用\*=取代contains() 例：//div[text()\*='next']

|轴|描述|  
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
|扩展轴||
|preceding-sibling-one|前一个同胞节点|
|following-sibling-one|返回下一个同胞节点|
|sibling|全部同胞|  

|操作符|描述|
|------:|:------|
|a+b|返回数值相加结果|
|a-b|返回数值相减结果|
|a=b|判断是否相等返回Boolean|
|a!=b|不等于 返回Boolean|
|a&gt;b|大于 返回Boolean|
|a>=b|大于等于 返回Boolean|
|a&lt;b|小于 返回Boolean|
|a<=b|小于等于 返回Boolean|
|操作符扩展||
|a^=b|字符串a以字符串b开头 a startwith b|
|a*=b|a包含b, a contains b|
|a$=b|a以b结尾 a endwith b|
|a~=b|a的内容符合 正则表达式b|
|a!~b|a的内容不符合 正则表达式b|
未完待续。。。