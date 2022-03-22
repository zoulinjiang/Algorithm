package cn.itcast.controller;

import cn.itcast.config.SysLog;
import cn.itcast.entity.PageResult;
import cn.itcast.entity.QueryPageBean;
import cn.itcast.entity.Result;
import cn.itcast.entity.User;
import cn.itcast.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private RegisterService registerService;
    @SysLog("用户注册")
    @RequestMapping("/register")
    public Result register(@RequestBody User user){
        Boolean flag = registerService.register(user);
        if (!flag){
            return new Result(false,"用户名已被注册使用");
        }
        return new Result(true,"注册成功");
    }
    @SysLog("用户激活")
    @RequestMapping("/active")
    public String active(String code){
        if (code!=null){
            boolean flag=  registerService.active(code);
            //3.判断标记
            String msg = null;
            if(flag){
                msg = "激活成功，请<a href='http://localhost:8081/'>点击登入用户端</a>";
            }else{msg = "激活失败，请联系管理员!";}
            return msg;
        }
        return null;
    }
@SysLog("用户登入")
    @RequestMapping("/login")
    public Result login(@RequestBody User user){
        User user1= registerService.login(user);
        if(user1 !=null){
            if(user1.getStatus()==null){
                return new Result(false,"未激活邮箱,请激活邮箱");
            }
            if (user1.getStatus().equals("Y")){
                return new Result(true,"用户登入成功",user1.getId());
            }else {
                return new Result(false,"该账号已被冻结，请联系管理员");
            }
        }else {
            return new Result(false,"账号密码输入错误");
        }}
@SysLog("添加用户")
    @RequestMapping("add")
    public Result add(@RequestBody User user) {
      boolean flag=  registerService.save(user);
        if (flag){return new Result(true,"新增用户成功");}
        else{return new Result(false,"新增用户失败");}
    }
    @SysLog("编辑用户")
    @RequestMapping("/edit")
    public Result edit(@RequestBody User user){
        System.out.println(user);
      boolean flag=  registerService.editUser(user);
        if (flag){return new Result(true,"编辑用户成功");}
        else{return new Result(false,"编辑用户失败");}
    }

    @RequestMapping("/findId")
    public Result findUser(int id){
        User user=  registerService.findUserId(id);
        return new Result(true,"回显用户信息成功",user);
    }
    @SysLog("删除用户")
    @RequestMapping("/delete")
    public Result delete(int id){
      boolean flag=  registerService.deleteUser(id);
      if (flag){return new Result(true,"删除用户成功");}
      else{return new Result(false,"删除用户失败");}

    }
    @SysLog("冻结用户")
    @RequestMapping("/freeze")
    public Result freeze(int id){
      boolean flag=  registerService.freezeUser(id);
      if (flag){ return new Result(true,"冻结用户成功"); }
        else {return new Result(true,"冻结用户失败");}
    }
    @SysLog("解冻用户")
    @RequestMapping("/unfreeze")
    public Result unfreeze(int id){
      boolean flag=  registerService.unfreezeUser(id);
      if (flag){ return new Result(true,"用户成功，变回正常状态"); }
        else {return new Result(true,"用户失败，变回正常状态");}
    }

    @RequestMapping("/findAll")
    public PageResult findUser(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult= registerService.findAll(queryPageBean);
        return pageResult;
    }
}
