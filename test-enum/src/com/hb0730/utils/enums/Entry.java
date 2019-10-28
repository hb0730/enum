package com.hb0730.utils.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 *
 * @author bing_huang
 * @since V1.0
 */
class Entry {

    final Map map;
    final List list;


    private Entry() {
        this.map = new HashMap();
        this.list = new ArrayList();
    }

    // $FF: synthetic method
    Entry(Base x0) {
        this();
    }
}
