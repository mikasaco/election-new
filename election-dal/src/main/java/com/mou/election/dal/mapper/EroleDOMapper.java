package com.mou.election.dal.mapper;

import com.mou.election.dal.domian.EroleDO;
import com.mou.election.dal.domian.EroleDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EroleDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table e_role
     *
     * @mbggenerated Wed Jul 07 02:05:19 CST 2021
     */
    int countByExample(EroleDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table e_role
     *
     * @mbggenerated Wed Jul 07 02:05:19 CST 2021
     */
    int deleteByExample(EroleDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table e_role
     *
     * @mbggenerated Wed Jul 07 02:05:19 CST 2021
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table e_role
     *
     * @mbggenerated Wed Jul 07 02:05:19 CST 2021
     */
    int insert(EroleDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table e_role
     *
     * @mbggenerated Wed Jul 07 02:05:19 CST 2021
     */
    int insertSelective(EroleDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table e_role
     *
     * @mbggenerated Wed Jul 07 02:05:19 CST 2021
     */
    List<EroleDO> selectByExample(EroleDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table e_role
     *
     * @mbggenerated Wed Jul 07 02:05:19 CST 2021
     */
    EroleDO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table e_role
     *
     * @mbggenerated Wed Jul 07 02:05:19 CST 2021
     */
    int updateByExampleSelective(@Param("record") EroleDO record, @Param("example") EroleDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table e_role
     *
     * @mbggenerated Wed Jul 07 02:05:19 CST 2021
     */
    int updateByExample(@Param("record") EroleDO record, @Param("example") EroleDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table e_role
     *
     * @mbggenerated Wed Jul 07 02:05:19 CST 2021
     */
    int updateByPrimaryKeySelective(EroleDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table e_role
     *
     * @mbggenerated Wed Jul 07 02:05:19 CST 2021
     */
    int updateByPrimaryKey(EroleDO record);
}