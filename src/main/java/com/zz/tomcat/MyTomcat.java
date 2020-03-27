package com.zz.tomcat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class MyTomcat {

    private int port = 8080;

    private Map<String, String> urlServletMap = new HashMap<String, String>();

    MyTomcat(int port) {
        this.port = port;
    }

    public void start() {
        initServletMapping();

        ServerSocket serverSocket = null;
        try{
            serverSocket=new ServerSocket(port);
            System.out.println("tomcat 启动成功。。。");
            while (true){
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                HttpRequest request = new HttpRequest(inputStream);
                HttpResponse response=new HttpResponse(outputStream);
                dispatch(request,response);
                socket.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(serverSocket!=null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void initServletMapping() {
        for (ServletMapping servletMapping :
                ServletMappingConfig.servletMappingList) {
            urlServletMap.put(servletMapping.getUrl(),servletMapping.getClazz());
        }
    }

    public void dispatch(HttpRequest request,HttpResponse response) throws ClassNotFoundException {
        String clazz =  urlServletMap.get(request.getUrl());
        try{
            if(null!=clazz && ""!=clazz) {
                Class<MyServlet> myServletClass = (Class<MyServlet>) Class.forName(clazz);
                MyServlet myServlet = myServletClass.newInstance();
                myServlet.service(request, response);
            }
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MyTomcat(8080).start();
    }
}
