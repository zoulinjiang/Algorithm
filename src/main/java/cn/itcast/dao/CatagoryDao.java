package cn.itcast.dao;

import cn.itcast.entity.Catagory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CatagoryDao extends BaseMapper<Catagory> {
}
