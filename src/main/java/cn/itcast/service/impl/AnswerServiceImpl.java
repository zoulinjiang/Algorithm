package cn.itcast.service.impl;

import cn.itcast.dao.AnswerDao;
import cn.itcast.dao.UserDao;
import cn.itcast.entity.Answer;
import cn.itcast.entity.User;
import cn.itcast.service.AnswerService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerDao answerDao;
    @Autowired
    private UserDao userDao;

    /**
     * 添加回复
     * @param answer 回答
     * @return 添加回复成功与否
     */
    @Override
    public boolean addAnswer(Answer answer) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getId,answer.getUid());
        User user = userDao.selectOne(lambdaQueryWrapper);
        if (user==null){
            answer.setUsername("管理员");
        }else
        answer.setUsername(user.getUsername());
        if (answer!=null){
            int num = answerDao.insert(answer);
            if (num==1)
                return true;
        }
        return false;
    }

    @Override
    public boolean removeAnswer(Integer id) {
        if (id!=null){
            int num = answerDao.deleteById(id);
            if (num==1)
                return true;
        }
        return false;
    }

    @Override
    public Answer findAnswerById(int id) {
        if (id == 0)
            return null;
        else
            return answerDao.selectById(id);
    }

    @Override
    public List<Answer> findAnswerByQid(Integer qid) {
        if (qid!=null) {
            LambdaQueryWrapper<Answer> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Answer::getQid,qid);
            return answerDao.selectList(lambdaQueryWrapper);
        }

        else
            return null;
    }

    @Override
    public List<Answer> findAnswerByUid(Integer uid) {
        if (uid!=null) {
            LambdaQueryWrapper<Answer> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Answer::getUid,uid);
            return answerDao.selectList(lambdaQueryWrapper);
        }
        else
            return null;
    }

    @Override
    public Answer selectById(int id) {
        Answer answer = answerDao.selectById(id);
        return answer;
    }

    @Override
    public void updateById(Answer answer) {
        answerDao.updateById(answer);
    }
}
