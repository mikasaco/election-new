package com.mou.election.dal.mapper;

import com.mou.election.dal.domian.EExamAnswerDO;
import com.mou.election.dal.domian.EExamAnswerDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EExamAnswerDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table e_exam_answer
     *
     * @mbggenerated Sun Jul 18 11:57:23 CST 2021
     */
    int countByExample(EExamAnswerDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table e_exam_answer
     *
     * @mbggenerated Sun Jul 18 11:57:23 CST 2021
     */
    int deleteByExample(EExamAnswerDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table e_exam_answer
     *
     * @mbggenerated Sun Jul 18 11:57:23 CST 2021
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table e_exam_answer
     *
     * @mbggenerated Sun Jul 18 11:57:23 CST 2021
     */
    int insert(EExamAnswerDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table e_exam_answer
     *
     * @mbggenerated Sun Jul 18 11:57:23 CST 2021
     */
    int insertSelective(EExamAnswerDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table e_exam_answer
     *
     * @mbggenerated Sun Jul 18 11:57:23 CST 2021
     */
    List<EExamAnswerDO> selectByExample(EExamAnswerDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table e_exam_answer
     *
     * @mbggenerated Sun Jul 18 11:57:23 CST 2021
     */
    EExamAnswerDO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table e_exam_answer
     *
     * @mbggenerated Sun Jul 18 11:57:23 CST 2021
     */
    int updateByExampleSelective(@Param("record") EExamAnswerDO record, @Param("example") EExamAnswerDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table e_exam_answer
     *
     * @mbggenerated Sun Jul 18 11:57:23 CST 2021
     */
    int updateByExample(@Param("record") EExamAnswerDO record, @Param("example") EExamAnswerDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table e_exam_answer
     *
     * @mbggenerated Sun Jul 18 11:57:23 CST 2021
     */
    int updateByPrimaryKeySelective(EExamAnswerDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table e_exam_answer
     *
     * @mbggenerated Sun Jul 18 11:57:23 CST 2021
     */
    int updateByPrimaryKey(EExamAnswerDO record);
}