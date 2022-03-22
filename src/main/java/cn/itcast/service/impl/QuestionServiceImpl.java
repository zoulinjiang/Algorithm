package cn.itcast.service.impl;

import cn.itcast.dao.QuestionDao;
import cn.itcast.dao.UserDao;
import cn.itcast.entity.PageResult;
import cn.itcast.entity.QueryPageBean;
import cn.itcast.entity.Question;
import cn.itcast.entity.User;
import cn.itcast.service.QuestionService;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private UserDao userDao;
    @Override
    public boolean addQuestion(Question question){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getId,question.getUid());
        User user = userDao.selectOne(lambdaQueryWrapper);

        if(user==null){
            question.setUsername("管理员");
        }else{
            question.setUsername(user.getUsername());}

        int num = questionDao.insert(question);
        if (num!=1)
            return false;
        else
            return true;
    }

    @Override
    public boolean removeQuestion(int qid){
        int num = questionDao.deleteById(qid);
        if (num!=1)
            return false;
        else
            return true;
    }

    @Override
    @Transactional
    public boolean removeQuestions(List<Question> list){
        for (Question question: list) {
            if (!removeQuestion(question.getId()))
                return false;
        }
        return true;
    }



//    @Override
//    public List<Question> findQuestionsByUser(User user) {
//        if (user!=null)
//                return questionDao.selectQuestionsByUser(user);
//        else
//            return null;
//    }

    @Override
    public Question findQuestionByQid(Integer qid) {
        if(qid!=null && qid>=0)
            return questionDao.selectById(qid);
        else
            return null;
    }

    @Override
    public List<Question> findByAid(int aid) {
        LambdaQueryWrapper<Question> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Question::getAid,aid);
        List<Question> questions = questionDao.selectList(lambdaQueryWrapper);
        return questions;
    }

    @Override
    public boolean updateQuestion(Question question) {
        LambdaQueryWrapper<Question> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Question::getId,question.getId());

        return questionDao.update(question,lambdaQueryWrapper)>0;
    }

    @Override
    public PageResult findQuestions(QueryPageBean queryPageBean) {
        Page<Question> page = new Page<>(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());

        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        if(queryPageBean.getQueryString() == null){

        }else {
            queryWrapper.like("question",queryPageBean.getQueryString());
        }
        Page<Question> userPage = questionDao.selectPage(page, queryWrapper);
        return new PageResult(userPage.getTotal(),userPage.getRecords());
    }

    @Override
    public List<Question> findByUid(int id) {
        List<Question> questions = questionDao.selectList(new LambdaQueryWrapper<Question>().eq(Question::getUid, id));
        return questions;
    }
}
