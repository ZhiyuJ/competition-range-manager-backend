package cn.edu.sdu.ise.labs.dao;

import cn.edu.sdu.ise.labs.model.Range;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jzhy36
 */
public interface RangeMapper {
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
    int insert(Range record);

    /**
     * 根据主键选择记录
     *
     * @param id
     * @return
     */
    Range selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新记录
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(Range record);

    /**
     * 根据场地编码获取场地详情
     *
     * @param rangeCode 场地代码
     * @return 场地详情
     */
    List<Range> listByCode(
            @Param("rangeCode") String rangeCode);
}