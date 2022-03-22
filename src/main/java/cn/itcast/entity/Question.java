package cn.itcast.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("question")
public class Question {
    @TableId
    private Integer id;
    private Integer uid;
    private Integer aid;
    private String question;
    private String username;

    public Question(Integer uid, Integer aid, String question, String username) {
        this.uid = uid;
        this.aid = aid;
        this.question = question;
        this.username = username;
    }
}
