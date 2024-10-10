package cn.wh.study.chapter_06;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mortbay.jetty.HttpHeaders;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.AbstractHandler;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.util.ByteArrayISO8859Writer;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;

public class WebClientTest {

    @BeforeClass
    public static void setUp() throws Exception {
        Server server = new Server(8080);
        WebClientTest t = new WebClientTest();
        Context context = new Context(server, "/testGetContentOk");
        context.setHandler(t.new TestGetContentOkHandler());
        Context contextNotFound = new Context(server,"/testGetContentNotFound");
        contextNotFound.setHandler(t.new TestGetContentNotFoundHandler());
        server.setStopAtShutdown(true);
        server.start();
    }

    /**
     * 配合代码中的JettySample进行的测试
     * @throws MalformedURLException
     */
    @Test
    public void getContent() throws MalformedURLException {
        WebClient client = new WebClient();
        URL url = new URL("http://localhost:8080/src/main/java/cn/wh/study/chapter_06/demo.txt");
        String content = client.getContent(url);
        Assert.assertEquals("it works!",content);
    }

    @Test
    public void getContentOk() throws MalformedURLException {
        WebClient client = new WebClient();
        URL url = new URL("http://localhost:8080/testGetContentOk");
        String content = client.getContent(url);
        Assert.assertEquals("it also works!",content);
        String res = client.getContent(new URL("http://localhost:8080/testGetContentNotFound"));
        Assert.assertNull(res);
    }

    public class TestGetContentOkHandler extends AbstractHandler {
        @Override
        public void handle(String target, HttpServletRequest request,
                           HttpServletResponse response, int dispatch) throws IOException, ServletException {
            ServletOutputStream out = response.getOutputStream();
            ByteArrayISO8859Writer writer = new ByteArrayISO8859Writer();
            writer.write("it also works!");
            writer.flush();
            response.setIntHeader(HttpHeaders.CONTENT_LENGTH,writer.size());
            writer.writeTo(out);
            out.flush();
        }
    }

    public class TestGetContentNotFoundHandler extends AbstractHandler {
        @Override
        public void handle(String target, HttpServletRequest request,
                           HttpServletResponse response, int dispatch) throws IOException, ServletException {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}