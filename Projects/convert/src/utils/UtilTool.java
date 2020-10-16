package utils;


import enums.StatusCode;

import java.math.BigInteger;
import java.util.Calendar;


public class UtilTool {
    /**
     * crc校验
     *
     * @param data  数据包
     * @param begin 从数组的第begin位开始
     * @param end   从数组的第end位开始, 不包含end
     * @return short
     */
    public static boolean checkCrc16(byte[] data, int begin, int end) {
        int wCRC = 0xffff;
        for (byte datum : subBytes(data, begin, end)) {
            wCRC = (wCRC ^ (0xFF & datum));
            for (int i = 0; i < 8; i++) {
                if ((wCRC & 0x0001) > 0) {
                    wCRC = wCRC >> 1 ^ 0xA001;
                } else {
                    wCRC = wCRC >> 1;
                }
            }
        }
        return wCRC == bytesToShort(data, end);
    }

    /**
     * mac验证
     *
     * @param dt   数据包
     * @param mlen mac长度
     * @return byte[]
     */
    public static byte[] getMac(byte[] dt, int mlen) {
        int n, k, len, ilen;
        // original input String lgth
        len = dt.length;
        // 原始串和mac 长度的余数
        k = len % mlen;
        n = mlen - k;
        ilen = len + n;
        byte[] bt = new byte[ilen];
        for (int i = 0; i < len; i++) {
            dt[i] = (byte) (dt[i] > 0x00 ? dt[i] : dt[i] + 256);
            bt[i] = dt[i];
        }
        bt[len] = 127;
        byte[] bo = new byte[mlen];
        for (int i = 0; i < ilen; ) {
            for (int j = 0; j < mlen; j++) {
                bo[j] ^= bt[i];
                i++;
            }
        }
        for (int i = 0; i < mlen; i++) {
            // 如果遇到\r \n
            if ((bo[i] >= 0x00 ? bo[i] : bo[i] + 256) >= 0x80) {
                // "\r"
                bo[i] ^= 0x80;
            }
            if (bo[i] == 0x0d) {
                // "\r"
                bo[i] = 0x4d;
            }
            if (bo[i] == 0x0a) {
                // "\n"
                bo[i] = 0x4a;
            }
            if (bo[i] == 0x3a) {
                // ":"
                bo[i] = 0x7a;
            }
            if (bo[i] == 0x7c) {
                // "|"
                bo[i] = 0x3c;
            }
            if (bo[i] == 0x00) {
                // "0"
                bo[i] = 0x40;
            }
        }
        return bo;
    }

    /**
     * byte数组中取int数值，本方法适用于(低位在前，高位在后)的顺序
     *
     * @param data   byte数组
     * @param offset 从数组的第offset位开始
     * @return int数值
     */
    public static int bytesToShort(byte[] data, int offset) {
        int value;
        value = (data[offset] & 0xFF) | ((data[offset + 1] & 0xFF) << 8);
        return value;
    }

    /**
     * byte数组中取int数值(有符号)，本方法适用于(低位在前，高位在后)的顺序
     * 注:此方法用于有符号短整型
     *
     * @param data   byte数组
     * @param offset 从数组的第offset位开始
     * @return int数值
     */
    public static int bytes2Short(byte[] data, int offset) {
        String value = UtilTool.byteArray2HexString(new byte[]{data[offset + 1], data[offset]}, false);
        return new BigInteger(value, 16).shortValue();
    }

    /**
     * byte数组中取int数值，本方法适用于(低位在前，高位在后)的顺序，和和intToBytes（）配套使用
     *
     * @param data   byte数组
     * @param offset 从数组的第offset位开始
     * @return int数值
     */
    public static int bytesToInt(byte[] data, int offset) {
        int value;
        value = (data[offset] & 0xFF)
                | ((data[offset + 1] & 0xFF) << 8)
                | ((data[offset + 2] & 0xFF) << 16)
                | ((data[offset + 3] & 0xFF) << 24);
        return value;
    }

    /**
     * byte数组中取long数值，本方法适用于(低位在前，高位在后)的顺序,和和longToBytes（）配套使用
     *
     * @param data   byte数组
     * @param offset 从数组的第offset位开始
     * @return int数值
     */
    public static long bytesToLong(byte[] data, int offset) {
        long value;
        value = (data[offset] & 0xFF)
                | ((data[offset + 1] & 0xFF) << 8)
                | ((data[offset + 2] & 0xFF) << 16)
                | ((data[offset + 3] & 0xFF) << 24)
                | ((long) (data[offset + 4] & 0xFF) << 32)
                | ((long) (data[offset + 5] & 0xFF) << 40)
                | ((long) (data[offset + 6] & 0xFF) << 48)
                | ((long) (data[offset + 7] & 0xFF) << 56);
        return value;
    }


    /**
     * 字符转bytes
     *
     * @param str 字符
     * @return byte[]
     */
    public static byte[] stringToBytes(String str) {
        return UtilTool.hexString2byteArray(str);
    }

