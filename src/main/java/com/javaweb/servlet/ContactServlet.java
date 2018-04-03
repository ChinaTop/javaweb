package com.javaweb.servlet;

import com.javaweb.dao.CarDao;
import com.javaweb.dao.ContactDao;
import com.javaweb.pojo.Car;
import com.javaweb.pojo.Contact;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ContactServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        // action 实现控制器的流程控制  CarServlet  | CarServlet?action=save
        String action = request.getParameter("action");
        action = (action == null) ? "list" : action; // 默认查询所有
        ContactDao dao = new ContactDao();

        if ("list".equals(action)) {
            String name = request.getParameter("name");
            String mobil = request.getParameter("mobil");
            List<Contact> contacts = dao.find(name,mobil);
            request.setAttribute("contacts", contacts);
            request.getRequestDispatcher("contactlist.jsp").forward(request, response);
        } else if ("findById".equals(action)) {
            // 根据 id 查询
            String sid = request.getParameter("id");
            sid = (sid == null || "".equals(sid)) ? "0" : sid;
            Integer id = Integer.parseInt(sid);

            Contact contact=dao.find(id);
            request.setAttribute("contact",contact);
            request.getRequestDispatcher("contactedit.jsp").forward(request,response);

        } else if ("save".equals(action)) {
            // 新增  | 修改
            String sid = request.getParameter("id");
            sid = (sid == null || "".equals(sid)) ? "0" : sid;
            Integer id = Integer.parseInt(sid);

            String name = request.getParameter("name");
            String mobil= request.getParameter("mobil");
            String org= request.getParameter("org");
            String phone= request.getParameter("phone");
            String email= request.getParameter("email");
            String address= request.getParameter("address");

            Contact contact=new Contact();
            contact.setId(id);
            contact.setName(name);
            contact.setMobil(mobil);
            contact.setOrg(org);
            contact.setPhone(phone);
            contact.setEmail(email);
            contact.setAddress(address);

            int count = 0;
            if (id > 0) {
                count = dao.modify(contact);
            } else {
                count = dao.add(contact);
            }
            //重定向到查询
            if (count > 0) {
                out.println("保存成功");
            } else {
                out.println("保存失败");
            }
            response.setHeader("refresh", "2;url=ContactServlet");

        } else if ("remove".equals(action)) {
            // 删除
            String sid = request.getParameter("id");
            sid = (sid == null || "".equals(sid)) ? "0" : sid;
            Integer id = Integer.parseInt(sid);
            int count=dao.remove(id);
            if (count > 0) {
                out.println("删除成功");
            } else {
                out.println("删除失败");
            }
            response.setHeader("refresh", "2;url=ContactServlet");
        }

        out.flush();
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
