package com.hb0730.utils.enums;


import com.hb0730.utils.LocaleUtils;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * <p></p>
 *
 * @author bing_huang
 * @since V1.0
 */
public abstract class Assist {
    private static final Set keys = new HashSet();


    protected abstract void initEnum();

    protected static Locale getDefaultLocale() {
        Locale thisLocale = null;

        try {
            Class t = Class.forName("com.kingdee.bos.ContextUtils");
            Method m = t.getMethod("getOriginalLocaleFromEnv", (Class[])null);
            thisLocale = (Locale)m.invoke((Object)null, (Object[])null);
            if(thisLocale == null) {
                thisLocale = LocaleUtils.getDefaultOriginLocale();
            }
        } catch (Throwable var3) {
            thisLocale = LocaleUtils.getDefaultOriginLocale();
        }

        return thisLocale;
    }

    protected abstract String getResource(String var1, Locale var2);

    protected abstract String getKey();

    protected abstract boolean isKeyErasable();

    String innerGetKey() {
        String key = this.getKey();
        if(this.isKeyErasable()) {
            Set var2 = keys;
            synchronized(keys) {
                keys.add(key);
            }
        }

        return key;
    }

    static String[] clearKeys() {
        Set var1 = keys;
        synchronized(keys) {
            String[] ks = (String[])keys.toArray(new String[0]);
            keys.clear();
            return ks;
        }
    }
}
