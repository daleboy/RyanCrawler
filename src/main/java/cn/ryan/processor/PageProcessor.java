package cn.ryan.processor;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class PageProcessor {
	protected CrawlerSite site;

	public PageProcessor(Processor p) {
		site = p.getSite();
	}

	public static Connection create(Processor p) {
		return Jsoup.create(p);
	}

}
