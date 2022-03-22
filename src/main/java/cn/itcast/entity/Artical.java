package cn.itcast.entity;

import lombok.Data;

@Data
public class Artical {
    private int id;
    private String title;
    private String img;
    private String context;
    private int user_id;


    public Artical() {
    }

    public Artical(int id, String title, String img, String context, int user_id ) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.context = context;
        this.user_id = user_id;
    }
}
