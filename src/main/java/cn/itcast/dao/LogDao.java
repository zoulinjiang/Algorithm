package cn.itcast.dao;

import cn.itcast.entity.Log;
import cn.itcast.entity.SysLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface LogDao extends BaseMapper<SysLogEntity> {
    @Update("truncate table sys_log")
    void removeAllLogs();
}
