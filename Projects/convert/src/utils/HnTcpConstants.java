package utils;

/**
 * @Author: Two2
 */
public class HnTcpConstants {

    /**
     * 起始符
     */
    public static final byte START = 0x68;
    /**
     * 头标识
     */
    public static final byte HEAD = (byte) 0xF5;
    /**
     * 尾标识
     */
    public static final byte FOOT = 0x0A;
    /**
     * 结束符
     */
    public static final byte END = 0x16;
    /**
     * 传送方向,主站发出
     */
    public static final byte DIRECTION_SERVER = 0x0;
    /**
     * 传送方向,从站发出
     */
    public static final byte DIRECTION_CLIENT = 0x1;
    /**
     * 请求响应标志,请求
     */
    public static final byte FLAG_REQUEST = 0x0;
    /**
     * 请求响应标志,响应
     */
    public static final byte FLAG_RESPONSE = 0x1;
    /**
     * 加密位默认值0
     */
    public static final byte[] ENCRYPTION_BIT = {0x0, 0x0};

    //以下为响应码
    /**
     * 成功
     */
    public static final byte[] SUCCESS = {0x0, 0x0};
    /**
     * 参数设置错误
     */
    public static final byte[] PARAMETER_SET = {0x3, 0x1};
    /**
     * 参数查询错误
     */
    public static final byte[] PARAMETER_QUERY = {0x3, 0x2};
    /**
     * 上报错误
     */
    public static final byte[] REPORT = {0x4, 0x1};
    /**
     * 开阀错误
     */
    public static final byte[] OPEN_VALVE = {0x5, 0x1};
    /**
     * 关阀错误
     */
    public static final byte[] CLOSE_VALVE = {0x5, 0x2};
    /**
     * 成功
     */
    public static final byte[] FOUR_BYTE = {0x0, 0x0,0x0,0x0};
    /**
     * 表端上报异常和客户异常对应表
     */
    public static final String[] ALARMCODE = {"", "00001002", "", "", "", "", "", "", "", "", "", "", "", "00001005", "", "",
            "00001004", "00001007", "", "", "", "", "", "", "", "", "00001009", "", "", "", "", "",
            "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
            "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};

}
