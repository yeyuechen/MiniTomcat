package com.zz.tomcat;

import java.util.ArrayList;
import java.util.List;

public class ServletMappingConfig {

    public static List<ServletMapping> servletMappingList=new ArrayList<ServletMapping>();
    static {
        servletMappingList.add(new ServletMapping("helloWorld","/world","org.example.HelloController"));
    }
}
