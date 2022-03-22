package cn.itcast.dao;

import cn.itcast.entity.Matrix;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * (Matrix)表数据库访问层
 *
 * @author makejava
 * @since 2022-03-01 15:57:13
 */
@Mapper
public interface MatrixMapper extends BaseMapper<Matrix> {

}


