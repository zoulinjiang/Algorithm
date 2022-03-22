package cn.itcast.dao;

import cn.itcast.entity.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionDao extends BaseMapper<Question> {
}
