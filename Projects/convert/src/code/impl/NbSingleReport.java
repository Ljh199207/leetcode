package code.impl;

import code.Decoder;
import entity.MeterMessage;
import entity.ServerMessage;
import utils.HnTcpConstants;
import utils.UtilTool;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

/**
 * 表上报功能
 */
public class NbSingleReport implements Decoder {
    @Override
    public byte[] decoder(MeterMessage message) {
        return new byte[0];
    }

    @Override
    public byte[] encoder(ServerMessage message) {
        ByteBuffer buffer = ByteBuffer.allocate(24);
        byte[] data = UtilTool.stringToBytes(message.getData());
        //抄表时间
        String readTime = UtilTool.bytesToString(data, 4, 10);
        //物联网表编号 8
        buffer.put(UtilTool.stringToBytes(message.getMeterNo()));
        //表具类型 预付费
        String gasType = UtilTool.bytesToString(data, 25, 26);
        buffer.put(UtilTool.stringToBytes(gasType));
        //标况 累计用量
        String totalGas = UtilTool.bytesToString(data, 28, 32);
        buffer.put(UtilTool.stringToBytes(totalGas));
        //周期用气量
        buffer.put(HnTcpConstants.FOUR_BYTE);
        //累计使用金额
        buffer.put(HnTcpConstants.FOUR_BYTE);
        //表内余额
        String balance = UtilTool.bytesToString(data, 44, 48);
        buffer.put(UtilTool.stringToBytes(balance));
        //预留
        buffer.put(HnTcpConstants.SUCCESS);
        //阀门状态
        String valveStatus = UtilTool.bytesToString(data, 12, 24);

        return new byte[0];
    }
}
