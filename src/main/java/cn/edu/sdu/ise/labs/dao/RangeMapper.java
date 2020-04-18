package cn.edu.sdu.ise.labs.dao;

import cn.edu.sdu.ise.labs.model.Range;

public interface RangeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Range record);

    int insertSelective(Range record);

    Range selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Range record);

    int updateByPrimaryKey(Range record);
}