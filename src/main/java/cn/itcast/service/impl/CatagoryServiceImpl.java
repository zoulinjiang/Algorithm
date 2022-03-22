package cn.itcast.service.impl;

import cn.itcast.dao.CatagoryDao;
import cn.itcast.entity.Catagory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatagoryServiceImpl {
    @Autowired
    private CatagoryDao catagoryDao;

    public String findName(int id){
        Catagory catagory = catagoryDao.selectById(id);
        return catagory.getName();
    }
}
