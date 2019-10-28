package com.hb0730.utils.enums;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * <p></p>
 *
 * @author bing_huang
 * @since V1.0
 */
public abstract class Enum implements Comparable, Serializable {
    private static final long serialVersionUID = -2396224579477180874L;

    private static final Map cEnumClasses = new HashMap();
    private final String iName;


    protected Enum(String name) {
        this(name, (Assist) null);
    }

    Enum(String name, Assist assist) {
        if (name != null && name.length() != 0) {
            this.iName = name;
            if (assist == null) {
                assist = this.getAssist(this.getClass().getName());
            }

            String className = assist.innerGetKey();
            Entry entry = (Entry) cEnumClasses.get(className);
            if (entry == null) {
                entry = new Entry((Base) null);
                cEnumClasses.put(className, entry);
            }

            if (entry.map.containsKey(name)) {
                throw new IllegalArgumentException("The Enum name must be unique, \'" + name + "\' has already been added in " + className);
            } else {
                entry.map.put(name, this);
                entry.list.add(this);
            }
        } else {
            throw new IllegalArgumentException("The Enum name must not be empty");
        }
    }

    protected Object readResolve() {
        return getEnum(this.getClass(), this.getName());
    }

    protected static Enum getEnum(Class enumClass, String name) {
        return (Enum) getEnumMap((Class) enumClass).get(name);
    }

    protected static Map getEnumMap(Class enumClass) {
        if (enumClass == null) {
            throw new IllegalArgumentException("The Enum Class must not be null");
        } else if (!Enum.class.isAssignableFrom(enumClass)) {
            throw new IllegalArgumentException("The Class must be a subclass of Enum");
        } else {
            return getEnumMap((Assist) (new DefaultAssist(enumClass.getName())));
        }
    }

    static Map getEnumMap(Assist assist) {
        String classname = assist.innerGetKey();
        Entry entry = (Entry) cEnumClasses.get(classname);
        return entry == null ? Collections.emptyMap() : Collections.unmodifiableMap(entry.map);
    }

    static List getEnumList(Assist assist, boolean isInit) {
        String classname = assist.innerGetKey();
        Entry entry = (Entry) cEnumClasses.get(classname);
        if (entry == null) {
            if (isInit) {
                assist.initEnum();
                entry = (Entry) cEnumClasses.get(classname);
            }

            if (entry == null) {
                return Collections.EMPTY_LIST;
            }
        }

        return Collections.unmodifiableList(entry.list);
    }

    public static final void clear() {
        String[] classnames = Assist.clearKeys();
        if (classnames != null) {
            String[] arr$ = classnames;
            int len$ = classnames.length;

            for (int i$ = 0; i$ < len$; ++i$) {
                String c = arr$[i$];
                cEnumClasses.remove(c);
            }

        }
    }

    protected static List getEnumList(Class enumClass) {
        return getEnumList((Class) enumClass, true);
    }

    static List getEnumList(Class enumClass, boolean isInit) {
        if (enumClass == null) {
            throw new IllegalArgumentException("The Enum Class must not be null");
        } else if (!Enum.class.isAssignableFrom(enumClass)) {
            throw new IllegalArgumentException("The Class must be a subclass of Enum");
        } else {
            return getEnumList((Assist) (new DefaultAssist(enumClass.getName())), isInit);
        }
    }

    protected static List getEnumList(Class enumClass, Locale locale) {
        return getEnumList((Class) enumClass, false);
    }

    protected static String[] getEnumNames(Class enumClass) {
        List enums = getEnumList(enumClass);
        String[] names = new String[enums.size()];

        for (int i = 0; i < enums.size(); ++i) {
            Enum _enum = (Enum) enums.get(i);
            names[i] = _enum.getName();
        }

        return names;
    }

    protected static Iterator iterator(Class enumClass) {
        return getEnumList(enumClass).iterator();
    }

    public final String getName() {
        return this.iName;
    }

    @Override
    public final boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other == null) {
            return false;
        } else if (other.getClass() == this.getClass()) {
            return this.iName.equals(((Enum) other).iName);
        } else if (other.getClass().getName().equals(this.getClass().getName())) {
            try {
                return this.iName.equals(((Enum) other).iName);
            } catch (ClassCastException var8) {
                try {
                    Method ex2 = other.getClass().getMethod("getName", (Class[]) null);
                    String name = (String) ex2.invoke(other, (Object[]) null);
                    return this.iName.equals(name);
                } catch (NoSuchMethodException var5) {
                    ;
                } catch (IllegalAccessException var6) {
                    ;
                } catch (InvocationTargetException var7) {
                    ;
                }

                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public final int hashCode() {
        return 7 + this.iName.hashCode();
    }

    @Override
    public int compareTo(Object other) {
        return this.iName.compareTo(((Enum) other).iName);
    }

    @Override
    public String toString() {
        String str = this.getAlias((Locale) null);
        return str == null ? this.iName : str;
    }

    public String getAlias() {
        return this.getAlias((Locale) null);
    }

    public String getAlias(Locale locale) {
        return this.getAssist(this.getClass().getName()).getResource(this.getName(), locale);
    }

    Assist getAssist(String classname) {
        return new DefaultAssist(classname);
    }

    protected static String[] getEnumAliases(Class enumClass, Locale locale) {
        List enums = getEnumList(enumClass);
        String[] aliases = new String[enums.size()];

        for (int i = 0; i < enums.size(); ++i) {
            Enum _enum = (Enum) enums.get(i);
            aliases[i] = _enum.getAlias(locale);
        }

        return aliases;
    }

    public static void getStaticFields(Class enumCalss) {
        Field[] fields = enumCalss.getDeclaredFields();

        for (int i = 0; i < fields.length; ++i) {
            if ((fields[i].getModifiers() & 8) != 0 && (fields[i].getModifiers() & 1) != 0) {
                try {
                    fields[i].get((Object) null);
                    break;
                } catch (IllegalArgumentException var4) {
                    ;
                } catch (IllegalAccessException var5) {
                    ;
                }
            }
        }

    }

}
