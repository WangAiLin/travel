package cn.wal.travel.dao.impl;

import cn.wal.travel.dao.SellerDao;
import cn.wal.travel.domain.Seller;
import cn.wal.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class SellerDaoImpl implements SellerDao{
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Seller findById(int id) {
        String sql = "select * from tab_seller where sid = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Seller>(Seller.class),id);
    }
}
