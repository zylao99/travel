package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.Route;

import java.util.List;

public interface FavoriteDao {
    public Favorite findByRidAndUid(int rid, int uid);

    int findCountByrRid(String rid);

    void add(int rid, int uid);


}
