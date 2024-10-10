package cn.wh.study.chapter_06;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author 王恒
 * @className cn.wh.study.chapter_06.WebClient
 * @date 2024-10-10 21:07
 * @description 一个简单的HTTP连接示例。假设此类是一个大项目的一个部分。
 * 此类中连接了外部的接口，对应测试类去演示怎么测试这种连接外部接口的方法。
 */
public class WebClient {

    /**
     * 连接URL，获取对应的内容
     * @param url
     * @return
     */
    public String getContent(URL url){
        StringBuffer content = new StringBuffer();
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            InputStream is = connection.getInputStream();
            byte[] buffer = new byte[1024];
            int count;
            while (-1 != (count = is.read(buffer))){
                content.append(new String(buffer, 0, count));
            }
        } catch (IOException e) {
            return null;
        }
        return content.toString();
    }
}
