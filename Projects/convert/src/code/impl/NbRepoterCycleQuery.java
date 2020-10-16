package code.impl;

import code.Decoder;
import entity.MeterMessage;
import entity.ServerMessage;
import utils.UtilTool;

import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 上报周期查询
 */
public class NbRepoterCycleQuery implements Decoder {

    @Override
    public byte[] decoder(MeterMessage message) {
        return null;
    }

    @Override
    public byte[] encoder(ServerMessage message) {
        return new byte[0];
    }
}
