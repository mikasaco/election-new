package com.mou.election.dal.domian;

import java.util.Date;

public class EApplyDO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_apply.id
     *
     * @mbggenerated Thu Jul 08 00:10:05 CST 2021
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_apply.gmt_create
     *
     * @mbggenerated Thu Jul 08 00:10:05 CST 2021
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_apply.gmt_modified
     *
     * @mbggenerated Thu Jul 08 00:10:05 CST 2021
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_apply.user_id
     *
     * @mbggenerated Thu Jul 08 00:10:05 CST 2021
     */
    private Long userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_apply.delay
     *
     * @mbggenerated Thu Jul 08 00:10:05 CST 2021
     */
    private String delay;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_apply.apply_election_date
     *
     * @mbggenerated Thu Jul 08 00:10:05 CST 2021
     */
    private Date applyElectionDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_apply.apply_remark
     *
     * @mbggenerated Thu Jul 08 00:10:05 CST 2021
     */
    private String applyRemark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_apply.feature
     *
     * @mbggenerated Thu Jul 08 00:10:05 CST 2021
     */
    private String feature;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_apply.status
     *
     * @mbggenerated Thu Jul 08 00:10:05 CST 2021
     */
    private String status;

    private Date delayElectionDate;

    private String sendStatus;


    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_apply.id
     *
     * @return the value of e_apply.id
     *
     * @mbggenerated Thu Jul 08 00:10:05 CST 2021
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_apply.id
     *
     * @param id the value for e_apply.id
     *
     * @mbggenerated Thu Jul 08 00:10:05 CST 2021
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_apply.gmt_create
     *
     * @return the value of e_apply.gmt_create
     *
     * @mbggenerated Thu Jul 08 00:10:05 CST 2021
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_apply.gmt_create
     *
     * @param gmtCreate the value for e_apply.gmt_create
     *
     * @mbggenerated Thu Jul 08 00:10:05 CST 2021
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_apply.gmt_modified
     *
     * @return the value of e_apply.gmt_modified
     *
     * @mbggenerated Thu Jul 08 00:10:05 CST 2021
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_apply.gmt_modified
     *
     * @param gmtModified the value for e_apply.gmt_modified
     *
     * @mbggenerated Thu Jul 08 00:10:05 CST 2021
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_apply.user_id
     *
     * @return the value of e_apply.user_id
     *
     * @mbggenerated Thu Jul 08 00:10:05 CST 2021
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_apply.user_id
     *
     * @param userId the value for e_apply.user_id
     *
     * @mbggenerated Thu Jul 08 00:10:05 CST 2021
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_apply.delay
     *
     * @return the value of e_apply.delay
     *
     * @mbggenerated Thu Jul 08 00:10:05 CST 2021
     */
    public String getDelay() {
        return delay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_apply.delay
     *
     * @param delay the value for e_apply.delay
     *
     * @mbggenerated Thu Jul 08 00:10:05 CST 2021
     */
    public void setDelay(String delay) {
        this.delay = delay == null ? null : delay.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_apply.apply_election_date
     *
     * @return the value of e_apply.apply_election_date
     *
     * @mbggenerated Thu Jul 08 00:10:05 CST 2021
     */
    public Date getApplyElectionDate() {
        return applyElectionDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_apply.apply_election_date
     *
     * @param applyElectionDate the value for e_apply.apply_election_date
     *
     * @mbggenerated Thu Jul 08 00:10:05 CST 2021
     */
    public void setApplyElectionDate(Date applyElectionDate) {
        this.applyElectionDate = applyElectionDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_apply.apply_remark
     *
     * @return the value of e_apply.apply_remark
     *
     * @mbggenerated Thu Jul 08 00:10:05 CST 2021
     */
    public String getApplyRemark() {
        return applyRemark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_apply.apply_remark
     *
     * @param applyRemark the value for e_apply.apply_remark
     *
     * @mbggenerated Thu Jul 08 00:10:05 CST 2021
     */
    public void setApplyRemark(String applyRemark) {
        this.applyRemark = applyRemark == null ? null : applyRemark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_apply.feature
     *
     * @return the value of e_apply.feature
     *
     * @mbggenerated Thu Jul 08 00:10:05 CST 2021
     */
    public String getFeature() {
        return feature;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_apply.feature
     *
     * @param feature the value for e_apply.feature
     *
     * @mbggenerated Thu Jul 08 00:10:05 CST 2021
     */
    public void setFeature(String feature) {
        this.feature = feature == null ? null : feature.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_apply.status
     *
     * @return the value of e_apply.status
     *
     * @mbggenerated Thu Jul 08 00:10:05 CST 2021
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_apply.status
     *
     * @param status the value for e_apply.status
     *
     * @mbggenerated Thu Jul 08 00:10:05 CST 2021
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getDelayElectionDate() {
        return delayElectionDate;
    }

    public void setDelayElectionDate(Date delayElectionDate) {
        this.delayElectionDate = delayElectionDate;
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }
}