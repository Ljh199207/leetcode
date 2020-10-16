package utils;

public class DataUtil {

    public static Integer convertDirection(Integer direction) {
        Integer dir = 0;
        switch (direction) {
            //1：主站发送任务指令到从站
            case 1:
                dir = 0;
                break;
            // 3：从站主动反馈任务指令执行信息
            case 3:
                dir = 1;
                break;
            default:
                break;
        }
        return dir;
    }
}
