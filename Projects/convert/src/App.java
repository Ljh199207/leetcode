import convert.InDecoder;
import utils.UtilTool;

import java.util.Calendar;

public class App {


    public static void main(String[] args) {
        /*  String se = "6d9ce0d160904705821be8dbbc0d8e00";
         *//* byte[] srtbyte = se.getBytes();
        for (byte b : srtbyte) {
            System.out.println(b);
        }
*//*
        byte[] mes= UtilTool.stringToBytes(se);
        for (byte b : mes) {
            System.out.println(b);
        }*/
        /*String sessionId = "19123100000002";
        sessionId = sessionId.substring(2, 6) + sessionId.substring(10, 14);
        System.out.println(sessionId);*/
        /*String message = "6800000000010107003839383631313138323139303037333939323237720000000000000000005a454b4c4d303033000000000000000000000000000000000000000000000000000010010003022d0000460113003852030000000000000000000000000000000000000000000000be0a16";
        byte[] mes = UtilTool.hexString2byteArray(message);
        //获取报文Id
        System.out.println(sessionId);
        for (int i = 0; i < mes.length; i++) {
            if (i % 10 == 0) {
                System.out.println();
            }
            System.out.print("  " + mes[i]);

        }*/
/*       String message = "6821003052000019082161144871122019123115371207001908216114487153e716";
        InDecoder.decoder(message);*/

                      //68f527000100402500001908216114487112000019123712000007001908216114487107dd0a16
        String mes = "68f5740001002140010001200526605500020000af010000000054000600ca00200706104001000020200706e3000000e300000001010000e3000000e3000000000000000000000080841e00000000003130330000000000000000004f027301d401af0177fd4f00680e8a000100000000110a16";
        InDecoder.encode(mes);
    /*   String a = mes.substring(0,16);
        System.out.println(a.length()+ a);
        System.out.println(UtilTool.getYear());*/


        /*byte[] b1 = UtilTool.stringToBytes("0000");
        for (byte b : b1) {
            System.out.println(b);
        }
        byte[] b2 = UtilTool.bcdStringToBytes("0000");
        for (byte b : b2) {
            System.out.println(b);
        }*/
        // byte[] mes = UtilTool.stringToBytes("20191231153712");

       /* byte[] b1 = hexStringToBytes("20191231153712");
        for (int i = 0; i < b1.length; i++) {
            System.out.println("[" + i + "]:" + b1[i]);
        }*/
      /*  byte []out = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
                (byte)0x10, (byte)0x11, (byte)0x16, (byte)127, (byte)128, (byte)255 };


        System.out.println( bytesToHexString((byte)10));
        System.out.println( bytesToHexString(out) );
        System.out.println( bytesToHexString(out, 0) );
        System.out.println( bytesToHexString(out, 1) );

        byte []b1 = hexStringToBytes("0123456789ABCDEF");
        for( int i = 0; i < b1.length; i++ ) {
            System.out.println("[" + i + "]:" + b1[i]);
        }
        System.out.println( bytesToHexString(b1) );

        byte []b2 = bcdStringToBytes("1234567890123456");
        for( int i = 0; i < b2.length; i++ ) {
            System.out.println("[" + i + "]:" + b2[i]);
        }
        System.out.println( bytesToHexString(b2) );

        byte []bcdBytes = {0, 1, 2, 3, 4, 5, 6, 7, 9, 111,
                10, 11, 21, 31, 41, 51, 61, 71, 81, 91, 99};
        System.out.println( bcdToHexString(bcdBytes) );*/
    }

    /*    public static String bytesToHexString(byte src) {
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
    */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
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
}