package cn.itcast.controller;

import cn.itcast.entity.*;
import cn.itcast.expec.DeleteErrorException;
import cn.itcast.service.LogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
public class LogController {

    @Autowired
    private LogService logService;

    @RequestMapping("/removeAllLogs")
    public Result removeAllLogs() throws IOException {
         logService.removeAllLog();

            return new Result(true, "删除所有日志成功");

    }

    @RequestMapping("/loadingLogs")
    public PageResult loadingLogs(@RequestBody QueryPageBean queryPageBean){

        Page<SysLogEntity> page = new Page<>(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());

        QueryWrapper<SysLogEntity> queryWrapper = new QueryWrapper<>();

        Page<SysLogEntity> page1 = logService.page(page, queryWrapper);
        return new PageResult(page1.getTotal(),page1.getRecords());
    }


    @RequestMapping("/removeLogs")
    public Result removeLogs(int id) throws IOException {
        boolean b = logService.removeLog(id);
        if (b) {
            return new Result(true, "删除日志成功");
        } else {
            throw new DeleteErrorException("删除日志错误");
        }
    }
}
