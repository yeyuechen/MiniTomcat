package com.zz.tomcat;

public abstract class MyServlet {

    /**
     * GIT请求
     * @param request
     * @param response
     */
    public abstract void doGet(HttpRequest request,HttpResponse response);

    /**
     * POST请求
     * @param request
     * @param response
     */
    public abstract void doPost(HttpRequest request,HttpResponse response);

    public void service(HttpRequest request,HttpResponse response){
        if(request.getMethod().equalsIgnoreCase("POST")){
            doPost(request,response);
        }else if(request.getMethod().equalsIgnoreCase("GET")){
            doGet(request,response);
        }
    }
}
