package code.impl;

import code.Decoder;
import entity.MeterMessage;
import entity.ServerMessage;

/**
 * 物联网表心跳包
 */
public class NbHeartBeatDecoder implements Decoder {
    @Override
    public byte[] decoder(MeterMessage message) {
        System.out.println("物联网表心跳包----");
        return new byte[0];
    }

    @Override
    public byte[] encoder(ServerMessage message) {
        return new byte[0];
    }
}
