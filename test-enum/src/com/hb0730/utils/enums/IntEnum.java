package com.hb0730.utils.enums;

import java.util.Iterator;
import java.util.List;

/**
 * <p></p>
 *
 * @author bing_huang
 * @since V1.0
 */
public abstract class IntEnum extends Enum {

    private final int iValue;


    protected IntEnum(String name, int value) {
        super(name);
        this.iValue = value;
    }

    protected static Enum getEnum(Class enumClass, int value) {
        if (enumClass == null) {
            throw new IllegalArgumentException("The Enum Class must not be null");
        } else {
            List list = Enum.getEnumList(enumClass);
            Iterator it = list.iterator();

            IntEnum _enum;
            do {
                if (!it.hasNext()) {
                    return null;
                }

                _enum = (IntEnum) it.next();
            } while (_enum.getValue() != value);

            return _enum;
        }
    }

    public final int getValue() {
        return this.iValue;
    }

    public int compareTo(Object other) {
        return this.iValue - ((IntEnum) other).iValue;
    }
}
