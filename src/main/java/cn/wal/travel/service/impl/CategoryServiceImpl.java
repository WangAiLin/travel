package cn.wal.travel.service.impl;

import cn.wal.travel.dao.CategoryDao;
import cn.wal.travel.dao.impl.CategoryDaoImpl;
import cn.wal.travel.domain.Category;
import cn.wal.travel.service.CategoryService;
import cn.wal.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {
        //1.从redis中查询
        //1.1获取jedis客户端
        Jedis jedis = JedisUtil.getJedis();
        //1.2使用sortedset排序查询
        //Set<String> categorys = jedis.zrange("category", 0, -1);
        //查询sortedset中的分数(cid)和值(cname)
        Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);
        List<Category> cs;
        //2.判断查询的集合是否为空
        if(categorys == null || categorys.size() == 0){
            //3.如果为空，需要从数据库查询，在将数据存入redis
            cs = categoryDao.findAll();
            for (int i = 0; i < cs.size(); i++) {
                //cs.get(i).getCid() 排序的准则
                jedis.zadd("category",cs.get(i).getCid(),cs.get(i).getCname());
            }
        }else{
            //如果不为空,将set的数据存入list
            cs = new ArrayList<Category>();
            for(Tuple tuple :categorys){
                Category category = new Category();
                category.setCname(tuple.getElement());
                category.setCid((int)tuple.getScore());
                cs.add(category);
            }
        }

        return cs;
    }
}
