package enums;

public enum StatusCode {
    SUCCESS("0000","指令成功"),
    EMPTY("0101","报文为空"),
    DECODER_FAIL("0102","报文解密失败"),
    CHECK_FAIL("0103","报文校验失败"),
    FUNCTION_CODE_EMPTY("0104","报文功能码为空"),
    FUNCTION_CODE_UNKNOWN("0105","报文功能码未知"),
    DOMAIN_INCOMPLETE("0106","报文域不完整"),

    ;
    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    StatusCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
