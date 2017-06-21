package cn.ryan.test;

import java.io.IOException;

import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;

import cn.ryan.processor.CrawlerSite;
import cn.ryan.processor.PageProcessor;
import cn.ryan.processor.Processor;

public class Demo implements Processor {
	private CrawlerSite cr = CrawlerSite.create().userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36").ignoreContentType(true).timeOut(1000).setFilePath("/Users/Rui_Statham/Downloads");// 如果填写该路径，则会默认下载访问过的页面到该页面

	public CrawlerSite getSite() {
		return cr;
	}

	public static void main(String[] args) throws IOException {
		Response res = PageProcessor.create(new Demo()).url("https://www.baidu.com/s?wd=Java")// Url
				.execute();// 执行请求
		Document doc = res.parse();// 格式化doc
		
		doc.xpath("//div[@id='content_left']/div[position()<4]");
	}
}
