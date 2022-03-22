/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package cn.itcast.service.impl;

import cn.itcast.dao.SysLogDao;

import cn.itcast.entity.SysLogEntity;
import cn.itcast.service.SysLogService;
import cn.itcast.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("sysLogService")
public class SysLogServiceImpl extends ServiceImpl<SysLogDao, SysLogEntity> implements SysLogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

//        IPage<SysLogEntity> page = this.page(
//
//            new Query<SysLogEntity,String,String>().getPage(params),
//            new QueryWrapper<SysLogEntity>().like(StringUtils.isNotBlank(key),"username", key)
//        );

//        return new PageUtils(page);
        return null;
    }
}
