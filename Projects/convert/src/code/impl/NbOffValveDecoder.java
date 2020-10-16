package code.impl;

import code.Decoder;
import entity.MeterMessage;
import entity.ServerMessage;
import enums.CmdEnum;
import utils.CRC16M;
import utils.DataUtil;
import utils.HnTcpConstants;
import utils.UtilTool;

import java.nio.ByteBuffer;

/**
 * 物联网表关阀
 */
public class NbOffValveDecoder implements Decoder {
    @Override
    public byte[] decoder(MeterMessage message) {
        String data = "00000000";
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
