package cn.itcast.dao;

import cn.itcast.entity.Answer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AnswerDao extends BaseMapper<Answer> {
}
