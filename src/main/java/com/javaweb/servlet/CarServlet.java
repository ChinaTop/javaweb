package com.javaweb.servlet;

import com.javaweb.dao.CarDao;
import com.javaweb.dao.ContactDao;
import com.javaweb.dao.DBUtil;
import com.javaweb.pojo.Car;
import com.javaweb.pojo.Contact;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CarServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        // action 实现控制器的流程控制  CarServlet  | CarServlet?action=save
        String action = request.getParameter("action");
        action = (action == null) ? "list" : action; // 默认查询所有
        CarDao dao = new CarDao();

        if ("list".equals(action)) {
            // 条件查询
            String name = request.getParameter("name");
            String price = request.getParameter("price");
            price = (price == null || "".equals(price)) ? "0" : price;

            String spagesize=request.getParameter("pagesize");
            spagesize=(spagesize==null||"".equals(spagesize))?"5":spagesize;
            Integer pagesize=Integer.parseInt(spagesize);

            String spageno=request.getParameter("pageno");
            spageno=(spageno==null||"".equals(spageno))?"1":spageno;
            Integer pageno=Integer.parseInt(spageno);
            pageno=(pageno==0)?1:pageno;//起始页
            Integer maxPage=dao.getMaxPage(name, Double.valueOf(price),pagesize);
            pageno=(pageno>maxPage)?maxPage:pageno;//结束页
            Integer page=(pageno-1)*pagesize;




            String sort=request.getParameter("sort");
            sort=(sort==null||"".equals(sort))?"id":sort;
            String order=request.getParameter("order");
            sort=(order==null||"".equals(order))?"asc":order;
            List<Car> cars = dao.find(page,pagesize,sort,order,name, Double.parseDouble(price));

            request.setAttribute("cars", cars);
            request.getRequestDispatcher("carlist.jsp").forward(request, response);
        } else if ("findById".equals(action)) {
            // 根据 id 查询
            String sid = request.getParameter("id");
            sid = (sid == null || "".equals(sid)) ? "0" : sid;
            Integer id = Integer.parseInt(sid);
            Car car=dao.find(id);
            request.setAttribute("car",car);
            request.getRequestDispatcher("caredit.jsp").forward(request,response);
        } else if ("save".equals(action)) {
            // 新增  | 修改
            String sid = request.getParameter("id");
            sid = (sid == null || "".equals(sid)) ? "0" : sid;
            Integer id = Integer.parseInt(sid);

            String name = request.getParameter("name");
            String sprice = request.getParameter("price");
            sprice = (sprice == null || "".equals(sprice)) ? "0" : sprice;
            Double price = Double.parseDouble(sprice);

            Date createDate = new Date();
            String screateDate = request.getParameter("createDate");
            if (screateDate != null && !"".equals(screateDate)) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    createDate = sdf.parse(screateDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            //封装数据到car对象
            Car car = new Car();
            car.setId(id);
            car.setName(name);
            car.setPrice(price);
            car.setCreateDate(createDate);
            //判断后访问dao，id>0则修改，否则新增
            int count = 0;
            if (id > 0) {
                count = dao.modify(car);
            } else {
                count = dao.add(car);
            }
            //重定向到查询
            if (count > 0) {
                out.println("保存成功");
            } else {
                out.println("保存失败");
            }
            response.setHeader("refresh", "2;url=CarServlet");
        }else if("remove".equals(action)){
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
            response.setHeader("refresh", "2;url=CarServlet");
        }

        out.flush();
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
