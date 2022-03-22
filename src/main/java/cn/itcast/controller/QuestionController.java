package cn.itcast.controller;

import cn.itcast.config.SysLog;
import cn.itcast.entity.*;
import cn.itcast.service.AnswerService;
import cn.itcast.service.QuestionService;
import cn.itcast.service.impl.CatagoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private CatagoryServiceImpl catagoryService;

    //加载问题列表
    @RequestMapping("/loadingQuestions")
    public PageResult loadingQuestions(@RequestBody QueryPageBean queryPageBean) throws IOException {

        return questionService.findQuestions(queryPageBean);
    }
    @SysLog("加载用户问题")
    @RequestMapping("/loadingQuestionByUid")
    public Result loadingQuestionaByUid(int id) throws IOException {
        List<Question> questions = questionService.findByUid(id);

        return new Result(true,"查询用户问题列表",questions);
    }

    @SysLog("加载问题分类")
    @RequestMapping("/loadingQuestionByAid")
    public Result loadingQuestiona(int aid) throws IOException {
        List<Question> questions = questionService.findByAid(aid);
        String name = catagoryService.findName(aid);
        Map<String,Object> map=  new HashMap<>();
        map.put("name",name);
        map.put("questions",questions);
        return new Result(true,"查询问题列表",map);
    }

    //加载指定问题和该问题的回答
    @SysLog("加载指定问题和问题回答")
    @RequestMapping("/loadingQuestionAndAnswer")
    public Result loadingQuestionAndAnswer(Integer qid) throws IOException {
        Question question = questionService.findQuestionByQid(qid);
        //完成answer的加载
        List<Answer> answers = answerService.findAnswerByQid(qid);
        Map<String,Object> map= new HashMap<>();
        map.put("question",question);
        map.put("answers",answers);
        return new Result(true,"查询成功",map);
    }

    //加载某用户的问题列表
//    @RequestMapping("/loadingUserQuestions")
//    public void loadingUserQuestions(User user) throws IOException {
//        List<Question> questions = questionService.findQuestionsByUser(user);
//
//    }

    //添加问题
    @SysLog("添加问题")
    @RequestMapping("/addQuestion")
    public Result addQuestion(@RequestBody Question question) throws IOException {
        if (question.getQuestion().length()==0) {
            return new Result(false, "问题内容为空，请重新发布");
        }
        if (question.getAid()==null){
            return new Result(false, "问题种类未选，请重新发布");
        }

            boolean b = questionService.addQuestion(question);
            return new Result(true, "问题发布成功");

    }
    @SysLog("编辑问题")
    @RequestMapping("/updateQuestion")
    public Result updateQuestion(@RequestBody Question question) throws IOException {
        boolean b = questionService.updateQuestion(question);
        if (b)
        return new Result(true,"问题编辑成功");
        else return new Result(false,"问题编辑失败");
    }

    //删除问题
    @SysLog("删除问题")
    @RequestMapping("/removeQuestion")
    public Result removeQuestion(int qid) throws IOException {
        boolean b = questionService.removeQuestion(qid);
        if (b){
            return new Result(true,"删除问题成功");
        }else {
            return new Result(false,"删除问题失败");
        }
    }

}
