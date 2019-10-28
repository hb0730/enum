import com.hb0730.utils.enums.IntEnum;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 *
 * @author bing_huang
 * @since V1.0
 */
public class EnumTest {
    public static void main(String[] args) {
        BillBizTypeEnum.getEnumList();
        BillBizTypeEnum.getEnumMap();
    }
}

class BillBizTypeEnum extends IntEnum {
    public static final int JJGC_VALUE = 1;
    public static final int SBCG_VALUE = 2;
    public static final int WXZC_VALUE = 3;
    public static final int QT_VALUE = 4;

    public static final BillBizTypeEnum JJGC = new BillBizTypeEnum("JJGC", JJGC_VALUE);
    public static final BillBizTypeEnum SBCG = new BillBizTypeEnum("SBCG", SBCG_VALUE);
    public static final BillBizTypeEnum WXZC = new BillBizTypeEnum("WXZC", WXZC_VALUE);
    public static final BillBizTypeEnum QT = new BillBizTypeEnum("QT", QT_VALUE);

    /**
     * construct function
     *
     * @param integer billBizTypeEnum
     */
    private BillBizTypeEnum(String name, int billBizTypeEnum) {
        super(name, billBizTypeEnum);
    }

    /**
     * getEnum function
     *
     * @param String arguments
     */
    public static BillBizTypeEnum getEnum(String billBizTypeEnum) {
        return (BillBizTypeEnum) getEnum(BillBizTypeEnum.class, billBizTypeEnum);
    }

    /**
     * getEnum function
     *
     * @param String arguments
     */
    public static BillBizTypeEnum getEnum(int billBizTypeEnum) {
        return (BillBizTypeEnum) getEnum(BillBizTypeEnum.class, billBizTypeEnum);
    }

    /**
     * getEnumMap function
     */
    public static Map getEnumMap() {
        return getEnumMap(BillBizTypeEnum.class);
    }

    /**
     * getEnumList function
     */
    public static List getEnumList() {
        return getEnumList(BillBizTypeEnum.class);
    }

    /**
     * getIterator function
     */
    public static Iterator iterator() {
        return iterator(BillBizTypeEnum.class);
    }
}
