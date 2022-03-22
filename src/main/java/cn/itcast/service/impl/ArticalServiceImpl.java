package cn.itcast.service.impl;

import cn.itcast.dao.ArticalDao;
import cn.itcast.entity.*;
import cn.itcast.service.ArticalService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticalServiceImpl implements ArticalService {
    @Autowired
    private ArticalDao articalDao;
    @Override
    public Boolean add(Artical artical, int id) {
        artical.setUser_id(id);
        QueryWrapper<Artical> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",artical.getTitle());
        Artical artical1 = articalDao.selectOne(queryWrapper);
        if (artical1 !=null){
            return false;
        }

        articalDao.insert(artical);
        return true;
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean , int id) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();

        Page<Artical> page = new Page<>(currentPage, pageSize);

        QueryWrapper<Artical> queryWrapper = new QueryWrapper<>();
        if(queryString == null){
                queryWrapper.eq("user_id",id);
        }else {
            queryWrapper.like("title",queryString).eq("user_id",id);
        }


        Page<Artical> articalPage = articalDao.selectPage(page, queryWrapper);

        return new PageResult(articalPage.getTotal(),articalPage.getRecords());

    }

    @Override
    public Artical findId(int id) {
        Artical artical = articalDao.selectById(id);
        return artical;
    }

    @Override
    public boolean edit(Artical artical , int id) {
        artical.setUser_id(id);
        articalDao.updateById(artical);
        return true;
    }

    @Override
    public Boolean deleteId(int id) {
        int i = articalDao.deleteById(id);
        return (i>0);
    }

    @Override
    public Artical ArticalfindById(int id) {
        Artical artical = articalDao.selectById(id);
        return artical;
    }
}
