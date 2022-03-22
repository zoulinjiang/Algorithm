package cn.itcast.service.impl;

import cn.itcast.dao.MatrixMapper;
import cn.itcast.entity.Matrix;
import cn.itcast.service.MatrixService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * (Matrix)表服务实现类
 *
 * @author makejava
 * @since 2022-03-01 15:57:15
 */
@Service("matrixService")
public class MatrixServiceImpl extends ServiceImpl<MatrixMapper, Matrix> implements MatrixService {

}

