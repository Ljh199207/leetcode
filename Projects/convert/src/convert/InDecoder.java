package convert;


import code.Decoder;
import code.impl.*;
import entity.MeterMessage;
import entity.ServerMessage;
import enums.CmdEnum;
import utils.CRC16M;
import utils.DataUtil;
import utils.HnTcpConstants;
import enums.StatusCode;
import utils.UtilTool;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ljh
 */
public class InDecoder {

    private static Map<CmdEnum, Decoder> map = new HashMap<>();

    static {
        map.put(CmdEnum.CmdEnum_A, new NbTestDecoder());
        map.put(CmdEnum.CmdEnum_B, new NbRestoreSetDecoder());
        map.put(CmdEnum.CmdEnum_C, new NbHeartBeatDecoder());
        map.put(CmdEnum.CmdEnum_S, new NbSingleReport());
        map.put(CmdEnum.CmdEnum_T, new NbIpSet());
        map.put(CmdEnum.CmdEnum_V, new NbRepoterCycleSet());
        map.put(CmdEnum.CmdEnum_X, new NbTopUpDecoder());
        map.put(CmdEnum.CmdEnum_Y, new NbOnValveDecoder());
        map.put(CmdEnum.CmdEnum_W, new NbRepoterCycleSet());
        map.put(CmdEnum.CmdEnum_Z, new NbOffValveDecoder());

    }

    /**
     * 高坪->前卫协议
     *
     * @param ms
     * @return
     */
    public static byte[] decoder(String ms) {
        byte[] data = UtilTool.stringToBytes(ms);
        if (data != null) {
            int len = data.length;
            if (len < 27 || data[0] != HnTcpConstants.START || data[len - 1] != HnTcpConstants.END) {
                return UtilTool.bcdStringToBytes(StatusCode.DOMAIN_INCOMPLETE.getCode());
            }
            // cmdCode 功能码
            String cmdCode = UtilTool.bytesToString(data, 3, 5);
            //direction 传送方向
            int direction = data[5] & 0xFF;
            //flag 主站待发送指令条数
            int flag = data[6] & 0xFF;
            //shortMeterNo 从站编号
            String meterNo = UtilTool.bytesToString(data, 7, 15);
            meterNo = meterNo + "0000";
            // sessionId 报文 ID
            String sessionId = UtilTool.bytesToString(data, 15, 22);
            sessionId = sessionId.substring(2, 6) + sessionId.substring(10, 14);
            int dataLen = UtilTool.bytesToShort(data, 22);
            if (!UtilTool.checkCrc16(data, 0, 24 + dataLen)) {
                return UtilTool.bcdStringToBytes(StatusCode.CHECK_FAIL.getCode());
            }
            String data1 = UtilTool.bytesToString(data, 24, data.length - 3);
            MeterMessage meterMessage = new MeterMessage(meterNo, cmdCode, direction, flag, ms, data1, sessionId);
            return inDeCodeBuffer(meterMessage);
        } else {
            return UtilTool.stringToBytes(StatusCode.EMPTY.getCode());
        }
    }


    private static byte[] inDeCodeBuffer(MeterMessage message) {
        ByteBuffer ib = ByteBuffer.allocate(message.getLength() - 4);
        //起始符
        ib.put(HnTcpConstants.START);
        //标志符
        ib.put(HnTcpConstants.HEAD);
        //报文长度
        ib.put(UtilTool.shortToBytes((short) message.getLength().intValue()));
        //协议版本
        ib.put(UtilTool.shortToBytes((short) 1));
        //功能码
        ib.put(UtilTool.stringToBytes(CmdEnum.codeOf(message.getCmd()).getQwCode()));
        //传送方向
        ib.put(DataUtil.convertDirection(message.getDirection()).byteValue());
        //请求响应标志  0 请求
        ib.put(Integer.valueOf(0).byteValue());
        //表号
        ib.put(UtilTool.stringToBytes(message.getMeterNo()));
        //sessionId
        ib.put(UtilTool.stringToBytes(message.getSessionId()));
        //加密位
        ib.put(HnTcpConstants.ENCRYPTION_BIT);
        //dataLen
        ib.put(UtilTool.shortToBytes(message.getDataLen()));
        Decoder decoder = map.get(CmdEnum.codeOf(message.getCmd()));
        ib.put(decoder.decoder(message));
        byte[] data = ib.array();
        byte[] crc = UtilTool.shortToBytes(CRC16M.calcCRC16(data));
        ByteBuffer buffer = ByteBuffer.allocate(message.getLength());
        buffer.put(data);
        UtilTool.reverseByte(crc);
        buffer.put(crc);
        //尾标识
        buffer.put(HnTcpConstants.FOOT);
        //结束
        buffer.put(HnTcpConstants.END);
        buffer.flip();
        byte[] result = buffer.array();
        String hex = UtilTool.byteArray2HexString(result, false);
        System.out.println(hex);
        return result;
    }

