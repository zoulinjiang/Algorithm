package cn.itcast.service;

import Jama.Matrix;
import cn.itcast.entity.Algorithm;
import cn.itcast.entity.PageResult;
import cn.itcast.entity.QueryPageBean;


import javax.servlet.http.HttpSession;
import java.util.List;

public interface AlgorithmService {

    /**
     * 通过章节id查询算法内容
     * @param aid 章节id
     * @return 该章节的所有算法内容
     */
    List<Algorithm> findAlgorithmsByAid(Integer aid);

    /**
     * 通过用户id查询算法内容
     * @param uid 用户id
     * @return 该用户的所有算法
     */
    List<Algorithm> findAlgorithmsByUid(Integer uid);

    /**
     * 添加算法
     * @param algorithm 被添加的算法
     * @return 添加成功的个数
     */
    int addAlgorithm(Algorithm algorithm);

    /**
     * 通过id删除算法
     * @param id 被删除的算法id
     * @param PATH 算法文件所在服务器的目录
     * @param index 该算法文件在session中的list集合的角标
     * @param session session
     * @return 删除成功的算法
     */
    int removeAlgorithmById(Integer id, String PATH, int index , HttpSession session);

    /**
     * 将字符串数据转化为二维数组
     * @param data 字符串数据
     * @return 一个对应的二维数组
     */
    double[][] stringToArray(String data);

    /**
     * 将字符串数据先转换为一个对应的二维数组之后，进行行列式计算
     * @param determinant 字符串数据
     * @return 行列式计算的值
     */
    double calculateDeterminant(String determinant);

    /**
     * 矩阵加法
     * @param matrix1 矩阵1
     * @param matrix2 矩阵2
     * @return 加完之后的矩阵
     */
    double[][] addMatrix(String matrix1, String matrix2);

    /**
     * 将一个二维的数组装换为字符串
     * @param arr 二维数组
     * @return 对应的字符串
     */
    String dArrayToString(double[][] arr);

    /**
     * 矩阵乘法
     * @param matrix1 矩阵1
     * @param matrix2 矩阵2
     * @return 乘完之后的矩阵
     */
    double[][] multiplyMatrix(String matrix1, String matrix2);

    /**
     * 矩阵转置
     * @param matrix 需要被转置的矩阵
     * @return 转置后的矩阵
     */
    double[][] transposeMatrix(String matrix);

    /**
     * 矩阵求逆
     * @param matrix 需要被求逆的矩阵
     * @return 求逆后的矩阵
     */
    double[][] inverseMatrix(String matrix);

    /**
     * 矩阵求秩
     * @param matrix 需要求秩的矩阵
     * @return 对应的秩
     */
    int rankMatrix(String matrix);

    /**
     * 矩阵求特征值
     * @param matrix 需要求特征值的矩阵
     * @return 对应的特征值
     */
    double[] characterMatrix(String matrix);

    /**
     * 计算线性方程组
     * @param matrix1 矩阵1
     * @param matrix2 矩阵2
     * @return 计算之后的矩阵
     */
    double[][] calculateEquations(String matrix1, String matrix2);

    /**
     * 判断向量组是否线性相关
     * @param matrix 矩阵
     * @return 是否线性相关
     */
    boolean correlateVectors(String matrix);

    /**
     * 判断两个矩阵是都相似
     * @param matrix1 矩阵1
     * @param matrix2 矩阵2
     * @return 相似与否
     */
    boolean similarMatrix(String matrix1, String matrix2);

    /**
     * 判断一个矩阵是不是方阵
     * @param matrix 矩阵
     * @return 是否是方阵
     */
    boolean checkSquareMatrix(Matrix matrix);



    Boolean findAlgorithms(String originalFilename);

    void insertAlgorithm(int aid, int uid, String originalFilename,String title, String context1);

    Algorithm findById(int id);

    boolean update(Algorithm algorithm);

    boolean delete(int id);

    List<cn.itcast.entity.Matrix> load(int uid);

    List<cn.itcast.entity.Matrix> loadByAid(int aid);

    boolean deleteByid(int id);

    PageResult findAll(QueryPageBean queryPageBean);
}
