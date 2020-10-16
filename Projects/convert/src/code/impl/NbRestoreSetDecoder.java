package code.impl;

import code.Decoder;
import entity.MeterMessage;
import entity.ServerMessage;

/**
 * 表具恢复出厂设置
 */
public class NbRestoreSetDecoder implements Decoder {
    @Override
    public byte[] decoder(MeterMessage message) {
        System.out.println("表具恢复出厂设置------");
        return new byte[0];
    }

    @Override
    public byte[] encoder(ServerMessage message) {
        return new byte[0];
    }
}
