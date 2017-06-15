package cn.ryan.test;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.print.Doc;

import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;

import cn.ryan.processor.CrawlerSite;
import cn.ryan.processor.PageProcessor;
import cn.ryan.processor.Processor;
import cn.ryan.utils.StringUtils;

public class CrawlerTest implements Processor {
	private CrawlerSite site = CrawlerSite.create().ignoreContentType(true).timeOut(5000)
			.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36");

	@Override
	public CrawlerSite getSite() {
		return site;
	}

	public void get() {
		ExecutorService exe = Executors.newFixedThreadPool(200);
		for (int i = 150001; i < 200000; i++) {
			exe.execute(new t(i));
		}
	}

	class t extends Thread {
		private int i;

		public t(int i) {
			this.i = i;
		}

		public void run() {
			try {
				Response res = PageProcessor.create(new CrawlerTest()).url("http://www.acfun.cn/u/" + i + ".aspx").execute();
				if (res == null) {
				}
				Document doc = res.parse();
				String str = doc.xpath("//div[@id='anchorMes']/div/div/div[@class='clearfix']/span[@class='fl fans']").text();
				if (!StringUtils.isNullOrEmpty(str)) {
					int c = Integer.parseInt(str);
					if (c > 1000) {
						System.out.println("ID：" + i + "\t粉丝数：" + c);
					}
				}

			} catch (IOException e) {
			}

		}
	}

	public static void main(String[] args) {
		new CrawlerTest().get();
	}

}
