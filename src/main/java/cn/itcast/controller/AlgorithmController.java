package cn.itcast.controller;

import cn.itcast.config.SysLog;
import cn.itcast.entity.*;

import cn.itcast.entity.vo.AlgorithmsVo;
import cn.itcast.entity.vo.MatrixVo;
import cn.itcast.service.AlgorithmService;
import cn.itcast.service.MatrixService;
import cn.itcast.service.impl.CatagoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class AlgorithmController {

    @Autowired
    private AlgorithmService algorithmService;
    @Autowired
    private CatagoryServiceImpl catagoryService;
    @SysLog("删除历史计算")
    @RequestMapping("/deleteComputeById")
    public Result deleteByid( int id){
        boolean b =algorithmService.deleteByid(id);
        if (b){
            return new Result(true,"删除历史计算成功");
         }else {return new Result(false,"删除历史计算失败");}

    }
    @SysLog("用户查找章节算法")
    @RequestMapping("/loadComputeByAid")
    public Result loadByAid( int aid){
        List<Matrix> matrixList=  algorithmService.loadByAid(aid);
        String name = catagoryService.findName(aid);
        Map<String,Object> map = new HashMap<>();
        map.put("catagoryName",name);
        map.put("computes",matrixList);
        return new Result(true,"查新历史计算成功",map);
    }
    @SysLog("用户查询历史计算")
    @RequestMapping("/loadComputeByUid")
    public Result load( int uid){
        List<Matrix> matrixList=  algorithmService.load(uid);
        return new Result(true,"查询历史计算成功",matrixList);
    }
    @SysLog("删除算法")
    @RequestMapping("/deleteALgorithm")
    public Result delete( int id){
        boolean flag=  algorithmService.delete(id);
        return new Result(true,"删除算法成功");
    }
    @SysLog("编辑算法")
    @RequestMapping("/updateALgorithm")
    public Result update(@RequestBody Algorithm algorithm){
       boolean flag=  algorithmService.update(algorithm);
        return new Result(true,"编辑算法成功");
    }

    @RequestMapping("/findALgorithmById")
    public Result findById(int id){
        Algorithm algorithm= algorithmService.findById(id);
        return new Result(true,"查询算法",algorithm);
    }

    @RequestMapping("/allAlgorithmResource")
    public PageResult all(@RequestBody QueryPageBean queryPageBean){

       return algorithmService.findAll(queryPageBean);
    }
    @RequestMapping("/loadingAlgorithmResource")
    public Result loadingAlgorithmResource(Integer aid) throws IOException {
        List<Algorithm> algorithms = algorithmService.findAlgorithmsByAid(aid);
        String name = catagoryService.findName(aid);
        AlgorithmsVo algorithmsVo = new AlgorithmsVo(name,algorithms);
        return new Result(true,"查询章节算法",algorithmsVo);
    }
    @RequestMapping("/loadingUserAlgorithmResource")
    public Result loadingUserAlgorithmResource(int uid) throws IOException {

        List<Algorithm> algorithms = algorithmService.findAlgorithmsByUid(uid);
        return new Result(true,"查询用户算法",algorithms);
    }
//删除指定章节的指定算法文件
    @RequestMapping("/removeAlgorithm")
    public void removeAlgorithm(int index, int aid, int id) throws IOException {

    }
    @SysLog("下载算法文件")
    @RequestMapping("/down")
    public ResponseEntity<byte[]> dd(int id) throws IOException, InterruptedException {
       Algorithm algorithm = algorithmService.findById(id);
        String title = algorithm.getTitle();
        File file = new File("C:\\Users\\timeuse\\IdeaProjects\\1\\Artical1\\src\\main\\resources\\static\\" + id + ".txt");
        FileWriter writer = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write(title);
        bufferedWriter.newLine();
        bufferedWriter.write(algorithm.getContent());

        writer.flush();
        bufferedWriter.close();
        //创建输入流
        InputStream is = new FileInputStream(file);
        byte[] bytes = is.readAllBytes();
        //创建HttpHeaders对象设置响应头信息
        MultiValueMap<String, String> headers = new HttpHeaders();
        //设置要下载方式以及下载文件的名字
        System.out.println(file.getName()+"-------------------------");
        headers.add("Content-Disposition", "attachment;filename="+file.getName());
        //设置响应状态码
        HttpStatus statusCode = HttpStatus.OK;
        //创建ResponseEntity对象
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, statusCode);
        //关闭输入流
        is.close();

        return responseEntity;
    }
    @SysLog("上传算法文件")
    @RequestMapping("/upload")
    public Result upload1(MultipartFile multipartFile,int aid, int uid) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        File file = new File("C:\\Users\\timeuse\\IdeaProjects\\1\\Artical1\\src\\main\\resources\\static\\" + originalFilename);
        multipartFile.transferTo(file);

        Reader ris = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(ris);
        String title = bufferedReader.readLine();
        System.out.println(title);
        System.out.println("----------------------------------------");
        String context;
        String context1 = "";
        while ((context= bufferedReader.readLine()) !=null)
        {context1 += context;}
        System.out.println(context1);

        Boolean flag= algorithmService.findAlgorithms(title);
        if (flag){
            algorithmService.insertAlgorithm(aid,uid,originalFilename,title,context1);
            return new Result(true,"文件上传成功");
        }else {
            return new Result(false,"文件上传失败");
        }

    }
@Autowired
private MatrixService matrixService;

