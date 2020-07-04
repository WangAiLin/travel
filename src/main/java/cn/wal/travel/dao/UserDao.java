package cn.wal.travel.dao;

import cn.wal.travel.domain.User;

public interface UserDao {
    /**
     * 根据用户名查询用户信息
     * @return
     */
    public User findByUsername(String username);

    /**
     * 保存用户
     * @param user
     */
    public void save(User user);

    User findByCode(String code);

    void updateStatus(User user);

    User findByUsernameAndPassword(String username, String password);
}
