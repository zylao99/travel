package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

public interface RouteDao {
    public int findTotalCount(int cid, String rname);

    public List<Route> findByPage(int cid, int start, int pageSize, String rname);

         Route findOne(int rid);

    int findTotalCount(int uid);

    List<Route> findByPage(int uid,int start, int pageSize);

    List<Route> findAll();
}
