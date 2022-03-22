package cn.itcast.service.impl;

import Jama.Matrix;
import cn.itcast.dao.AlgorithmDao;
import cn.itcast.dao.CatagoryDao;
import cn.itcast.dao.MatrixMapper;
import cn.itcast.dao.UserDao;
import cn.itcast.entity.*;
import cn.itcast.service.AlgorithmService;

import cn.itcast.service.MatrixService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class AlgorithmServiceImpl implements AlgorithmService {
    @Autowired
    private AlgorithmDao algorithmDao;

    @Override
    public List<Algorithm> findAlgorithmsByAid(Integer aid) {
        if (aid!=null && aid>=0){
            LambdaQueryWrapper<Algorithm> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Algorithm::getAid,aid);
            lambdaQueryWrapper.orderByAsc(Algorithm::getId);
           return  algorithmDao.selectList(lambdaQueryWrapper);
        }else{
            return null;
        }
    }
    @Override
    public List<Algorithm> findAlgorithmsByUid(Integer uid) {
        if (uid!=null && uid>=0){
            LambdaQueryWrapper<Algorithm> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Algorithm::getUid,uid);
            lambdaQueryWrapper.orderByAsc(Algorithm::getId);
            return  algorithmDao.selectList(lambdaQueryWrapper);
        }else{
            return null;
        }
    }

    /**
     * 添加算法
     * @param algorithm 被添加的算法
     * @return 添加成功的算法数量
     */
    @Override
    public int addAlgorithm(Algorithm algorithm) {
        if (algorithm!=null)
            return algorithmDao.insert(algorithm);
        else
            return 0;
    }

    /**
     * 通过算法的id移除算法
     * @param id 被删除的算法id
     * @param PATH 被删除的算法文件所在服务器的目录
     * @param index 被删除的算法文件在session中的list集合中的角标
     * @param session session
     * @return 删除成功的算法数量
     */
    @Override
    @Transactional
    public int removeAlgorithmById(Integer id, String PATH, int index, HttpSession session) {
        if (id!=null && id>0 && PATH != null && index >=0 ){
            int num = algorithmDao.deleteById(id);
            if (num!=1)
                return 0;
            else {
                Object attribute = session.getAttribute("algorithms");
                if (attribute==null)
                    return 0;
                else {
                    List<Algorithm> algorithms = (List<Algorithm>) attribute;
                    Algorithm algorithm = algorithms.get(index);
                    String algorithmName = algorithm.getAlgorithmname();
                    File file = new File(PATH+algorithmName);
                    boolean delete = file.delete();
                    if (delete)
                        return num;
                    else
                        return 0;
                }
            }
        }
        else
            return 0;
    }

    /**
     * 将字符串转为double类型的二维数组
     * @param data 字符串数据
     * @return double类型的
     */
    @Override
    public double[][] stringToArray(String data){
        String[] dda = data.split("\n"); //double dimensional array 二维数组

        double[][] fDst = new double[dda.length][];//建立二维数组final destination的简写

        for(int i = 0; i < dda.length; i++){
            String[] subDda = dda[i].split(",");//sub Double Dimensional Array 二维数组的子数组
            fDst[i] = new double[subDda.length];//Java的二维数组可以理解为数组的集合，这里建立第0维数组的第0个子数组
            for(int j = 0; j < subDda.length; j++){
                fDst[i][j] = Double.parseDouble(subDda[j]);//api文档中说明parsedouble等价于valueof
            }
        }
        return fDst;
    }

    /**
     * 将字符串数据先转换为一个对应的二维数组之后，进行行列式计算
     * @param determinant 字符串数据
     * @return 行列式的值
     */
    @Override
    public double calculateDeterminant(String determinant) {
        double[][] dda = stringToArray(determinant);
        Matrix matrix = new Matrix(dda);
        return matrix.det();
    }

    /**
     * 矩阵加法
     * @param m1 矩阵1
     * @param m2 矩阵2
     * @return  加完之后的新矩阵
     */
    @Override
    public double[][] addMatrix(String m1, String m2) {
        double[][] ma1 = stringToArray(m1);
        double[][] ma2 = stringToArray(m2);
        Matrix matrix1 = new Matrix(ma1);
        Matrix matrix2 = new Matrix(ma2);
        Matrix matrix3 = matrix1.plus(matrix2);
        return matrix3.getArray();
    }

    /**
     * 将一个二维数组转换为字符串
     * @param arr 二维数组
     * @return 对应的字符串
     */
    @Override
    public String dArrayToString(double[][] arr) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i<arr.length; i++){
            res.append(Arrays.toString(arr[i]));
            res.append(System.getProperty("line.separator"));
        }
        return res.toString();
    }

    /**
     * 矩阵乘法
     * @param m1 矩阵1
     * @param m2 矩阵2
     * @return  乘完之后的新矩阵
     */
    @Override
    public double[][] multiplyMatrix(String m1, String m2) {
        double[][] ma1 = stringToArray(m1);
        double[][] ma2 = stringToArray(m2);
        Matrix matrix1 = new Matrix(ma1);
        Matrix matrix2 = new Matrix(ma2);
        Matrix matrix3 = matrix1.times(matrix2);
        return matrix3.getArray();
    }

    /**
     * 矩阵转置
     * @param m 被转置的矩阵
     * @return 转置完成的矩阵
     */
    @Override
    public double[][] transposeMatrix(String m) {
        double[][] ma = stringToArray(m);
        Matrix matrix = new Matrix(ma);
        Matrix matrix2 = matrix.transpose();
        return matrix2.getArray();
    }

    /**
     * 矩阵求逆
     * @param m 被求逆的矩阵
     * @return 转置求逆的矩阵
     */
    @Override
    public double[][] inverseMatrix(String m) {
        double[][] ma = stringToArray(m);
        Matrix matrix = new Matrix(ma);
        Matrix matrix2 = matrix.inverse();
        return matrix2.getArray();
    }

    /**
     * 矩阵求秩
     * @param m 被求秩的矩阵
     * @return 对应的秩
     */
    @Override
    public int rankMatrix(String m) {
        double[][] ma = stringToArray(m);
        Matrix matrix = new Matrix(ma);
        return matrix.rank();
    }

    /**
     * 矩阵求特征值
     * @param m 被求特征值的矩阵
     * @return 对应的特征值
     */
    @Override
    public double[] characterMatrix(String m) {
        double[][] ma = stringToArray(m);
        Matrix matrix = new Matrix(ma);
        return matrix.eig().getRealEigenvalues();
    }

    /**
     * 计算线性方程组
     * @param m1 矩阵1
     * @param m2 矩阵2
     * @return 结果矩阵
     */
    @Override
    public double[][] calculateEquations(String m1, String m2) {
        double[][] ma1 = stringToArray(m1);
        double[][] ma2 = stringToArray(m2);
        Matrix matrix1 = new Matrix(ma1);
        Matrix matrix2 = new Matrix(ma2);
        Matrix matrix3 = matrix1.solve(matrix2);
        return matrix3.getArray();
    }

    /**
     * 判断向量组是否线性相关
     * @param m 矩阵
     * @return 线性相关与否
     */
    @Override
    public boolean correlateVectors(String m) {
        double[][] ma = stringToArray(m);
        Matrix matrix = new Matrix(ma);
        if(matrix.rank()<matrix.getColumnDimension())
            return true;
        else
            return false;
    }

    /**
     * 判断两个矩阵是否相似
     * @param m1 矩阵1
     * @param m2 矩阵2
     * @return 相似与否
     */
    @Override
    public boolean similarMatrix(String m1, String m2) {
        double[][] ma1 = stringToArray(m1);
        double[][] ma2 = stringToArray(m2);
        Matrix matrix1 = new Matrix(ma1);
        Matrix matrix2 = new Matrix(ma2);
        boolean character = Arrays.equals(characterMatrix(m1),characterMatrix(m2));
        return checkSquareMatrix(matrix1) && checkSquareMatrix(matrix2) && character && matrix1.rank() == matrix2.rank() && matrix1.trace() == matrix2.trace() && matrix1.det() == matrix2.det();
    }

    @Override
    public boolean checkSquareMatrix(Matrix matrix){
        return matrix.getColumnDimension() == matrix.getRowDimension();
    }



    @Override
    public Boolean findAlgorithms(String title) {
        LambdaQueryWrapper<Algorithm> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Algorithm::getTitle,title);
        Algorithm algorithm = algorithmDao.selectOne(lambdaQueryWrapper);

        return Objects.isNull(algorithm);
    }
    @Autowired
    private UserDao userDao;

    @Override
    public void insertAlgorithm(int aid, int uid, String originalFilename,String title, String context1) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getId,uid);
        User user = userDao.selectOne(lambdaQueryWrapper);
        Algorithm algorithm = new Algorithm(aid, uid, originalFilename,title,context1,user.getUsername());
        algorithmDao.insert(algorithm);
    }

    @Override
    public Algorithm findById(int id) {
        Algorithm algorithm = algorithmDao.selectById(id);
        return algorithm;
    }

    @Override
    public boolean update(Algorithm algorithm) {

        return algorithmDao.updateById(algorithm) ==1;
    }

    @Override
    public boolean delete(int id) {

        return algorithmDao.deleteById(id) ==1;
    }
    @Autowired
    private MatrixMapper matrixMapper;
    @Override
    public List<cn.itcast.entity.Matrix> load(int uid) {
        LambdaQueryWrapper<cn.itcast.entity.Matrix> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(cn.itcast.entity.Matrix::getUid,uid);

        return  matrixMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public List<cn.itcast.entity.Matrix> loadByAid(int aid) {
        LambdaQueryWrapper<cn.itcast.entity.Matrix> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(cn.itcast.entity.Matrix::getAid,aid);

        return  matrixMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public boolean deleteByid(int id) {

        return matrixMapper.deleteById(id)>0;
    }

    @Override
    public PageResult findAll(QueryPageBean queryPageBean) {
        Page<Algorithm> page = new Page<>(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());

        QueryWrapper<Algorithm> queryWrapper = new QueryWrapper<>();
        if(queryPageBean.getQueryString() == null){

        }else {
            queryWrapper.like("algorithmName",queryPageBean.getQueryString());
        }
        Page<Algorithm> userPage = algorithmDao.selectPage(page, queryWrapper);
        return new PageResult(userPage.getTotal(),userPage.getRecords());
    }
}
