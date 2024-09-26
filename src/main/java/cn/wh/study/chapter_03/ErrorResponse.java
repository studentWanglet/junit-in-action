package cn.wh.study.chapter_03;

/**
 * @author 王恒
 * @className cn.wh.study.chapter_03.ErrorResponse
 * @date 2024-09-26 21:09
 * @description todo
 */
public class ErrorResponse implements Response{
    private Request originalRequest;

    private Exception originalException;

    public ErrorResponse(Request originalRequest, Exception originalException) {
        this.originalRequest = originalRequest;
        this.originalException = originalException;
    }

    public Request getOriginalRequest() {
        return originalRequest;
    }

    public Exception getOriginalException() {
        return originalException;
    }
}
