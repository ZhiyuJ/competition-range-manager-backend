package cn.edu.sdu.ise.labs.dao;

import cn.edu.sdu.ise.labs.model.Supply;

/**
 * @author jzhy36
 */
public interface SupplyMapper {
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
    int insert(Supply record);

    /**
     * 根据主键选择记录
     *
     * @param id
     * @return
     */
    Supply selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新记录
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(Supply record);
}