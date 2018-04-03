package com.javaweb.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.ArrayList;
import java.util.List;

public class OnlineListener implements HttpSessionAttributeListener {
    private static int count=0;
    private static List<Object> users=new ArrayList();

    public static List<Object> online(){
        return users;
    }

    public static int getCount(){
        return count;
    }

    public void attributeAdded(HttpSessionBindingEvent event) {
        if("username".equals(event.getName())) {
            System.out.println(event.getValue() + "上线了");
            count++;
            users.add(event.getValue());
        }
    }

    public void attributeRemoved(HttpSessionBindingEvent event) {
        System.out.println(event.getValue()+"下线了");
        count--;
    }
}
