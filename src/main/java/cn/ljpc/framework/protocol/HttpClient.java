package cn.ljpc.framework.protocol;

import cn.ljpc.framework.Invocation;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Jacker
 * @Description
 * @create 2021-04-16 9:36
 */
public class HttpClient {

    public String send(String hostname, Integer port, Invocation invocation) {

        // 获取用户的配置 HttpClient
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建http POST请求
        HttpPost httpPost = null;
        try {
            httpPost = new HttpPost(new URI("http", null, hostname, port, "/", null, null));
            httpPost.setEntity(new StringEntity(JSONObject.toJSONString(invocation), CharsetUtils.get("utf8")));
            CloseableHttpResponse response = null;

            try {
                // 执行请求
                response = httpclient.execute(httpPost);
                // 判断返回状态是否为200
                if (response.getStatusLine().getStatusCode() == 200) {
                    return EntityUtils.toString(response.getEntity(), "UTF-8");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (response != null) {
                    try {
                        response.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    httpclient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (URISyntaxException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

}
