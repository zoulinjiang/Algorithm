package cn.itcast.service.impl;

import cn.itcast.dao.LogDao;
import cn.itcast.dao.SysLogDao;
import cn.itcast.entity.*;
import cn.itcast.service.LogService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LogServiceImpl extends ServiceImpl<LogDao, SysLogEntity>  implements LogService {

    @Autowired
    private LogDao logDao;

//    /**
//     * 添加异常日志
//     * @param uid 发生异常的用户id
//     * @param eMsg 异常信息
//     * @return 添加成功与否
//     */
//    @Override
//    public boolean addLog(int uid, String eMsg) {
//        if (eMsg!=null && uid>0){
//            Log log = new Log(new Date(), uid, eMsg);
//            int num = logDao.insert(log);
//            if (num==1)
//                return true;
//        }
//        return false;
//    }

    /**
     * 删除异常对象
     * @param id 被删除日志的id
     * @return 删除是否成功
     */
    @Override
    public boolean removeLog(int id) {
        int num = logDao.deleteById(id);
        if (num!=1)
            return false;
        else
            return true;
    }

    @Override
    public void removeAllLog() {
        logDao.removeAllLogs();
    }

}
