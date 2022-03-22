package cn.itcast.service;

import cn.itcast.entity.Answer;


import java.util.List;

public interface AnswerService {

    /**
     * 添加回答
     * @param answer 回答
     * @return 添加成功的个数
     */
    boolean addAnswer(Answer answer);

    /**
     * 删除回答
     * @param id 回答的id
     * @return 删除成功的个数
     */
    boolean removeAnswer(Integer id);

    /**
     * 通过answer对象的id获取指定的answer对象
     * @param id 指定的answer对象的id
     * @return 一个对应的answer对象
     */
    Answer findAnswerById(int id);

    /**
     * 查找指定问题下的所有回答
     * @param qid 指定问题的id
     * @return 回答列表
     */
    List<Answer> findAnswerByQid(Integer qid);

    /**
     * 查找指定用户的所有回答
     * @param uid 指定用户的id
     * @return 回答列表
     */
    List<Answer> findAnswerByUid(Integer uid);

    Answer selectById(int id);

    void updateById(Answer answer);
}
