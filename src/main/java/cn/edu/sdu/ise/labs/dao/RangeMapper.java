package cn.edu.sdu.ise.labs.dao;

import cn.edu.sdu.ise.labs.dto.RangeQueryDTO;
import cn.edu.sdu.ise.labs.model.Range;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jzhy36
 */
@Component(value = "rangeMapper")
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
     * 在修改场地接口实现的时候，进行修改记录
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(Range record);

    /**
     * 根据场地编码获取场地详情
     * 用于在修改场地时，检验是否有这个场地编码
     *
     * @param rangeCode
     * @return
     */
    Range getByCode(@Param("rangeCode") String rangeCode);

    /**
     * 根据查询条件获取场地列表
     * 分页查询接口使用
     *
     * @param queryDTO 查询条件
     * @param offset   开始位置
     * @param limit    记录数量
     * @return 部门列表
     */
    List<Range> list(
            @Param("queryDTO") RangeQueryDTO queryDTO,
            @Param("offset") Integer offset,
            @Param("limit") Integer limit);


    /**
     * 根据查询条件获取命中个数
     * 在分页查询接口里使用
     *
     * @param queryDTO
     * @return 命中个数
     */
    Integer count(@Param("queryDTO") RangeQueryDTO queryDTO);


    /**
     * 根据rangeCode删除记录，并返回删除记录数
     * 在删除场地接口里，真正实现对数据的删除
     *
     * @param rangeCode
     * @return
     */
    int deleteByCode(
            @Param("rangeCode") String rangeCode);

    /**
     * 根据场地编码获得场地详情
     *
     * @param rangeCode
     * @return
     */
    List<Range> listByCode(
            @Param("rangeCode") String rangeCode);


    /**
     * 根据场地名称获取记录条数
     * 用于新建场地时，校验场地名称唯一性
     *
     * @param rangeName 场地名称
     * @return 记录条数
     */
    Integer countByRangeName(
            @Param("rangeName") String rangeName);


}