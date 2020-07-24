package cn.wal.travel.service;

public interface FavoriateService {
    /**
     * 判断是否收藏
     */
    public boolean isFavoriate(String rid,int uid);

    /**
     * 添加收藏
     * @param rid
     * @param uid
     */
    void add(String rid, int uid);
}
