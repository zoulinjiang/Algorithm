package cn.itcast.service;

import cn.itcast.entity.Artical;
import cn.itcast.entity.PageResult;
import cn.itcast.entity.QueryPageBean;
import cn.itcast.entity.Result;

import java.util.List;

public interface ArticalService {
    public Boolean add(Artical artical , int id);
    public PageResult findPage(QueryPageBean queryPageBean , int id);

    Artical findId(int id);

    boolean edit(Artical artical ,int id);

    Boolean deleteId(int id);


    Artical ArticalfindById(int id);
}
