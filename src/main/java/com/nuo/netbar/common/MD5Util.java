package com.nuo.netbar.common;

import com.twmacinta.util.MD5;

import java.io.UnsupportedEncodingException;

public class MD5Util {
	private static final String KEY = "agoodday";
	private static final String CHARSET_NAME = "UTF-8";

	public static String encodePassword(String password) {
		try {
			return MD5.asHex((password+KEY).getBytes(CHARSET_NAME));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return password;
		}
	}
}
