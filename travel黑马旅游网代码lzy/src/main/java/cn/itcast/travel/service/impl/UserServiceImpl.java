package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public boolean regist(User user) {

        User u = userDao.findByUsername(user.getUsername());

        if (u != null) {

            return false;

        } else {

            user.setCode(UuidUtil.getUuid());
            user.setStatus("N");
            userDao.save(user);

            String content = "<a href='http://localhost/travel/user/active?code=" + user.getCode() + "'>点击激活【黑马旅游网】</a>";
            MailUtils.sendMail(user.getEmail(), content, "激活邮件");
            return true;

        }
    }

    //激活用户
    @Override
    public boolean active(String code) {
        //查询用户
        User user = userDao.findByCode(code);
        if (user != null) {
            userDao.updateStatus(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User login(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());

    }

    @Override
    public User eActive(User user) {
        User u = userDao.findByUsername(user.getUsername());


        String content = "<a href='http://localhost/travel/user/active?code=" + u.getCode() + "'>点击激活【黑马旅游网】</a>";
        MailUtils.sendMail(u.getEmail(), content, "激活邮件");

        return u;
    }

    }
