package code.impl;

import code.Decoder;
import entity.MeterMessage;
import entity.ServerMessage;
import enums.StatusCode;
import utils.UtilTool;

import java.nio.ByteBuffer;

/**
 * 充值
 */
public class NbTopUpDecoder implements Decoder {
    @Override
    public byte[] decoder(MeterMessage message) {
        ByteBuffer buffer = ByteBuffer.allocate(12);
        byte[] data = UtilTool.stringToBytes(message.getData());
        //00 000000 80841e00 00 00 00 00
        //计费总类
        //String type = "00";
        buffer.put((byte) 0);
        //保留位
        //String keep = "000000";
        buffer.put((byte) 000);
        //充值总购金额
        String amount = UtilTool.bytesToString(data, 8, 12);
        buffer.put(UtilTool.stringToBytes(amount));
        //充值总购气量
        //String totalGas = "00000000";
        buffer.put((byte) 000);
        buffer.flip();
        byte[] result = buffer.array();
        return result;
    }

    @Override
    public byte[] encoder(ServerMessage message) {
        byte[] data = UtilTool.stringToBytes(message.getData());
        String d = UtilTool.response(data);
        String type = UtilTool.bytesToString(data, 2, 3);
        String amount;
        if (type.equals("0")) {
            amount = UtilTool.bytesToString(data, 4, 8);
        } else {
            amount = "00000000";
        }
        d = d + message.getMeterNo() + amount;
        return UtilTool.stringToBytes(d);
    }
}
