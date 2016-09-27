package me.flyness.toolbox.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;

/**
 * Created by lizhitao on 2016/9/27.
 * CRC32工具
 */
public class CRCUtil {
    /**
     * 将对象列表进行CRC32运算
     *
     * @param fields 对象列表
     * @return int CRC32计算后的int值
     */
    public static int crc32(Object... fields) {
        if (fields == null)
            return 0;
        byte[] bytes = toBytes(fields);
        CRC32 crc32 = new CRC32();
        crc32.update(bytes);
        return (int) crc32.getValue();
    }

    private static byte[] toBytes(Object[] fields) {
        List<byte[]> list = new ArrayList<byte[]>();
        int l = 0;
        for (Object field : fields) {
            byte[] bytes = toBytes(field);
            l += bytes.length;
            list.add(bytes);
        }
        byte[] nbytes = new byte[l];
        int i = 0;
        for (byte[] b : list) {
            System.arraycopy(b, 0, nbytes, i, b.length);
            i += b.length;
        }
        return nbytes;
    }

    private static byte[] toBytes(Object field) {
        try {
            if (field == null)
                return new byte[]{(byte) 0};
            if (field instanceof byte[])
                return (byte[]) field;
            if (field instanceof String)
                return ((String) field).getBytes("UTF-8");
            if (field instanceof Number)
                return longToBytes(((Number) field).longValue());
            if (field instanceof Boolean)
                return new byte[]{((Boolean) field).booleanValue() ? (byte) 1 : (byte) 0};
            return toBytes(field.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new byte[]{(byte) 0};
        }
    }

    private static byte[] longToBytes(long l) {
        byte[] b = new byte[8];
        for (int i = 0; i < 8; i++) {
            b[i] = new Long(l).byteValue();
            l = l >> 8;
        }
        return b;
    }
}
