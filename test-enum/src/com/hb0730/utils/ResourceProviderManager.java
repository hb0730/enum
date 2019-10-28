package com.hb0730.utils;

import java.util.Locale;

/**
 * <p></p>
 *
 * @author bing_huang
 * @since V1.0
 */
public class ResourceProviderManager {

    private static IResourceProvider provider;


    public static void setProvider(IResourceProvider prd) {
        provider = prd;
    }

    public static boolean isMultiLanguages() {
        return provider == null?false:provider.isMultiLanguages();
    }

    public static String getEnumString(String pk, Locale l, String key) {
        return provider.getEnumString(pk, l, key);
    }

    public static String getExceptionString(String pk, Locale l, String key) {
        return provider.getExceptionString(pk, l, key);
    }
}
