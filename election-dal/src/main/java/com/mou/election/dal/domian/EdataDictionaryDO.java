package com.mou.election.dal.domian;

import java.util.Date;

public class EdataDictionaryDO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_data_dictionary.id
     *
     * @mbggenerated Sat Jul 03 07:13:55 CST 2021
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_data_dictionary.gmt_create
     *
     * @mbggenerated Sat Jul 03 07:13:55 CST 2021
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_data_dictionary.gmt_modified
     *
     * @mbggenerated Sat Jul 03 07:13:55 CST 2021
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_data_dictionary.data_type
     *
     * @mbggenerated Sat Jul 03 07:13:55 CST 2021
     */
    private String dataType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_data_dictionary.data_code
     *
     * @mbggenerated Sat Jul 03 07:13:55 CST 2021
     */
    private String dataCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_data_dictionary.data_desc
     *
     * @mbggenerated Sat Jul 03 07:13:55 CST 2021
     */
    private String dataDesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column e_data_dictionary.data_feature
     *
     * @mbggenerated Sat Jul 03 07:13:55 CST 2021
     */
    private String dataFeature;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_data_dictionary.id
     *
     * @return the value of e_data_dictionary.id
     *
     * @mbggenerated Sat Jul 03 07:13:55 CST 2021
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_data_dictionary.id
     *
     * @param id the value for e_data_dictionary.id
     *
     * @mbggenerated Sat Jul 03 07:13:55 CST 2021
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_data_dictionary.gmt_create
     *
     * @return the value of e_data_dictionary.gmt_create
     *
     * @mbggenerated Sat Jul 03 07:13:55 CST 2021
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_data_dictionary.gmt_create
     *
     * @param gmtCreate the value for e_data_dictionary.gmt_create
     *
     * @mbggenerated Sat Jul 03 07:13:55 CST 2021
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_data_dictionary.gmt_modified
     *
     * @return the value of e_data_dictionary.gmt_modified
     *
     * @mbggenerated Sat Jul 03 07:13:55 CST 2021
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_data_dictionary.gmt_modified
     *
     * @param gmtModified the value for e_data_dictionary.gmt_modified
     *
     * @mbggenerated Sat Jul 03 07:13:55 CST 2021
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_data_dictionary.data_type
     *
     * @return the value of e_data_dictionary.data_type
     *
     * @mbggenerated Sat Jul 03 07:13:55 CST 2021
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_data_dictionary.data_type
     *
     * @param dataType the value for e_data_dictionary.data_type
     *
     * @mbggenerated Sat Jul 03 07:13:55 CST 2021
     */
    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_data_dictionary.data_code
     *
     * @return the value of e_data_dictionary.data_code
     *
     * @mbggenerated Sat Jul 03 07:13:55 CST 2021
     */
    public String getDataCode() {
        return dataCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_data_dictionary.data_code
     *
     * @param dataCode the value for e_data_dictionary.data_code
     *
     * @mbggenerated Sat Jul 03 07:13:55 CST 2021
     */
    public void setDataCode(String dataCode) {
        this.dataCode = dataCode == null ? null : dataCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_data_dictionary.data_desc
     *
     * @return the value of e_data_dictionary.data_desc
     *
     * @mbggenerated Sat Jul 03 07:13:55 CST 2021
     */
    public String getDataDesc() {
        return dataDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_data_dictionary.data_desc
     *
     * @param dataDesc the value for e_data_dictionary.data_desc
     *
     * @mbggenerated Sat Jul 03 07:13:55 CST 2021
     */
    public void setDataDesc(String dataDesc) {
        this.dataDesc = dataDesc == null ? null : dataDesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column e_data_dictionary.data_feature
     *
     * @return the value of e_data_dictionary.data_feature
     *
     * @mbggenerated Sat Jul 03 07:13:55 CST 2021
     */
    public String getDataFeature() {
        return dataFeature;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column e_data_dictionary.data_feature
     *
     * @param dataFeature the value for e_data_dictionary.data_feature
     *
     * @mbggenerated Sat Jul 03 07:13:55 CST 2021
     */
    public void setDataFeature(String dataFeature) {
        this.dataFeature = dataFeature == null ? null : dataFeature.trim();
    }
}