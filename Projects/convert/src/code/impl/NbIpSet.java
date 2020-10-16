package code.impl;

import code.Decoder;
import entity.MeterMessage;
import entity.ServerMessage;
import utils.UtilTool;

public class NbIpSet implements Decoder {
    @Override
    public byte[] decoder(MeterMessage message) {
        byte[] data = UtilTool.stringToBytes(message.getData());
         //ip
        return new byte[0];
    }

    @Override
    public byte[] encoder(ServerMessage message) {
        return new byte[0];
    }
}
