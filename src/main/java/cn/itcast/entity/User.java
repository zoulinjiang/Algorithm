package cn.itcast.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.hssf.record.formula.functions.Char;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("users")
public class User {
    @TableId
    private Integer id;
    private String username;
    private String password;
    private boolean is_admin;
    private String email;
    private  String code;
    private String status;

    public boolean getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }
}
