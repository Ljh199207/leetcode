package enums;

import java.util.Optional;
import java.util.stream.Stream;

public enum CmdEnum {
    CmdEnum_A("3001", "物联网表测试指令", "11"),
    CmdEnum_B("3002", "表具恢复出厂设置", "12"),
    CmdEnum_C("3003", "物联网表心跳包", "23"),
    /*    CmdEnum_D("3004", "物联网表注册包"),
        CmdEnum_E("3005", "表具基本参数配置"),
        CmdEnum_F("3006", "表具基本参数查询"),
        CmdEnum_G("3008", "通信协议和版本号查询"),
        CmdEnum_H("3010", "启动远程升级命令"),

        CmdEnum_I("3033", "物联网表清上报任务"),
        CmdEnum_J("3021", "物联网表心跳周期设置"),
        CmdEnum_K("3022", "物联网表心跳周期查询"),
        CmdEnum_L("3031", "物联网表地址设置"),

        CmdEnum_M("3023", "物联表上报周期设置"),
        CmdEnum_N("3024", "物联表上报周期查询"),
        CmdEnum_O("3025", "物联网表时钟设置"),
        CmdEnum_P("3026", "物联网表时钟查询"),
        CmdEnum_Q("3027", "物联网表主站 IP 设置"),
        CmdEnum_R("3028", "物联网表主站 IP 查询"),*/
    CmdEnum_S("3041", "物联网表主动上报", "4021"),
    CmdEnum_T("3027", "物联网表主站 IP 设置", "4011"),
    CmdEnum_U("3025", "物联网表时钟设置", "401D"),
    CmdEnum_V("3024", "物联表上报周期查询", "4042"),
    CmdEnum_W("3023", "物联表上报周期设置", "4012"),
    CmdEnum_X("3053", "物联网表充值", "4013"),
    CmdEnum_Y("3051", "物联网表开阀", "4025"),
    CmdEnum_Z("3052", "物联网表关阀", "4025"),

    ERROR_CMD("9999", "错误指令", "9999"),
    ;

    private String code;

    private String qwCode;
    private String name;

    CmdEnum(String cmdEnum, String typeName, String qwCode) {
        this.code = cmdEnum;
        this.name = typeName;
        this.qwCode = qwCode;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getQwCode() {
        return qwCode;
    }

    public static CmdEnum codeOf(String code) {
        Optional<CmdEnum> op = Stream.of(CmdEnum.values()).filter(hnCmdEnum -> hnCmdEnum.code.equals(code)).findFirst();
        return op.orElse(ERROR_CMD);
    }

    public static CmdEnum QwcodeOf(String qwCode) {
        Optional<CmdEnum> op = Stream.of(CmdEnum.values()).filter(hnCmdEnum -> hnCmdEnum.qwCode.equals(qwCode)).findFirst();
        return op.orElse(ERROR_CMD);
    }

}
