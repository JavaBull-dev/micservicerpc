package cn.ljpc.framework;

import cn.ljpc.framework.protocol.HttpClient;
import cn.ljpc.framework.register.RemoteRegister;
import cn.ljpc.provider.HelloService;

import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @author Jacker
 * @Description
 * @create 2021-04-16 10:05
 */
public class ProxyFactory {

    /**
     * <T> T 泛型的支持
     */
    public static <T> T getProxy(Class interfaceName) {
        Object ret = Proxy.newProxyInstance(ProxyFactory.class.getClassLoader(),
                //InvokeHandler
                new Class[]{interfaceName}, (proxy, method, args) -> {
                    HttpClient httpClient = new HttpClient();
                    Invocation invocation = new Invocation(interfaceName.getName(),
                            method.getName(), args, method.getParameterTypes());

                    /**
                     * 1.首先从缓存中获取，获取不到时，从注册中心获取，从而提高性能
                     *
                     * 存在的问题：
                     *  当注册中心更新服务时，由于本地存在缓存，客户端直接从本地获取服务，所以就不会中从服务中心获取；导致不一致
                     *
                     *  使用 zookeeper 或者redis解决
                     *  zookeeper：
                     *   watcher
                     *
                     *   redis：
                     *    发布-订阅
                     *
                     *  当服务中心的服务更新时，通知客户端
                     */

                    /**
                     * 从注册中心获取urls
                     */
                    List<URL> urls = RemoteRegister.get(interfaceName);

                    /**
                     * 负载均衡
                     * 用来选择一个存在指定服务的服务器
                     */
                    URL url = LoadBalance.random(urls);

                    //返回结果
                    return httpClient.send(url.getHostname(), url.getPort(), invocation);
                });

        return (T) ret;
    }
}
