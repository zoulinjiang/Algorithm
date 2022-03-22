package cn.itcast.entity;

import java.util.Date;

public class Log {
    private Integer id;
    private Date time;
    private Integer uid;//发生异常的用户id
    private String message;

    public Log() {}

    public Log(Date time, Integer uid, String message) {
        this.time = time;
        this.uid = uid;
        this.message = message;
    }

    public Log(Integer id, Date time, Integer uid, String message) {
        this.id = id;
        this.time = time;
        this.uid = uid;
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "log{" +
                "id=" + id +
                ", time=" + time +
                ", uid=" + uid +
                ", message='" + message + '\'' +
                '}';
    }
}
