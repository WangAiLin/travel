package cn.wal.travel.dao;

import cn.wal.travel.domain.RouteImg;

import java.util.List;

public interface RouteImgDao {

    /**
     * 根据route的rid查询图片
     * @param rid
     * @return
     */
    public List<RouteImg> findByRid(int rid);
}
