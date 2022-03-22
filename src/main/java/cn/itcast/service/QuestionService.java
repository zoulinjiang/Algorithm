package cn.itcast.service;

import cn.itcast.entity.PageResult;
import cn.itcast.entity.QueryPageBean;
import cn.itcast.entity.Question;

import java.util.List;

public interface QuestionService {

    public boolean addQuestion(Question question);

    /**
     * 删除单个问题
     * @param qid 被删除的问题的id
     * @return 删除是否成功
     */
    public boolean removeQuestion(int qid);

    public boolean removeQuestions(List<Question> list);



    /**
     * 查询某个用户的所有问题
     * @param user 用户信息
     * @return 指定用户的所有信息，如果用户为空，则返回null
     */
//    public List<Question> findQuestionsByUser(User user);

    /**
     * 根据指定的id查找问题
     * @param qid 问题的id、
     * @return 被查找到的问题，如果没找到，则返回null
     */
    public Question findQuestionByQid(Integer qid);

    List<Question> findByAid(int aid);

    boolean updateQuestion(Question question);

    PageResult findQuestions(QueryPageBean queryPageBean);

    List<Question> findByUid(int id);
}
