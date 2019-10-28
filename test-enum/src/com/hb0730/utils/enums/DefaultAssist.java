package com.hb0730.utils.enums;

import com.hb0730.utils.ResourceProviderManager;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * <p></p>
 *
 * @author bing_huang
 * @since V1.0
 */
class DefaultAssist extends Assist {

    private final String className;


    DefaultAssist(String className) {
        this.className = className;
    }

    @Override
    protected void initEnum() {
        try {
            Enum.getStaticFields(Class.forName(this.className));
        } catch (ClassNotFoundException var2) {
            ;
        }

    }

    @Override
    protected String getKey() {
        return this.className;
    }

    @Override
    protected String getResource(String name, Locale locale) {
        if (locale == null) {
            locale = getDefaultLocale();
        }

        String res;
        if (ResourceProviderManager.isMultiLanguages()) {
            res = ResourceProviderManager.getEnumString(this.className, locale, name);
//            if(!Stringutils.isEmpty(res)) {
//                return res;
//            }
            if (res != null) {
                return res;
            }
        }

        res = null;

        try {
            ResourceBundle res1 = ResourceBundle.getBundle(this.className, locale);
            return res1.getString(name);
        } catch (MissingResourceException var5) {
            return name;
        }
    }

    @Override
    protected boolean isKeyErasable() {
        return false;
    }
}
