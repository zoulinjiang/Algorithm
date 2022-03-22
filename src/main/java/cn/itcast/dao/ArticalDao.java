package cn.itcast.dao;

import cn.itcast.entity.Artical;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticalDao extends BaseMapper<Artical> {
}
