package club.javafamily.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetAddress;

/**
 * @author Jack Li
 * @date 2021/12/17 5:20 下午
 * @description
 */
@Slf4j
public class NetworkPingUtils {

    public static boolean ping(String ipAddress, Integer timeout) {
        try {
            //  当返回值是true时，说明host是可用的，false则不可。
            return InetAddress.getByName(ipAddress).isReachable(timeout);
        } catch (IOException e) {
            log.error("网络不通. IP: {}, time: {}", ipAddress, timeout);
        }

        return false;
    }

}