    /**
     * short转bytes
     *
     * @param a int
     * @return byte[]
     */
    public static byte[] shortToBytes(int a) {
        return new byte[]{
                (byte) (a & 0xFF),
                (byte) ((a >> 8) & 0xFF)
        };
    }

    /**
     * int转bytes
     *
     * @param a int
     * @return byte[]
     */
    public static byte[] intToBytes(int a) {
        return new byte[]{
                (byte) (a & 0xFF),
                (byte) ((a >> 8) & 0xFF),
                (byte) ((a >> 16) & 0xFF),
                (byte) ((a >> 24) & 0xFF)
        };
    }

    /**
     * long转 bytes
     *
     * @param src
     * @return
     */
    public static byte[] long2Bytes(long src) {
        return new byte[]{
                (byte) (src & 0xFF),
                (byte) ((src >> 8) & 0xFF),
                (byte) ((src >> 16) & 0xFF),
                (byte) ((src >> 24) & 0xFF),
                (byte) ((src >> 32) & 0xFF),
                (byte) ((src >> 40) & 0xFF),
                (byte) ((src >> 48) & 0xFF),
                (byte) ((src >> 56) & 0xFF),
        };
    }

    /**
     * 将字节数组转化为16进制字符串
     * flag = true 则字节之间加空格，flag=false则字节之间不加空格
     *
     * @param b
     * @return
     */
    public static String byteArray2HexString(byte[] b, boolean flag) {
        StringBuffer buffer = new StringBuffer();
        int temp = 0;

        for (int i = 0; i < b.length; i++) {
            if (b[i] < 0) {
                temp = 256 + b[i];
            } else {
                temp = b[i];
            }
            String ts = Integer.toHexString(temp);
            if (ts.length() == 1) {
                if (flag)
                    buffer.append("0" + ts + " ");
                else
                    buffer.append("0" + ts);
            } else {
                if (flag)
                    buffer.append(ts + " ");
                else
                    buffer.append(ts);
            }
        }
        return buffer.toString();
    }


    /**
     * 十六进制字符串->字节数组
     *
     * @param hexString
     * @return
     */
    public static byte[] hexString2byteArray(String hexString) {
        int length = hexString.length();

        byte[] b = new byte[length / 2];

        for (int i = 0; i < length / 2; i++) {
            b[i] = (byte) ((int) Integer.valueOf(hexString.substring(2 * i,
                    2 * i + 2), 16));
        }
        return b;
    }

    /**
     * 字节反转 低字节在前，高字节在后
     *
     * @param str
     * @return
     */
    public static String reverse(String str) {
        int length = str.length();
        String string = "";
        for (int i = length / 2 - 1; i >= 0; i--) {
            string += str.substring(2 * i, 2 * i + 2);
        }
        return string;
    }

    /**
     * 将字符串格式化成指定长度，字符串前补零
     *
     * @param str
     * @param length
     * @return
     */
    public static String preFormat(String str, int length) {
        int ln = str.trim().length();
        for (int i = 0; i < length - ln; i++) {
            str = "0" + str.trim();
        }
        return str;

    }

    /**
     * 将字符串格式化成指定长度，字符串后面补零
     *
     * @param str
     * @param length
     * @return
     */
    public static String format(String str, int length) {
        int ln = str.trim().length();
        for (int i = 0; i < length - ln; i++) {
            str = str.trim() + "0";
        }
        return str;
    }

    public static byte[] bcdStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("") || hexString.length() % 2 != 0) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) * 10 + charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static byte[] subBytes(byte[] src, int begin, int end) {
        if (src == null) {
            return null;
        }
        if (begin < 0) {
            begin = 0;
        }
        if (end > src.length) {
            end = src.length;
        }
        final int newSize = end - begin;
        byte[] bs = new byte[newSize];
        System.arraycopy(src, begin, bs, 0, newSize);
        return bs;
    }

    /**
     * byte数组中取字符
     *
     * @param data  byte数组
     * @param begin 从数组的第begin位开始
     * @param end   从数组的第end位开始, 不包含end
     * @return String
     */
    public static String bytesToString(byte[] data, int begin, int end) {
        return UtilTool.byteArray2HexString(subBytes(data, begin, end), false);
    }


    public static void reverseByte(final byte[] array) {
        if (array == null) {
            return;
        }
        reverse(array, 0, array.length);
    }

    private static void reverse(final byte[] array, final int startIndexInclusive, final int endIndexExclusive) {
        if (array == null) {
            return;
        }
        int i = startIndexInclusive < 0 ? 0 : startIndexInclusive;
        int j = Math.min(array.length, endIndexExclusive) - 1;
        byte tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }

    /**
     * 获取当前年份yy
     *
     * @return
     */
    public static String getYear() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        return String.valueOf(year % 100);
    }

    public static String response(byte[] data) {
        String d = UtilTool.bytesToString(data, 0, 2);
        if (StatusCode.SUCCESS.getCode().equals(d)) {
            d = "0000";
        } else {
            d = "3109";
        }
        return d;
    }

}
