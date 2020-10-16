package code;

import entity.MeterMessage;
import entity.ServerMessage;


public interface Decoder {
    //请求信息
    byte[] decoder(MeterMessage message);

    //响应信息
    byte[] encoder(ServerMessage message);
}
