package com.mou.election.dal.domian;

import java.util.Date;

public class EuserDO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_user.id
     *
     * @mbggenerated Wed Jul 07 01:42:13 CST 2021
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_user.gmt_create
     *
     * @mbggenerated Wed Jul 07 01:42:13 CST 2021
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_user.gmt_modified
     *
     * @mbggenerated Wed Jul 07 01:42:13 CST 2021
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_user.user_name
     *
     * @mbggenerated Wed Jul 07 01:42:13 CST 2021
     */
    private String userName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_user.phone
     *
     * @mbggenerated Wed Jul 07 01:42:13 CST 2021
     */
    private String phone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_user.password
     *
     * @mbggenerated Wed Jul 07 01:42:13 CST 2021
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_user.open_id
     *
     * @mbggenerated Wed Jul 07 01:42:13 CST 2021
     */
    private String openId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_user.change_term_date
     *
     * @mbggenerated Wed Jul 07 01:42:13 CST 2021
     */
    private Date changeTermDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_user.organization_id
     *
     * @mbggenerated Wed Jul 07 01:42:13 CST 2021
     */
    private Long organizationId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_user.post
     *
     * @mbggenerated Wed Jul 07 01:42:13 CST 2021
     */
    private String post;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_user.status
     *
     * @mbggenerated Wed Jul 07 01:42:13 CST 2021
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_user.feature
     *
     * @mbggenerated Wed Jul 07 01:42:13 CST 2021
     */
    private String feature;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_user.id
     *
     * @return the value of e_user.id
     *
     * @mbggenerated Wed Jul 07 01:42:13 CST 2021
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_user.id
     *
     * @param id the value for e_user.id
     *
     * @mbggenerated Wed Jul 07 01:42:13 CST 2021
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_user.gmt_create
     *
     * @return the value of e_user.gmt_create
     *
     * @mbggenerated Wed Jul 07 01:42:13 CST 2021
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_user.gmt_create
     *
     * @param gmtCreate the value for e_user.gmt_create
     *
     * @mbggenerated Wed Jul 07 01:42:13 CST 2021
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_user.gmt_modified
     *
     * @return the value of e_user.gmt_modified
     *
     * @mbggenerated Wed Jul 07 01:42:13 CST 2021
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_user.gmt_modified
     *
     * @param gmtModified the value for e_user.gmt_modified
     *
     * @mbggenerated Wed Jul 07 01:42:13 CST 2021
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_user.user_name
     *
     * @return the value of e_user.user_name
     *
     * @mbggenerated Wed Jul 07 01:42:13 CST 2021
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_user.user_name
     *
     * @param userName the value for e_user.user_name
     *
     * @mbggenerated Wed Jul 07 01:42:13 CST 2021
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_user.phone
     *
     * @return the value of e_user.phone
     *
     * @mbggenerated Wed Jul 07 01:42:13 CST 2021
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_user.phone
     *
     * @param phone the value for e_user.phone
     *
     * @mbggenerated Wed Jul 07 01:42:13 CST 2021
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_user.password
     *
     * @return the value of e_user.password
     *
     * @mbggenerated Wed Jul 07 01:42:13 CST 2021
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_user.password
     *
     * @param password the value for e_user.password
     *
     * @mbggenerated Wed Jul 07 01:42:13 CST 2021
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_user.open_id
     *
     * @return the value of e_user.open_id
     *
     * @mbggenerated Wed Jul 07 01:42:13 CST 2021
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_user.open_id
     *
     * @param openId the value for e_user.open_id
     *
     * @mbggenerated Wed Jul 07 01:42:13 CST 2021
     */
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_user.change_term_date
     *
     * @return the value of e_user.change_term_date
     *
     * @mbggenerated Wed Jul 07 01:42:13 CST 2021
     */
    public Date getChangeTermDate() {
        return changeTermDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_user.change_term_date
     *
     * @param changeTermDate the value for e_user.change_term_date
     *
     * @mbggenerated Wed Jul 07 01:42:13 CST 2021
     */
    public void setChangeTermDate(Date changeTermDate) {
        this.changeTermDate = changeTermDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_user.organization_id
     *
     * @return the value of e_user.organization_id
     *
     * @mbggenerated Wed Jul 07 01:42:13 CST 2021
     */
    public Long getOrganizationId() {
        return organizationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_user.organization_id
     *
     * @param organizationId the value for e_user.organization_id
     *
     * @mbggenerated Wed Jul 07 01:42:13 CST 2021
     */
    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_user.post
     *
     * @return the value of e_user.post
     *
     * @mbggenerated Wed Jul 07 01:42:13 CST 2021
     */
    public String getPost() {
        return post;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_user.post
     *
     * @param post the value for e_user.post
     *
     * @mbggenerated Wed Jul 07 01:42:13 CST 2021
     */
    public void setPost(String post) {
        this.post = post == null ? null : post.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_user.status
     *
     * @return the value of e_user.status
     *
     * @mbggenerated Wed Jul 07 01:42:13 CST 2021
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_user.status
     *
     * @param status the value for e_user.status
     *
     * @mbggenerated Wed Jul 07 01:42:13 CST 2021
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_user.feature
     *
     * @return the value of e_user.feature
     *
     * @mbggenerated Wed Jul 07 01:42:13 CST 2021
     */
    public String getFeature() {
        return feature;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_user.feature
     *
     * @param feature the value for e_user.feature
     *
     * @mbggenerated Wed Jul 07 01:42:13 CST 2021
     */
    public void setFeature(String feature) {
        this.feature = feature == null ? null : feature.trim();
    }
}