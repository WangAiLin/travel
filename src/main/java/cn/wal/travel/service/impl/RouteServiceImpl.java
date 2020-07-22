package cn.wal.travel.service.impl;

import cn.wal.travel.dao.RouteDao;
import cn.wal.travel.dao.impl.RouteDaoImpl;
import cn.wal.travel.domain.PageBean;
import cn.wal.travel.domain.Route;
import cn.wal.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {

    private RouteDao routeDao = new RouteDaoImpl();

    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize) {
        PageBean<Route> pb = new PageBean<Route>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);

        int totalCount = routeDao.findTotalCount(cid);
        pb.setTotalCount(totalCount);

        //计算开始的记录数
        int start=(currentPage-1) * pageSize;
        List<Route> list = routeDao.findByPage(cid,start,pageSize);
        pb.setList(list);

        //设置总页数 = 总记录数/每页显示条数
        int totalPage = totalCount % pageSize == 0? totalCount/pageSize:(totalCount/pageSize)+1;
        pb.setTotalPage(totalPage);
        return  pb;
    }
}
