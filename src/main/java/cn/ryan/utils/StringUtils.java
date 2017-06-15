package cn.ryan.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * 基础工具类
 *
 */
public class StringUtils {
	public static boolean isNotBlank(CharSequence cs) {
		return (!(isBlank(cs)));
	}

	public static boolean isBlank(CharSequence cs) {
		int strLen;
		if ((cs == null) || ((strLen = cs.length()) == 0))
			return true;
		for (int i = 0; i < strLen; ++i) {
			if (!(Character.isWhitespace(cs.charAt(i)))) {
				return false;
			}
		}
		return true;
	}

	public static String substringAfter(String str, String separator) {
		if (isEmpty(str)) {
			return str;
		}
		if (separator == null) {
			return "";
		}
		int pos = str.indexOf(separator);
		if (pos == -1) {
			return "";
		}
		return str.substring(pos + separator.length());
	}

	public static boolean isEmpty(CharSequence cs) {
		return ((cs == null) || (cs.length() == 0));
	}

	public static String join(Iterator<?> iterator, String separator) {
		if (iterator == null) {
			return null;
		}
		if (!(iterator.hasNext())) {
			return "";
		}
		Object first = iterator.next();
		if (!(iterator.hasNext())) {
			String result = (first == null) ? "" : first.toString();
			return result;
		}

		StringBuilder buf = new StringBuilder(256);
		if (first != null) {
			buf.append(first);
		}

		while (iterator.hasNext()) {
			if (separator != null) {
				buf.append(separator);
			}
			Object obj = iterator.next();
			if (obj != null) {
				buf.append(obj);
			}
		}
		return buf.toString();
	}

	public static String join(Iterable<?> iterable, String separator) {
		if (iterable == null) {
			return null;
		}
		return join(iterable.iterator(), separator);
	}

	public static String join(Object[] array, String separator, int startIndex, int endIndex) {
		if (array == null) {
			return null;
		}
		if (separator == null) {
			separator = "";
		}

		int noOfItems = endIndex - startIndex;
		if (noOfItems <= 0) {
			return "";
		}

		StringBuilder buf = new StringBuilder(noOfItems * 16);

		for (int i = startIndex; i < endIndex; ++i) {
			if (i > startIndex) {
				buf.append(separator);
			}
			if (array[i] != null) {
				buf.append(array[i]);
			}
		}
		return buf.toString();
	}

	public static String join(Object[] array, String separator) {
		if (array == null) {
			return null;
		}
		return join(array, separator, 0, array.length);
	}

	public static boolean isboolIp(String ipAddress) {
		String ip = "((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))";
		Pattern pattern = Pattern.compile(ip);
		Matcher matcher = pattern.matcher(ipAddress);
		return matcher.matches();
	}

	public static boolean isNullOrEmpty(Object toTest) {
		return ((toTest == null) || (toTest.equals("null")) || (toTest.equals("")));
	}

	public static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	public static String absLinks(String baseUrl, String changeUrl) {
		URL url;
		try {
			try {
				url = new URL(baseUrl);
				if (changeUrl.startsWith("?")) {
					changeUrl = url.getPath() + changeUrl;
				}
				if (changeUrl.indexOf(".") == 0 && url.getFile().indexOf("/") != 0) {
					url = new URL( "." + url.getFile());
				}
				return new URL(url , changeUrl).toExternalForm();
			} catch (MalformedURLException e) {
				return new URL(changeUrl).toExternalForm();
			}
		} catch (MalformedURLException e) {
			return "";
		}
	}

	public static void main(String[] args) {
		System.out.println(absLinks("http://www.baidu.com/ds123/", ".?absUrl=1"));
	}
}
