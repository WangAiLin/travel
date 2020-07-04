package cn.wal.travel.service;

import cn.wal.travel.domain.User;

public interface UserService {
    /**
     * 注册用户
     * @param user
     * @return
     */
    boolean regist(User user);

    /**
     * 激活邮箱
     * @param code
     * @return
     */
    boolean active(String code);

    User login(User user);
}
