package cn.wal.travel.service.impl;

import cn.wal.travel.dao.UserDao;
import cn.wal.travel.dao.impl.UserDaoImpl;
import cn.wal.travel.domain.User;
import cn.wal.travel.service.UserService;
import cn.wal.travel.util.MailUtils;
import cn.wal.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();
    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public boolean regist(User user) {
        //1.根据用户名查询用户对象
        User u = userDao.findByUsername(user.getUsername());
        if(u != null){
            return false;
        }
        //设置激活码，唯一字符串
        user.setCode(UuidUtil.getUuid());
        //设置激活状态
        user.setStatus("N");
        //2.保存用户信息
        userDao.save(user);

        //激活邮件发送，邮件正文
        String content = "<a href='http://localhost:8088/travel/user/active?code="+user.getCode()+"'>点击激活旅游网</a>";
        MailUtils.sendMail(user.getEmail(),content,"激活邮件");
        return true;
    }

    /**
     * 邮箱激活
     * @param code
     * @return
     */
    @Override
    public boolean active(String code) {
        //根据激活码查询出注册的用户
        User user = userDao.findByCode(code);
        if(user != null){
            //修改激活的状态
            userDao.updateStatus(user);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public User login(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
}
