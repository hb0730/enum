package com.hb0730.utils;

import java.util.Locale;

/**
 * <p></p>
 *
 * @author bing_huang
 * @since V1.0
 */

public interface IResourceProvider {

    boolean isMultiLanguages();

    String getEnumString(String var1, Locale var2, String var3);

    String getExceptionString(String var1, Locale var2, String var3);
}
