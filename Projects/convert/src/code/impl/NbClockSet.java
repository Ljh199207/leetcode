package code.impl;

import code.Decoder;
import entity.MeterMessage;
import entity.ServerMessage;
import enums.StatusCode;
import utils.UtilTool;

/**
 * 时钟设置
 */
public class NbClockSet implements Decoder {
    @Override
    public byte[] decoder(MeterMessage message) {
        byte[] data = UtilTool.stringToBytes(message.getData());
        return UtilTool.subBytes(data, 2, data.length);

    }

    @Override
    public byte[] encoder(ServerMessage message) {
        byte[] data = UtilTool.stringToBytes(message.getData());
        String d = UtilTool.response(data);
        d = d + message.getMeterNo();
        return UtilTool.stringToBytes(d);
    }
}
