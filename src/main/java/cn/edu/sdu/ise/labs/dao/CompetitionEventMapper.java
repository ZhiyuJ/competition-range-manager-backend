package cn.edu.sdu.ise.labs.dao;

import cn.edu.sdu.ise.labs.model.CompetitionEvent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author jzhy36
 */
@Component(value = "competitionEventMapper")
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

    /**
     * 根据场地编码查询competition_event表中status！=3的记录条数
     * 用来判断是否满足删除条件
     *
     * @param rangeCode
     * @return
     */
    Integer countByRangeCode(@Param("rangeCode") String rangeCode);
}