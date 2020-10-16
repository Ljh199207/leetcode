package code.impl;

import code.Decoder;
import entity.MeterMessage;
import entity.ServerMessage;
import utils.UtilTool;

import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 上报周期设置
 */
public class NbRepoterCycleSet implements Decoder {

    @Override
    public byte[] decoder(MeterMessage message) {
        ByteBuffer buffer = ByteBuffer.allocate(24);
        byte[] data = UtilTool.stringToBytes(message.getData());
        String reportType = UtilTool.bytesToString(data, 8, 9);
        int cycle = 1;
        if (reportType.equals("1")) {
            buffer.put((byte) 4);
            cycle = 1;
        } else if (reportType.equals("2")) {
            buffer.put((byte) 4);
            cycle = 7;
        } else if (reportType.equals("3")) {
            buffer.put((byte) 5);
            cycle = 1;
        }
        LocalDateTime time = LocalDateTime.now();
        String peakShiftTime = LocalDateTime.of(time.getYear(), time.getMonth(), time.getDayOfMonth(), time.getHour(), time.getMinute(), time.getSecond()).format(DateTimeFormatter.ofPattern("yyMMddHHmmss"));

        buffer.put(UtilTool.stringToBytes(peakShiftTime));
        //保留位
        buffer.put((byte) 0);
        //错峰时长8小时
        buffer.put(UtilTool.shortToBytes(8));
        //错峰间隔60秒
        buffer.put(UtilTool.shortToBytes(60));
        //周期长度 几日
        buffer.put(UtilTool.shortToBytes(cycle));
        //设置默认定时上传时间和上传间隔
        buffer.put(UtilTool.stringToBytes(peakShiftTime));
        buffer.put(UtilTool.intToBytes(120));
        buffer.flip();
        byte[] result = buffer.array();
        return result;
    }

    @Override
    public byte[] encoder(ServerMessage message) {
        return new byte[0];
    }
}
