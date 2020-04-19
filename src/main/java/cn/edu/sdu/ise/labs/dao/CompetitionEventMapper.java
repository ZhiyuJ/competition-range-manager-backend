package cn.edu.sdu.ise.labs.dao;

import cn.edu.sdu.ise.labs.model.CompetitionEvent;

/**
 * @author jzhy36
 */
public interface CompetitionEventMapper {
    /**
     * 删除记录
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增记录
     *
     * @param record
     * @return
     */
    int insert(CompetitionEvent record);

    /**
     * 根据主键选择记录
     *
     * @param id
     * @return
     */
    CompetitionEvent selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新记录
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(CompetitionEvent record);
}