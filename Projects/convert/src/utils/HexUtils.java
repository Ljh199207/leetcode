package utils;

public class HexUtils {

    public static void main(String[] args) {
        byte[] out = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
                (byte) 0x07, (byte) 0x11, (byte) 0x12, (byte) 127, (byte) 128, (byte) 255};

        byte[] in = {
                (byte) 0x40, (byte) 0x21};

 /*       System.out.println( bytesToHexString((byte)10));
        System.out.println( bytesToHexString(out) );
        System.out.println( bytesToHexString(out, 0) );
        System.out.println( bytesToHexString(out, 1) );
*/
    /*    byte []b1 = hexStringToBytes("0123456789ABCDEF");
        for( int i = 0; i < b1.length; i++ ) {
            System.out.println("[" + i + "]:" + b1[i]);
        }
        System.out.println( bytesToHexString(b1) );*/

    /*    byte []b2 = bcdStringToBytes("1234567890123456");
        for( int i = 0; i < b2.length; i++ ) {
            System.out.println("[" + i + "]:" + b2[i]);
        }*/
        System.out.println(bytesToHexString(in));

       /* byte []bcdBytes = {0, 1, 2, 3, 4, 5, 6, 7, 9, 111,
                10, 11, 21, 31, 41, 51, 61, 71, 81, 91, 99};
        System.out.println( bcdToHexString(bcdBytes) );*/
    }

    public static String bytesToHexString(byte src) {
        int v = src & 0xFF;
        String hv = Integer.toHexString(v);
        return hv;
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            hv = hv.toUpperCase();
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static String bytesToHexString(byte[] src, int mode) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {

            if (i != 0 && i % 16 == 0) {
                stringBuilder.append("\r\n");
            }

            if (mode == 1) {
                stringBuilder.append("0x");
            }

            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);

            if (mode == 1 && i + 1 != src.length) {
                stringBuilder.append(',');
            }
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    public static String bcdToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            int v1 = v / 10;
            int v2 = v % 10;

            if (v >= 0 && v < 100) {
                stringBuilder.append(v1);
                stringBuilder.append(v2);
            }
        }
        return stringBuilder.toString();
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("") || hexString.length() % 2 != 0) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
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
}
