package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

import java.util.List;

public interface RouteService {
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname);

    Route findOne(String rid);

    PageBean<Route> fPageQuery(int uid,int currentPage, int pageSize);

    PageBean<Route> findAll();
}