//   计算行列式
    @RequestMapping("/runDeterminant")
    public String runDeterminant(@RequestBody Matrix matrix){
        String msg = "格式错误";
        try {
            msg = String.valueOf(algorithmService.calculateDeterminant(matrix.getMatrix1()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!msg.equals("格式错误")){
            SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = new Date();
            String format = simpleFormat.format(now);
            Matrix matrix1 = new Matrix(matrix.getUid(),matrix.getAid(), matrix.getMatrix1(),msg,format);
            matrixService.save(matrix1);
        }
        return msg;
    }
//    矩阵加法
    @RequestMapping("/addMatrix")
    public String runMatrix(@RequestBody Matrix matrix){
        String msg = "格式错误";
        try {
            msg = algorithmService.dArrayToString(algorithmService.addMatrix(matrix.getMatrix1(), matrix.getMatrix2()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!msg.equals("格式错误")){
            SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = new Date();
            String format = simpleFormat.format(now);

            Matrix matrix1 = new Matrix(matrix.getUid(),matrix.getAid(), matrix.getMatrix1(), matrix.getMatrix2() , msg, format);
            matrixService.save(matrix1);
        }
        return msg;
    }
    @RequestMapping("/multiplyMatrix")
    public String multiplyMatrix(@RequestBody Matrix matrix){
        String msg = "格式错误";
        try {
            msg = algorithmService.dArrayToString(algorithmService.multiplyMatrix(matrix.getMatrix1(), matrix.getMatrix2()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!msg.equals("格式错误")){
            SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = new Date();
            String format = simpleFormat.format(now);
            Matrix matrix1 = new Matrix(matrix.getUid(),matrix.getAid(), matrix.getMatrix1(), matrix.getMatrix2() , msg, format);
            matrixService.save(matrix1);
        }
        return msg;
    }
//    矩阵转置
    @RequestMapping("/transMatrix")
    public String transMatrix(@RequestBody Matrix matrix){
        String msg = "格式错误";
        try {
            msg = algorithmService.dArrayToString(algorithmService.transposeMatrix(matrix.getMatrix1()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!msg.equals("格式错误")){
            SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = new Date();
            String format = simpleFormat.format(now);
            Matrix matrix1 = new Matrix(matrix.getUid(),matrix.getAid(), matrix.getMatrix1(),msg,format);
            matrixService.save(matrix1);
        }
        return msg;
    }
//    矩阵求逆
    @RequestMapping("/inverseMatrix")
    public String inverseMatrix(@RequestBody Matrix matrix){
        String msg = "格式错误";
        try {
            msg = algorithmService.dArrayToString(algorithmService.inverseMatrix(matrix.getMatrix1()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!msg.equals("格式错误")){
            SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = new Date();
            String format = simpleFormat.format(now);
            Matrix matrix1 = new Matrix(matrix.getUid(),matrix.getAid(), matrix.getMatrix1(),msg,format);
            matrixService.save(matrix1);
        }
        return msg;
    }
//矩阵求秩
    @RequestMapping("/rankMatrix")
    public String rankMatrix(@RequestBody Matrix matrix){
        String msg = "格式错误";
        try {
            msg = String.valueOf(algorithmService.rankMatrix(matrix.getMatrix1()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!msg.equals("格式错误")){
            SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = new Date();
            String format = simpleFormat.format(now);
            Matrix matrix1 = new Matrix(matrix.getUid(),matrix.getAid(), matrix.getMatrix1(),msg,format);
            matrixService.save(matrix1);
        }
        return msg;
    }
//    矩阵求特征值
    @RequestMapping("/characterMatrix")
    public String characterMatrix(@RequestBody Matrix matrix){
        String msg = "格式错误";
        try {
            msg = Arrays.toString(algorithmService.characterMatrix(matrix.getMatrix1()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!msg.equals("格式错误")){
            SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = new Date();
            String format = simpleFormat.format(now);
            Matrix matrix1 = new Matrix(matrix.getUid(),matrix.getAid(), matrix.getMatrix1(),msg,format);
            matrixService.save(matrix1);
        }
        return msg;
    }
//解线性方程组
    @RequestMapping("/calculateEquations")
    public String calculateEquations(@RequestBody Matrix matrix){
        String msg = "格式错误";
        try {
            msg = algorithmService.dArrayToString(algorithmService.calculateEquations(matrix.getMatrix1(), matrix.getMatrix2()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!msg.equals("格式错误")){
            SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = new Date();
            String format = simpleFormat.format(now);
            Matrix matrix1 = new Matrix(matrix.getUid(),matrix.getAid(), matrix.getMatrix1(), matrix.getMatrix2() , msg, format);
            matrixService.save(matrix1);
        }
        return msg;
    }
//    判断向量组是否线性相关
    @RequestMapping("/correlateVectors")
    public String correlateVectors(@RequestBody Matrix matrix){
        String msg = "格式错误";
        try {
            msg = String.valueOf(algorithmService.correlateVectors(matrix.getMatrix1()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!msg.equals("格式错误")){
            SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = new Date();
            String format = simpleFormat.format(now);
            Matrix matrix1 = new Matrix(matrix.getUid(),matrix.getAid(), matrix.getMatrix1(),msg,format);
            matrixService.save(matrix1);
        }
        return msg;
    }
    //矩阵是否相似
    @RequestMapping("/similarMatrix")
    public String similarMatrix(@RequestBody Matrix matrix){
        String msg = "格式错误";
        try {
            msg = String.valueOf(algorithmService.similarMatrix(matrix.getMatrix1(), matrix.getMatrix2()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!msg.equals("格式错误")){
            SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = new Date();
            String format = simpleFormat.format(now);
            Matrix matrix1 = new Matrix(matrix.getUid(),matrix.getAid(), matrix.getMatrix1(), matrix.getMatrix2() , msg, format);
            matrixService.save(matrix1);
        }
        return msg;
    }
}
