package com.example.practica1.util;

import org.apache.commons.codec.digest.DigestUtils;

public class HashUtils {

    public static String sha256(String input) {
        return DigestUtils.sha256Hex(input);
    }
}
