package cn.ryan.processor;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.jsoup.Connection.Method;

public class CrawlerSite  {

	private int timeOut;
	private String userAgent;
	private boolean ignoreContentType;
	private org.jsoup.Connection.Method method;
	private Map<String, String> headers;
	private String filePath;

	public static final String DEFAULT_UA = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36";
	
	private CrawlerSite(){
		timeOut = 5000;
		userAgent = DEFAULT_UA;
		ignoreContentType = false;
		method = Method.GET;
		headers = new HashMap<>();
	}
	
	public static CrawlerSite create(){
		return new CrawlerSite();
	}

	public int getTimeOut() {
		return timeOut;
	}

	public CrawlerSite timeOut(int timeOut) {
		this.timeOut = timeOut;
		return this;
	}

	public String getUserAgent() {
		return userAgent;
	}


	public CrawlerSite userAgent(String userAgent) {
		this.userAgent = userAgent;
		return this;
	}


	public boolean isIgnoreContentType() {
		return ignoreContentType;
	}


	public CrawlerSite ignoreContentType(boolean ignoreContentType) {
		this.ignoreContentType = ignoreContentType;
		return this;
	}


	public org.jsoup.Connection.Method getMethod() {
		return method;
	}


	public CrawlerSite method(org.jsoup.Connection.Method method) {
		this.method = method;
		return this;
	}



	public Map<String, String> getHeaders() {
		return headers;
	}


	public CrawlerSite setHeaders(Map<String, String> headers) {
		this.headers = headers;
		return this;
	}


	public String getFilePath() {
		return filePath;
	}


	public CrawlerSite setFilePath(String filePath) {
		this.filePath = filePath;
		return this;
	}


	public static void trustAllHosts() {
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return new java.security.cert.X509Certificate[] {};
			}

			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}
		} };
		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
