package org.example;

import com.zz.tomcat.HttpRequest;
import com.zz.tomcat.HttpResponse;
import com.zz.tomcat.MyServlet;

import java.io.IOException;

public class HelloController extends MyServlet {


    public void doGet(HttpRequest request, HttpResponse response) {
        try {
            response.write("hello world get... ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpRequest request, HttpResponse response) {
        try {
            response.write("hello world post... ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