    /**
     * 前卫->南充高坪
     *
     * @param ms
     * @return
     */
    public static byte[] encode(String ms) {
        byte[] data = UtilTool.stringToBytes(ms);
        if (data == null) {
            return UtilTool.stringToBytes(StatusCode.EMPTY.getCode());
        }
        int len = data.length;
        if (data[0] != HnTcpConstants.START || data[data.length - 1] != HnTcpConstants.END) {
            return UtilTool.bcdStringToBytes(StatusCode.DOMAIN_INCOMPLETE.getCode());
        }
        // cmdCode 功能码
        String cmd = UtilTool.byteArray2HexString(new byte[]{data[6], data[7]}, false).toUpperCase();
        // String cmd2 = UtilTool.bytesToString(data, 6, 8);

        //截取表号//
        String meterNo = UtilTool.bytesToString(data, 10, 20);
        if (meterNo != null && meterNo.length() > 0) {
            meterNo = meterNo.substring(0, 16);
        }
        //获取报文Id
        String sessionId = UtilTool.bytesToString(data, 20, 24);
        if (sessionId != null && sessionId.length() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(UtilTool.getYear());
            stringBuilder.append(sessionId.substring(0, 4));
            stringBuilder.append(("0000"));
            stringBuilder.append(sessionId.substring(4, 8));
            sessionId = stringBuilder.toString();
        }
        //传送方向
        int direction = data[8] & 0xFF;
        //请求响应
        int flag = data[9] & 0xFF;
        //获取数据域
        String hexData = UtilTool.bytesToString(data, 28, data.length - 4);
        ServerMessage serverMessage = new ServerMessage(meterNo, cmd, direction, flag, sessionId, ms, hexData);
        enCodeBuffer(serverMessage);
        return null;
    }

    private static byte[] enCodeBuffer(ServerMessage message) {
        ByteBuffer ib = ByteBuffer.allocate(message.getLength() - 3);
        //起始符
        ib.put(HnTcpConstants.START);
        //报文长度
        ib.put(UtilTool.shortToBytes((short) message.getLength().intValue()));
        //功能码
        ib.put(UtilTool.stringToBytes(CmdEnum.codeOf(message.getCmd()).getQwCode()));
        //传送方向
        ib.put(Integer.valueOf(3).byteValue());
        //主站待发送指令条数
        ib.put(Integer.valueOf(0).byteValue());
        //表号
        ib.put(UtilTool.stringToBytes(message.getMeterNo()));
        //sessionId
        ib.put(UtilTool.stringToBytes(message.getSessionId()));
        //dataLen
        ib.put(UtilTool.shortToBytes(message.getDataLen()));
        Decoder decoder = map.get(CmdEnum.QwcodeOf(message.getCmd()));
        ib.put(decoder.encoder(message));
        byte[] data = ib.array();
        byte[] crc = UtilTool.shortToBytes(CRC16M.calcCRC16(data));
        ByteBuffer buffer = ByteBuffer.allocate(message.getLength());
        buffer.put(data);
        UtilTool.reverseByte(crc);
        buffer.put(crc);
        //结束
        buffer.put(HnTcpConstants.END);
        buffer.flip();
        byte[] result = buffer.array();
        String hex = UtilTool.byteArray2HexString(result, false);
        System.out.println(hex);
        return result;
    }
}