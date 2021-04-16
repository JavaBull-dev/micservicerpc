package cn.ljpc.provider;

/**
 * @author Jacker
 * @Description
 * @create 2021-04-16 8:46
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "hello:" + name;
    }
}
