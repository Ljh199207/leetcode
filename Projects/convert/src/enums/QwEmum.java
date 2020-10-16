/*
package enums;

import java.util.Optional;
import java.util.stream.Stream;

public enum  QwEmum {
    OFF_VALVE("3032", "物联网表关阀"),

    ERROR_CMD("9999", "错误指令"),
            ;

    private String code;

    private String name;

    QwEmum(String cmdEnum, String typeName) {
        this.code = cmdEnum;
        this.name = typeName;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static QwEmum codeOf(String code) {
        Optional<QwEmum> op = Stream.of(QwEmum.values()).filter(hnCmdEnum -> hnCmdEnum.code.equals(code)).findFirst();
        return op.orElse(ERROR_CMD);
    }
}
*/
