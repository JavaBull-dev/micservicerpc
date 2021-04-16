package cn.ljpc.framework.protocol;

import cn.ljpc.framework.Invocation;
import cn.ljpc.framework.register.LocalRegister;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Jacker
 * @Description
 * @create 2021-04-16 9:03
 */
public class HttpServerHandler {

    public void handler(HttpServletRequest req, HttpServletResponse resp) {

        // 处理请求逻辑
        try {
            //json反序列化
            Invocation object = JSONObject.parseObject(req.getInputStream(), Invocation.class);

            //从本地注册中获取Class
            Class clazz = LocalRegister.get(object.getInterfaceName());

            //反射
            Method method = clazz.getMethod(object.getMethodName(), object.getParamType());
            String result = (String) method.invoke(clazz.newInstance(), object.getParams());

            // 将结果返回
            IOUtils.write(result, resp.getOutputStream());
        } catch (IOException | NoSuchMethodException | IllegalAccessException
                | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
