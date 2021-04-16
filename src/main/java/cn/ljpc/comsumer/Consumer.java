package cn.ljpc.comsumer;

import cn.ljpc.framework.ProxyFactory;
import cn.ljpc.provider.HelloService;

/**
 * @author Jacker
 * @Description
 * @create 2021-04-16 8:45
 * <p>
 * 服务的消费者
 */
public class Consumer {

    public static void main(String[] args) {
        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        String result = helloService.sayHello("说骚话");
        System.out.println(result);
    }
}