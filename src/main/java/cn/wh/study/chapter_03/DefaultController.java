package cn.wh.study.chapter_03;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 王恒
 * @className cn.wh.study.chapter_03.DefaultController
 * @date 2024-09-26 21:03
 * @description todo
 */
public class DefaultController implements Control{

    private Map<String,RequestHandler> requestHandlers = new HashMap<>();

    protected RequestHandler getHandler(Request request){
        if (!requestHandlers.containsKey(request.getName())){
            String message = MessageFormat.format("Cannot find handler for request {0}",request.getName());
            throw new RuntimeException(message);
        }
        return requestHandlers.get(request.getName());
    }

    @Override
    public Response processRequest(Request request) {
        Response response = null;
        try {
            response = getHandler(request).process(request);
        } catch (Exception e) {
            response =  new ErrorResponse(request,e);
        }
        return response;
    }

    @Override
    public void addHandler(Request request, RequestHandler requestHandler) {
        if (this.requestHandlers.containsKey(request.getName())){
            throw new RuntimeException("Handler already exists for request "+request.getName());
        }
        this.requestHandlers.put(request.getName(),requestHandler);
    }
}
