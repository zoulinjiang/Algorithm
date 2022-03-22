package cn.itcast.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class Algorithm {
    private Integer id;
    private Integer aid;//章节的编号
    private Integer uid;//上传算法的用户编号
    private String algorithmname;//算法
    private String title;
    private  String content;
    private String username;


    public Algorithm(Integer aid, Integer uid, String algorithmName) {
        this.aid = aid;
        this.uid = uid;
        this.algorithmname = algorithmName;
    }

    public Algorithm(Integer aid, Integer uid, String algorithmname, String title, String content,String username) {
        this.aid = aid;
        this.uid = uid;
        this.algorithmname = algorithmname;
        this.title = title;
        this.content = content;
        this.username = username;
    }

    public Algorithm() {
    }

    public Algorithm(Integer id, Integer aid, Integer uid, String algorithmName) {
        this.id = id;
        this.aid = aid;
        this.uid = uid;
        this.algorithmname = algorithmName;
    }

}
