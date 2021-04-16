package cn.ljpc.framework;


import java.util.List;
import java.util.Random;

/**
 * @author Jacker
 * @Description
 * @create 2021-04-16 11:19
 *
 * 负载均衡 算法
 *
 */
public class LoadBalance {

    public static URL random(List<URL> list) {
        return list.get(new Random().nextInt(list.size()));
    }
}
