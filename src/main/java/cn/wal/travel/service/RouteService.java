package cn.wal.travel.service;

import cn.wal.travel.domain.PageBean;
import cn.wal.travel.domain.Route;

public interface RouteService {

    /**
     * 根据类别进行分页查询方法
     * @param cid
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageBean<Route> pageQuery(int cid,int currentPage,int pageSize,String rname);

    /**
     * 根据id查询
     * @param rid
     * @return
     */
    public Route findOne(String rid);
}
