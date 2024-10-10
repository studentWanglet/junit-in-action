package cn.wh.study.chapter_06;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.ResourceHandler;
import org.mortbay.jetty.servlet.Context;

/**
 * @author 王恒
 * @className cn.wh.study.chapter_06.JettySample
 * @date 2024-10-10 21:16
 * @description java中启用Jetty，并定义一个根目录(/)来启动文件
 */
public class JettySample {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        Context root = new Context(server, "/");
        root.setResourceBase("./");
        root.setHandler(new ResourceHandler());
        server.start();
    }
}
