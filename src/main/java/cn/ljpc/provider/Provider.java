package cn.ljpc.provider;

import cn.ljpc.framework.URL;
import cn.ljpc.framework.protocol.HttpServer;
import cn.ljpc.framework.register.LocalRegister;
import cn.ljpc.framework.register.RemoteRegister;

/**
 * @author Jacker
 * @Description
 * @create 2021-04-16 8:44
 * 服务的提供者
 */
public class Provider {

    public static void main(String[] args) {

        //本地注册 ：将接口名和实现类进行绑定
        LocalRegister.register(HelloService.class.getName(), HelloServiceImpl.class);

        //动态获取ip地址和用户设置的端口号
        URL url = new URL("localhost", 9999);

        // 注册中心注册
        RemoteRegister.register(HelloService.class.getName(), url);

        // 接受请求 netty tomcat jetty
        // 使用http协议 传输
        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostname(), url.getPort());
    }
}
