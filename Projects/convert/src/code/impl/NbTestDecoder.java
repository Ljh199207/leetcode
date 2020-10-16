package code.impl;

import code.Decoder;
import entity.MeterMessage;
import entity.ServerMessage;

/**
 * 物联网表测试指令
 */
public class NbTestDecoder implements Decoder {

    @Override
    public byte[] decoder(MeterMessage message) {
        System.out.println("物联网表测试指令------");
        return new byte[0];
    }

    @Override
    public byte[] encoder(ServerMessage message) {
        return new byte[0];
    }

}
