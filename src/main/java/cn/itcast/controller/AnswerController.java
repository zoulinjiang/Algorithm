package cn.itcast.controller;

import cn.itcast.config.SysLog;
import cn.itcast.entity.Answer;
import cn.itcast.entity.Result;
import cn.itcast.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @RequestMapping("/findAnswerById")
    public Result findById(int id){
      Answer answer=  answerService.selectById(id);
      return new Result(true,"请编辑答案内容",answer);
    }
    @SysLog("编辑答案")
    @RequestMapping("/updateAnswerById")
    public Result updateById(@RequestBody Answer answer){
        answerService.updateById(answer);
      return new Result(true,"请编辑答案内容");
    }

    //添加回答
    @SysLog("添加答案")
    @RequestMapping("/addAnswer")
    public Result addAnswer(@RequestBody Answer answer) throws IOException {
        if (answer.getResponse().length()==0){
         return new Result(true,"答案内容为空，请重新发布");
        }
        boolean b = answerService.addAnswer(answer);
        if (b) {
            return new Result(true,"添加答案成功");
        }else{
            return new Result(true,"添加答案失败");
        }
    }

    //删除回答,aid是answer对象的id
    @SysLog("删除答案")
    @RequestMapping("/removeAnswer")
    public Result removeAnswer(int aid) throws IOException {
        Answer answer = answerService.findAnswerById(aid);
        boolean b = answerService.removeAnswer(aid);
        if (b){
            return new Result(true,"添加答案成功");
        }else {
            return new Result(true,"添加答案成功");
        }
    }
}
