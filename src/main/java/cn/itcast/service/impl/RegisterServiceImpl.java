package cn.itcast.service.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.entity.PageResult;
import cn.itcast.entity.QueryPageBean;
import cn.itcast.entity.User;
import cn.itcast.service.RegisterService;
import cn.itcast.utils.MailUtils;
import cn.itcast.utils.UuidUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserDao userDao;
    @Override
    public Boolean register(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername());
        User user1 = userDao.selectOne(queryWrapper);
        if(user1 !=null){
            return false;
        }
        user.setCode(UuidUtil.getUuid());
        userDao.insert(user);

        String content="<a href='http://localhost:8888/user/active?code="+user.getCode()+"'>点击激活线代君网站 </a>";
        MailUtils.sendMail(user.getEmail(),content,"激活邮件");
        return true;
    }

    @Override
    public boolean active(String code) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code",code);
        User user = userDao.selectOne(queryWrapper);
        if (user !=null){
            user.setStatus("Y");
            userDao.updateById(user);
            return true;
        }else {
            return false;
        }

    }

    @Override
    public User login(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername()).eq("password",user.getPassword());
        User user1 = userDao.selectOne(queryWrapper);
        return user1;
    }

    @Override
    public PageResult findAll(QueryPageBean queryPageBean) {
        Page<User> page = new Page<>(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("id",1);
        if(queryPageBean.getQueryString() == null){

        }else {
            queryWrapper.like("username",queryPageBean.getQueryString());
        }
        Page<User> userPage = userDao.selectPage(page, queryWrapper);
        return new PageResult(userPage.getTotal(),userPage.getRecords());
    }

    @Override
    public boolean freezeUser(int id) {
        User user = new User();
        user.setId(id);
        user.setStatus("F");

        return userDao.updateById(user) >0;
    }

    @Override
    public boolean unfreezeUser(int id) {
        User user = new User();
        user.setId(id);
        user.setStatus("Y");

        return userDao.updateById(user) >0;
    }

    @Override
    public boolean deleteUser(int id) {

        return userDao.deleteById(id) >0;
    }

    @Override
    public User findUserId(int id) {
        User user = userDao.selectById(id);
        System.out.println("下册违法服气服气服气去"+user);
        return user;
    }

    @Override
    public boolean editUser(User user) {

        return userDao.updateById(user) >0;
    }

    @Override
    public boolean save(User user) {
        return userDao.insert(user) >0;
    }
}
