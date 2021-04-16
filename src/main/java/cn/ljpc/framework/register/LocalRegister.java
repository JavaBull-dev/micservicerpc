package cn.ljpc.framework.register;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jacker
 * @Description
 * @create 2021-04-16 9:21
 *
 * 本地注册
 * 将服务的接口名和服务的实现类进行绑定
 *
 */
public class LocalRegister {

    private static Map<String, Class> map = new HashMap<>();

    public static void register(String interfaceName, Class impClass) {
        map.put(interfaceName, impClass);
    }

    public static Class get(String interfaceName) {
        return map.get(interfaceName);
    }
}
