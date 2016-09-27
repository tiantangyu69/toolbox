package me.flyness.toolbox.character;

import java.io.UnsupportedEncodingException;

/**
 * Created by lizhitao on 2016/9/27.
 * 全角半角字符转换
 */
public class FullHalfCharConverter {
    private FullHalfCharConverter() {
    }

    /**
     * 将全角字符转换为半角字符
     *
     * @param fullStr 全角字符串
     * @return String 半角字符串
     */
    public static final String full2Half(String fullStr) {
        StringBuilder outStrBuf = new StringBuilder("");
        String halfStr;
        try {
            for (int i = 0; i < fullStr.length(); ++i) {
                halfStr = fullStr.substring(i, i + 1);
                if (halfStr.equals(" ")) {
                    outStrBuf.append(" ");
                } else {
                    byte[] halfStrBytes = halfStr.getBytes("unicode");
                    if (halfStrBytes[2] == -1) {
                        halfStrBytes[3] = (byte) (halfStrBytes[3] + 32);
                        halfStrBytes[2] = 0;
                        outStrBuf.append(new String(halfStrBytes, "unicode"));
                    } else {
                        outStrBuf.append(halfStr);
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return outStrBuf.toString();
    }

    /**
     * 将半角字符转换为全角字符
     *
     * @param halfStr 半角字符串
     * @return String 全角字符串
     */
    public static final String half2Full(String halfStr) {
        StringBuilder outStrBuf = new StringBuilder("");
        String fullStr;

        try {
            for (int i = 0; i < halfStr.length(); ++i) {
                fullStr = halfStr.substring(i, i + 1);
                if (fullStr.equals(" ")) {
                    outStrBuf.append(fullStr);
                } else {
                    byte[] fullStrBytes = fullStr.getBytes("unicode");
                    if (fullStrBytes[2] == 0) {
                        fullStrBytes[3] = (byte) (fullStrBytes[3] - 32);
                        fullStrBytes[2] = -1;
                        outStrBuf.append(new String(fullStrBytes, "unicode"));
                    } else {
                        outStrBuf.append(fullStr);
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return outStrBuf.toString();
    }
}