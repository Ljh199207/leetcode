package utils;

/**
 * @author ShaoHui
 */
public class CRC16M {

    public static short calcCRC16(byte[] data) {
        int wCRC = 0xffff;
        for (int k = 0; k < data.length; k++) {
            wCRC = (wCRC ^ (int) (0xFF & data[k]));
            for (int i = 0; i < 8; i++) {
                if ((wCRC & 0x0001) > 0) {
                    wCRC = (int) (wCRC >> 1 ^ 0xA001);
                } else {
                    wCRC = (int) (wCRC >> 1);
                }
            }
        }
        wCRC = (int) ((wCRC << 8) | ((wCRC >> 8) & 0xFF));
        return (short) wCRC;
    }
}
