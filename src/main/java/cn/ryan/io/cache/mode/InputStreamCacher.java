package cn.ryan.io.cache.mode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputStreamCacher {
	/**
	 * 将InputStream中的字节保存到ByteArrayOutputStream中。
	 */
	private ByteArrayOutputStream byteArrayOutputStream = null;

	public InputStreamCacher(InputStream inputStream) {
		if (inputStream == null) {
			return;
		}

		byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len;
		try {
			while ((len = inputStream.read(buffer)) > -1) {
				byteArrayOutputStream.write(buffer, 0, len);
			}
			byteArrayOutputStream.flush();
		} catch (IOException e) {
		}
	}

	public InputStream getInputStream() {
		return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
	};
}
