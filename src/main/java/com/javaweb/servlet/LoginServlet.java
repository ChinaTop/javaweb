package com.javaweb.servlet;

import com.google.gson.JsonObject;
import com.google.gson.JsonSerializer;
import com.javaweb.dao.CarDao;
import com.javaweb.pojo.Car;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsDateJsonValueProcessor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession();

            String username=request.getParameter("username");
            String password=request.getParameter("password");
            CarDao cardao=new CarDao();
            Car user= cardao.login(username,password);
            JSONObject jo=new JSONObject();
            if(user!=null){
                JsonConfig jsonConfig=new JsonConfig();
                jsonConfig.registerJsonValueProcessor(Date.class,new JsDateJsonValueProcessor());
                jo= (JSONObject) JSONSerializer.toJSON(user,jsonConfig);
            }else{
                jo.put("code",400);
                jo.put("message","错误的用户名或密码");
            }
            out.println(jo.toString());
            out.flush();
            out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
