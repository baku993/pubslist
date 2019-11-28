package com.alco.pubslist;

import org.apache.commons.codec.digest.DigestUtils;

public class Helper {

    public static String cacheData(String data){
        return DigestUtils.sha512Hex(data + "soloAsGod");
    }
}
