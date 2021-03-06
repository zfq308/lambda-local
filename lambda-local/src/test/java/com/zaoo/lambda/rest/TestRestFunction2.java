package com.zaoo.lambda.rest;

import com.zaoo.lambda.LambdaLocal;

@LambdaLocal("/testRestPath2")
public class TestRestFunction2 {
    @RestMethod(httpMethod = HttpMethod.POST, path = "/")
    public Response hello(@RestParam("firstName") String firstName,
                          @RestParam("lastName") String lastName,
                          @RestHeader("userToken") String userToken,
                          @RestBody Request request) {
        return new Response(firstName, lastName, userToken, request.addr, request.mobile);
    }

    public static class Response {
        public String firstName;
        public String lastName;
        public String userToken;
        public String addr;
        public String mobile;

        public Response() {
        }

        public Response(String firstName,
                        String lastName,
                        String userToken,
                        String addr,
                        String mobile) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.userToken = userToken;
            this.addr = addr;
            this.mobile = mobile;
        }
    }

    public static class Request {
        public String addr;
        public String mobile;
    }
}
