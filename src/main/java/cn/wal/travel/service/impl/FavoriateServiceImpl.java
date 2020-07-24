package cn.wal.travel.service.impl;

import cn.wal.travel.dao.FavoriateDao;
import cn.wal.travel.dao.impl.FavoriateDaoImpl;
import cn.wal.travel.domain.Favorite;
import cn.wal.travel.service.FavoriateService;

public class FavoriateServiceImpl implements FavoriateService {

    private FavoriateDao favoriateDao = new FavoriateDaoImpl();

    @Override
    public boolean isFavoriate(String rid, int uid) {
        Favorite favorite = favoriateDao.findByRidAndUid(Integer.parseInt(rid), uid);
        return favorite != null;//有值为true，没值为false.
    }

    @Override
    public void add(String rid, int uid) {
        favoriateDao.add(Integer.parseInt(rid),uid);
    }
}
