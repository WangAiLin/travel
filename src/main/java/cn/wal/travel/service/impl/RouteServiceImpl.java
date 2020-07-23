package cn.wal.travel.service.impl;

import cn.wal.travel.dao.RouteDao;
import cn.wal.travel.dao.RouteImgDao;
import cn.wal.travel.dao.SellerDao;
import cn.wal.travel.dao.impl.RouteDaoImpl;
import cn.wal.travel.dao.impl.RouteImgDaoImpl;
import cn.wal.travel.dao.impl.SellerDaoImpl;
import cn.wal.travel.domain.PageBean;
import cn.wal.travel.domain.Route;
import cn.wal.travel.domain.RouteImg;
import cn.wal.travel.domain.Seller;
import cn.wal.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {

    private RouteDao routeDao = new RouteDaoImpl();
    private RouteImgDao routeImgDao = new RouteImgDaoImpl();
    private SellerDao sellerDao = new SellerDaoImpl();
    
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {
        PageBean<Route> pb = new PageBean<Route>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);

        int totalCount = routeDao.findTotalCount(cid,rname);
        pb.setTotalCount(totalCount);

        //计算开始的记录数
        int start=(currentPage-1) * pageSize;
        List<Route> list = routeDao.findByPage(cid,start,pageSize,rname);
        pb.setList(list);

        //设置总页数 = 总记录数/每页显示条数
        int totalPage = totalCount % pageSize == 0? totalCount/pageSize:(totalCount/pageSize)+1;
        pb.setTotalPage(totalPage);
        return  pb;
    }

    @Override
    public Route findOne(String rid) {
        //根据id去route表中查询route对象
        Route route = routeDao.findOne(Integer.parseInt(rid));
        //根据route的id查询图片的集合
        List<RouteImg> routeImgList = routeImgDao.findByRid(route.getRid());
        route.setRouteImgList(routeImgList);
        //根据route的sid查询商家对象
        Seller seller = sellerDao.findById(route.getSid());
        route.setSeller(seller);
        return route;
    }
}
