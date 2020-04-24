package cn.edu.sdu.ise.labs.service;

import cn.edu.sdu.ise.labs.dto.RangeDTO;
import cn.edu.sdu.ise.labs.dto.RangeQueryDTO;
import cn.edu.sdu.ise.labs.model.Page;
import cn.edu.sdu.ise.labs.vo.RangeVO;

import java.util.List;

/**
 * @author 姜治羽
 * @create 2020-04-12 10:52
 */
public interface RangeService {
    /**
     * 根据检索条件查询场地表(range)，返回结果记录
     *
     * @param rangeCode
     * @return RangeVO
     */
    RangeVO getRange(String rangeCode);

    /**
     * 根据检索条件查询场地表（range），返回结果记录列表
     *
     * @param queryDTO
     * @return
     */
    Page<RangeVO> listRange(RangeQueryDTO queryDTO);

    /**
     * 1、	校验场地称的唯一性，若存在报错“场地名称已经存在”
     * 2、	创建场地记录，场地编码自动生成，将输入的相应字段填入
     *
     * @param rangeDTO
     * @return
     */
    String addRange(RangeDTO rangeDTO);

    /**
     * 1、	根据场地编码查询场地记录，若不存在则报错
     * 2、	校验场地名称的唯一性，若存在报错“场地名称已经存在”
     * 3、	创建场地记录，场地编码自动生成，将输入的相应字段填入
     *
     * @param rangeDTO
     * @return
     */
    String updateRange(RangeDTO rangeDTO);

    /**
     * 1、根据场地编码查询competition_event表中status！=3的记录，如果有记录，则报错“该场地已经被比赛使用，不能删除”
     * 2、根据场地代码，删除场地记录信息
     *
     * @param rangeCodes
     * @return
     */
    int deleteRange(List<String> rangeCodes);


}
