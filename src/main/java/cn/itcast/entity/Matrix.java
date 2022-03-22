package cn.itcast.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Matrix)表实体类
 *
 * @author makejava
 * @since 2022-03-01 15:57:14
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@TableName("matrix")
public class Matrix  {
    @TableId
    private Integer id;

    
    private Integer uid;
    
    private Integer aid;
    
    private String matrix1;
    
    private String matrix2;
    
    private String result;
    
    private String time;


    public Matrix(Integer uid, Integer aid, String matrix1, String result, String time) {
        this.uid = uid;
        this.aid = aid;
        this.matrix1 = matrix1;
        this.result = result;
        this.time = time;
    }

    public Matrix(Integer uid, Integer aid, String matrix1, String matrix2, String result, String time) {
        this.uid = uid;
        this.aid = aid;
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.result = result;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Matrix{" +
                "id=" + id +
                ", uid=" + uid +
                ", aid=" + aid +
                ", matrix1='" + matrix1 + '\'' +
                ", matrix2='" + matrix2 + '\'' +
                ", result='" + result + '\'' +
                ", time=" + time +
                '}';
    }
}

