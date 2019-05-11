package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.mail.Flags;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.server.UID;
import java.util.List;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {

    private RouteService routeService = new RouteServiceImpl();
    private FavoriteService favoriteService = new FavoriteServiceImpl();

    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");
        String rname = request.getParameter("rname");
        rname =new String(rname.getBytes("iso-8859-1"),"utf-8");

          int cid = 0;
            if (cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)) {
                cid = Integer.parseInt(cidStr);
             }
        int currentPage = 0;
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
         }else {
            currentPage=1;
        }
           int pageSize = 0;
            if (pageSizeStr != null && pageSizeStr.length() > 0) {
                pageSize = Integer.parseInt(pageSizeStr);
             }else {
                pageSize=5;
            }
        //3. 调用service查询PageBean对象
         PageBean<Route> pb = routeService.pageQuery(cid, currentPage,pageSize,rname);


        //4. 将pageBean对象序列化为json，返回
            writeValue(pb,response);

    }
    public void fPageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        User user = (User) request.getSession().getAttribute("user");
        int uid = user.getUid();
        int currentPage = 0;
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
         }else {
            currentPage=1;
        }
           int pageSize = 0;
            if (pageSizeStr != null && pageSizeStr.length() > 0) {
                pageSize = Integer.parseInt(pageSizeStr);
             }else {
                pageSize=12;
            }
        //3. 调用service查询PageBean对象
         PageBean<Route> pb = routeService.fPageQuery(uid,currentPage,pageSize);


        //4. 将pageBean对象序列化为json，返回
            writeValue(pb,response);

    }

    public void  findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         Route route = new Route();
        String rid = request.getParameter("rid");

        //3. 调用service查询PageBean对象
           route = (Route) routeService.findOne(rid);


        //4. 将pageBean对象序列化为json，返回
            writeValue(route,response);

    }

    public void  isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if(user==null){
            uid =0 ;

        }else {
            uid = user.getUid();
        }

        boolean flag = favoriteService.isFavorite(rid, uid);
        writeValue(flag,response);

    }

    public void  addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if(user==null){
            return;

        }else {
            uid = user.getUid();
        }

         favoriteService.add(rid, uid);

    }
    public void  findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


       //. 调用service查询PageBean对象
        PageBean<Route> pb =  routeService.findAll();


        //4. 将pageBean对象序列化为json，返回
        writeValue(,response);

    }

}
