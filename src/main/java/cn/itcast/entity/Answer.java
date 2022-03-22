package cn.itcast.entity;

import lombok.Data;

@Data
public class Answer {
    private Integer id;
    private Integer qid;//回答对应的问题的id
    private Integer uid;//回答问题的用户的id
    private String response;
    private  String username;

    public Answer() {
    }

    public Answer(Integer qid, Integer uid, String response, String username) {
        this.qid = qid;
        this.uid = uid;
        this.response = response;
        this.username = username;
    }


}
