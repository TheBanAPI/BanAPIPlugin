package de.curano.banapiplugin.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Util {

    public static String encode(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
    }

    public static String decode(String str) {
        return new String(Base64.getDecoder().decode(str));
    }

}
