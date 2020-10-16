package code.impl;

import code.Decoder;
import entity.MeterMessage;
import entity.ServerMessage;
import utils.UtilTool;

/**
 * 物联网表开阀
 */
public class NbOnValveDecoder implements Decoder {
    @Override
    public byte[] decoder(MeterMessage message) {
        String data = "00000001";
        return UtilTool.hexString2byteArray(data);
    }

    @Override
    public byte[] encoder(ServerMessage message) {
        byte[] data = UtilTool.stringToBytes(message.getData());
        String d = UtilTool.response(data);
        d = d + message.getMeterNo();
        return UtilTool.stringToBytes(d);
    }
}
