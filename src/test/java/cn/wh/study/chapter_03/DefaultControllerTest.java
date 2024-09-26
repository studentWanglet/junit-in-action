package cn.wh.study.chapter_03;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultControllerTest {

    private DefaultController defaultController;
    private SampleRequest request;
    private SampleHandler handler;

    @Before
    public void instantiate() throws Exception {
        defaultController = new DefaultController();
        request = new SampleRequest();
        handler = new SampleHandler();
    }

//    @Test
//    public void testMethod(){
//        throw new RuntimeException("implement me");
//    }

    @Test
    public void testAddHandler() throws Exception {
//        SampleRequest request = new SampleRequest();
//        SampleHandler handler = new SampleHandler();
        defaultController.addHandler(request, handler);
        RequestHandler handler1 = defaultController.getHandler(request);
        assertSame(handler, handler1);
    }

    @Test
    public void testProcessRequest() throws Exception {
//        SampleRequest request = new SampleRequest();
//        SampleHandler handler = new SampleHandler();
        defaultController.addHandler(request, handler);
        Response response = defaultController.processRequest(request);
        assertNotNull("must not return a null response", response);
        assertEquals("Response should be of type SampleResponse",
                new SampleResponse(), response
        );
    }

    @Test
    public void testProcessRequestWithException() throws Exception {
        SampleRequest request1 = new SampleRequest("throwException");
        defaultController.addHandler(request1,new SampleExceptionHandler());
        Response response = defaultController.processRequest(request1);
        assertNotNull("must not return a null response", response);
        assertEquals("Response should be of type SampleResponse",
                ErrorResponse.class,response.getClass()
                );
    }

    @Test(expected = RuntimeException.class)
    public void testGetHandlerNotDefined(){
        SampleRequest request = new SampleRequest("notDefined");
        defaultController.getHandler(request);
    }

    @Test(expected = RuntimeException.class)
    public void testGetRequestDuplicateName(){
        SampleRequest request = new SampleRequest("duplicate");
        defaultController.addHandler(request,new SampleHandler());
        defaultController.addHandler(request,new SampleHandler());
    }

    @Test(timeout = 130)
    @Ignore(value = "ignore for now until we decide a decent time-limit")
    public void testProcessRequestTimeout() throws Exception {
        Request request;
        Response response;
        RequestHandler handler = new SampleHandler();

        for (int i = 0; i < 999_999; i++) {
            request = new SampleRequest(String.valueOf(i));
            defaultController.addHandler(request,handler);
            response = defaultController.processRequest(request);
            assertNotNull(response);
            assertNotSame(ErrorResponse.class,response.getClass());
        }
    }

    private class SampleRequest implements Request {

        private static final String DEFAULT_NAME = "Test";

        private String name;

        public SampleRequest() {
            this(DEFAULT_NAME);
        }

        public SampleRequest(String name) {
            this.name = name;
        }
        @Override
        public String getName() {
            return this.name;
        }
    }

    private class SampleResponse implements Response {
        private static final String NAME = "TEST";

        public String getName() {
            return NAME;
        }

        @Override
        public int hashCode() {
            return NAME.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            boolean result = false;
            if (obj instanceof SampleResponse) {
                result = ((SampleResponse) obj).getName().equals(getName());
            }
            return result;
        }
    }

    private class SampleHandler implements RequestHandler {
        @Override
        public Response process(Request request) throws Exception {
            return new SampleResponse();
        }
    }

    private class SampleExceptionHandler implements RequestHandler {

        @Override
        public Response process(Request request) throws Exception {
            throw new Exception("error processing request");
        }
    }
}