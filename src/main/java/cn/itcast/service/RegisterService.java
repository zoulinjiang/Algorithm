package cn.itcast.service;

import cn.itcast.entity.PageResult;
import cn.itcast.entity.QueryPageBean;
import cn.itcast.entity.User;

public interface RegisterService {
    public Boolean register(User user);

    boolean active(String code);

    User login(User user);

    PageResult findAll(QueryPageBean queryPageBean);

    boolean freezeUser(int id);

    boolean unfreezeUser(int id);

    boolean deleteUser(int id);

    User findUserId(int id);

    boolean editUser(User user);

    boolean save(User user);
}
