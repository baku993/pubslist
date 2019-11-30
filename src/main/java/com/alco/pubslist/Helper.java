package com.alco.pubslist;

import org.apache.commons.codec.digest.DigestUtils;

public class Helper {

	public static String cacheData(String data, String salt) {

		return DigestUtils.sha512Hex(data + salt);
	}
}
