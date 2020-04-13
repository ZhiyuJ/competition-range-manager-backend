package cn.edu.sdu.ise.labs.dao;

import cn.edu.sdu.ise.labs.model.Supply;

public interface SupplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Supply record);

    int insertSelective(Supply record);

    Supply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Supply record);

    int updateByPrimaryKey(Supply record);
}