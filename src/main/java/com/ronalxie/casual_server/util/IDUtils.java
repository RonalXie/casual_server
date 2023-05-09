package com.ronalxie.casual_server.util;

public class IDUtils {
    public static synchronized Long nextId(){
        return System.currentTimeMillis();
    }
}
