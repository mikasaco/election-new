package com.mou.election.dal.domian;

import java.util.Date;

public class EResultDO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_result.id
     *
     * @mbggenerated Sat Jul 17 00:43:51 CST 2021
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_result.gmt_create
     *
     * @mbggenerated Sat Jul 17 00:43:51 CST 2021
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_result.gmt_modified
     *
     * @mbggenerated Sat Jul 17 00:43:51 CST 2021
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_result.exam_id
     *
     * @mbggenerated Sat Jul 17 00:43:51 CST 2021
     */
    private Long examId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_result.score
     *
     * @mbggenerated Sat Jul 17 00:43:51 CST 2021
     */
    private String score;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_result.user_id
     *
     * @mbggenerated Sat Jul 17 00:43:51 CST 2021
     */
    private Long userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_result.pass
     *
     * @mbggenerated Sat Jul 17 00:43:51 CST 2021
     */
    private Integer pass;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_result.id
     *
     * @return the value of e_result.id
     *
     * @mbggenerated Sat Jul 17 00:43:51 CST 2021
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_result.id
     *
     * @param id the value for e_result.id
     *
     * @mbggenerated Sat Jul 17 00:43:51 CST 2021
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_result.gmt_create
     *
     * @return the value of e_result.gmt_create
     *
     * @mbggenerated Sat Jul 17 00:43:51 CST 2021
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_result.gmt_create
     *
     * @param gmtCreate the value for e_result.gmt_create
     *
     * @mbggenerated Sat Jul 17 00:43:51 CST 2021
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_result.gmt_modified
     *
     * @return the value of e_result.gmt_modified
     *
     * @mbggenerated Sat Jul 17 00:43:51 CST 2021
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_result.gmt_modified
     *
     * @param gmtModified the value for e_result.gmt_modified
     *
     * @mbggenerated Sat Jul 17 00:43:51 CST 2021
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_result.exam_id
     *
     * @return the value of e_result.exam_id
     *
     * @mbggenerated Sat Jul 17 00:43:51 CST 2021
     */
    public Long getExamId() {
        return examId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_result.exam_id
     *
     * @param examId the value for e_result.exam_id
     *
     * @mbggenerated Sat Jul 17 00:43:51 CST 2021
     */
    public void setExamId(Long examId) {
        this.examId = examId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_result.score
     *
     * @return the value of e_result.score
     *
     * @mbggenerated Sat Jul 17 00:43:51 CST 2021
     */
    public String getScore() {
        return score;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_result.score
     *
     * @param score the value for e_result.score
     *
     * @mbggenerated Sat Jul 17 00:43:51 CST 2021
     */
    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_result.user_id
     *
     * @return the value of e_result.user_id
     *
     * @mbggenerated Sat Jul 17 00:43:51 CST 2021
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_result.user_id
     *
     * @param userId the value for e_result.user_id
     *
     * @mbggenerated Sat Jul 17 00:43:51 CST 2021
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_result.pass
     *
     * @return the value of e_result.pass
     *
     * @mbggenerated Sat Jul 17 00:43:51 CST 2021
     */
    public Integer getPass() {
        return pass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_result.pass
     *
     * @param pass the value for e_result.pass
     *
     * @mbggenerated Sat Jul 17 00:43:51 CST 2021
     */
    public void setPass(Integer pass) {
        this.pass = pass;
    }
}