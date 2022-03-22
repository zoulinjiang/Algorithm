package cn.itcast.service;

import cn.itcast.config.SysLog;
import cn.itcast.entity.Log;
import cn.itcast.entity.PageResult;
import cn.itcast.entity.QueryPageBean;
import cn.itcast.entity.SysLogEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface LogService extends IService<SysLogEntity> {

    /**
     * 添加异常日志
     * @param uid 发生异常的用户id
     * @param eMsg 异常信息
     * @return 添加成功与否
     */
//    boolean addLog(int uid, String eMsg);

    /**
     * 删除异常日志
     * @param id 被删除日志的id
     * @return 删除是否成功
     */
    boolean removeLog(int id);

    void removeAllLog();

}
