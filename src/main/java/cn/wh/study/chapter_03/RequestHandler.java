package cn.wh.study.chapter_03;

/**
 * @author 王恒
 * @className cn.wh.study.chapter_03.RequestHandler
 * @date 2024-09-26 20:32
 * @description 请求handler
 */
public interface RequestHandler {

    /**
     * 执行
     * @param request
     * @return
     * @throws Exception
     */
    Response process(Request request) throws Exception;
}
