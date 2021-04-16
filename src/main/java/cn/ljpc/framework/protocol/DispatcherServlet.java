package cn.ljpc.framework.protocol;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Jacker
 * @Description
 * @create 2021-04-16 9:01
 * <p>
 * <p>
 * 接受请求的类
 */
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //分层处理：
        new HttpServerHandler().handler(req, resp);
    }
}
