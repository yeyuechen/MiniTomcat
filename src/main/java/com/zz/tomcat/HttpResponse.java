package com.zz.tomcat;

import java.io.IOException;
import java.io.OutputStream;

public class HttpResponse {

    private OutputStream outputStream;

    HttpResponse(OutputStream outputStream){
        this.outputStream=outputStream;
    }

    public void write(String content) throws IOException{
        StringBuffer httpResponse = new StringBuffer();
        httpResponse.append("HTTP/1.1 200 ok \n")
                .append("\r\n")
                .append("<html><body>")
                .append(content)
                .append("</body></html>");
        outputStream.write(httpResponse.toString().getBytes());
        outputStream.close();
    }
}
