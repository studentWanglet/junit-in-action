package cn.wh.study.chapter_03;

/**
 * @author 王恒
 * @className cn.wh.study.chapter_03.Control
 * @date 2024-09-26 20:33
 * @description todo
 */
public interface Control {


    Response processRequest(Request request);

    void addHandler(Request request,RequestHandler requestHandler);
}
