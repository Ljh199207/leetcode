package entity;

public class MeterMessage {
    /**
     * 主键,Id
     */
    private Integer id;
    /**
     * 服务端Id
     */
    private Integer serverMesssageId;
    /**
     * 表号
     */
    private String meterNo;
    /**
     * 命令码
     */
    private String cmd;
    /**
     * 命令名称
     */
    private String cmdName;
    /**
     * 报文长度
     */
    private Integer length;
    /**
     * 传送方向
     */
    private Integer direction;
    /**
     * 请求响应标志
     */
    private Integer flag;
    /**
     * 16进制报文
     */
    private String hex;
    /**
     * 数据域报文长度
     */
    private Integer dataLen;
    /**
     * 序列化报文
     */
    private String json;
    /**
     * 数据域
     */
    private String data;
    /**
     * 协议版本
     */
    private int protocolVersion;
    /**
     * 报文Id
     */
    private String sessionId;


    public MeterMessage() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getServerMesssageId() {
        return serverMesssageId;
    }

    public void setServerMesssageId(Integer serverMesssageId) {
        this.serverMesssageId = serverMesssageId;
    }

    public String getMeterNo() {
        return meterNo;
    }

    public void setMeterNo(String meterNo) {
        this.meterNo = meterNo;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getCmdName() {
        return cmdName;
    }

    public void setCmdName(String cmdName) {
        this.cmdName = cmdName;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public Integer getDataLen() {
        return dataLen;
    }

    public void setDataLen(Integer dataLen) {
        this.dataLen = dataLen;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(int protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public MeterMessage(String meterNo, String cmd, Integer direction, Integer flag, String hex, String data, String sessionId) {

        if (data == null || data.length() == 0) {
            this.dataLen = 0;
        } else {
            this.dataLen = data.length() / 2;
        }
        this.meterNo = meterNo;
        this.cmd = cmd;
        this.length = 32 + dataLen;
        this.direction = direction;
        this.flag = flag;
        this.hex = hex;
        this.data = data;
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
