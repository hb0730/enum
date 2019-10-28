package com.hb0730.utils;

import java.util.*;

/**
 * <p></p>
 *
 * @author bing_huang
 * @since V1.0
 */
public class LocaleUtils {
    private static Locale defaultLocale = Locale.getDefault();
    private static Locale defaultOriginLocale = Locale.getDefault();
    private static Map locales = new HashMap(4);
    private static Map localeStrings = new HashMap(4);
    private static Map shortCodes = new HashMap(4);
    public static final Locale locale_l1 = new Locale("l1", "");
    public static final Locale locale_L1 = new Locale("L1", "");
    public static final Locale locale_en_US = new Locale("en", "US");
    public static final Locale locale_l2 = new Locale("l2", "");
    public static final Locale locale_L2 = new Locale("L2", "");
    public static final Locale locale_zh_CN = new Locale("zh", "CN");
    public static final Locale locale_l3 = new Locale("l3", "");
    public static final Locale locale_L3 = new Locale("L3", "");
    public static final Locale locale_zh_TW = new Locale("zh", "TW");
    public static final Locale locale_en = new Locale("en", "");


    public static Locale getLocale(String localeString) {
        if(localeString == null) {
            return null;
        } else if("l2".equals(localeString)) {
            return locale_l2;
        } else if("L2".equals(localeString)) {
            return locale_L2;
        } else if(!"zh_CN".equals(localeString) && !"CH".equals(localeString)) {
            if("l1".equals(localeString)) {
                return locale_l1;
            } else if("L1".equals(localeString)) {
                return locale_L1;
            } else if("en_US".equals(localeString)) {
                return locale_en_US;
            } else if(!"zh_TW".equals(localeString) && !"TW".equals(localeString)) {
                if("en".equals(localeString)) {
                    return locale_en;
                } else if("l3".equals(localeString)) {
                    return locale_l3;
                } else if("L3".equals(localeString)) {
                    return locale_L3;
                } else {
                    Locale locale = (Locale)locales.get(localeString);
                    if(locale == null) {
                        Map var2 = locales;
                        synchronized(locales) {
                            locale = (Locale)locales.get(localeString);
                            if(locale == null) {
                                locale = innerGetLocale(localeString);
                                locales.put(localeString, locale);
                                localeStrings.put(locale, localeString);
                            }
                        }
                    }

                    return locale;
                }
            } else {
                return locale_zh_TW;
            }
        } else {
            return locale_zh_CN;
        }
    }

    public static void clear() {
        Map var0 = locales;
        synchronized(locales) {
            locales.clear();
            localeStrings.clear();
        }

        var0 = shortCodes;
        synchronized(shortCodes) {
            shortCodes.clear();
        }
    }

    public static Locale getShortLocale(Locale local) {
        return getLocale(getShortCode(local));
    }

    public static String getShortCode(Locale locale) {
        return locale == null?null:(locale_l2.equals(locale)?"l2":(locale_L2.equals(locale)?"l2":(locale_zh_CN.equals(locale)?"l2":(locale_l1.equals(locale)?"l1":(locale_L1.equals(locale)?"l1":(locale_en_US.equals(locale)?"l1":(locale_zh_TW.equals(locale)?"l3":(locale_en.equals(locale)?"l1":(locale_l3.equals(locale)?"l3":(locale_L3.equals(locale)?"l3":(String)shortCodes.get(locale)))))))))));
    }

    public static void addShortCode(Locale locale, String shortCode) {
        if(locale != null && shortCode != null) {
            shortCodes.put(locale, shortCode.toLowerCase());
        }

    }

    public static List getOriginLocale(Locale locale) {
        if(locale == null) {
            return null;
        } else if(shortCodes.containsKey(locale)) {
            ArrayList ls = new ArrayList(1);
            ls.add(locale);
            return ls;
        } else {
            return getLocaleByShortCode(locale.toString());
        }
    }

    public static Locale getFirstOriginLocale(Locale locale) {
        List locales = getOriginLocale(locale);
        return locales != null && locales.size() > 0?(Locale)locales.get(0):null;
    }

    public static List getLocaleByShortCode(String shortCode) {
        Iterator ite = shortCodes.entrySet().iterator();
        ArrayList ls = new ArrayList(2);

        while(ite.hasNext()) {
            Map.Entry entry = (Map.Entry)ite.next();
            if(((String)entry.getValue()).equalsIgnoreCase(shortCode)) {
                ls.add(entry.getKey());
            }
        }

        if(ls.size() == 0) {
            ls.add(getLocale(shortCode));
        }

        return ls;
    }

    private static Locale innerGetLocale(String localeString) {
        ArrayList vect = new ArrayList(3);
        StringTokenizer token = new StringTokenizer(localeString, "_");

        while(token.hasMoreTokens()) {
            vect.add(token.nextToken());
        }

        Locale locale = null;
        if(vect.size() == 1) {
            locale = new Locale((String)vect.get(0), "");
        } else if(vect.size() == 2) {
            locale = new Locale((String)vect.get(0), (String)vect.get(1));
        } else if(vect.size() == 3) {
            locale = new Locale((String)vect.get(0), (String)vect.get(1), (String)vect.get(2));
        }

        return locale;
    }

    public static String getLocaleString(Locale locale) {
        if(locale == null) {
            return null;
        } else if(locale_l2.equals(locale)) {
            return "l2";
        } else if(locale_L2.equals(locale)) {
            return "l2";
        } else if(locale_zh_CN.equals(locale)) {
            return "zh_CN";
        } else if(locale_l1.equals(locale)) {
            return "l1";
        } else if(locale_L1.equals(locale)) {
            return "l1";
        } else if(locale_en_US.equals(locale)) {
            return "en_US";
        } else if(locale_zh_TW.equals(locale)) {
            return "zh_TW";
        } else if(locale_en.equals(locale)) {
            return "en";
        } else if(locale_l3.equals(locale)) {
            return "l3";
        } else if(locale_L3.equals(locale)) {
            return "l3";
        } else {
            String str = (String)localeStrings.get(locale);
            if(str == null) {
                str = locale.toString();
            }

            return str;
        }
    }

    public static Locale getDefaultLocale() {
        return defaultLocale;
    }

    public static void setDefaultLocale(Locale loc) {
        defaultLocale = getShortLocale(loc);
        defaultOriginLocale = getFirstOriginLocale(loc);
    }

    public static Locale getDefaultOriginLocale() {
        return defaultOriginLocale;
    }

}
