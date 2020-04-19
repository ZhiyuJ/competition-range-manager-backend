package cn.edu.sdu.ise.labs.service;

import cn.edu.sdu.ise.labs.dto.RangeDTO;
import cn.edu.sdu.ise.labs.vo.RangeVO;

import java.util.List;

/**
 * @author 姜治羽
 * @create 2020-04-12 10:52
 */
public interface RangeService {
    /**
     * 根据场地编码获取详情
     *
     * @param rangeCode 场地编码
     * @return 场地详情
     */
    List<RangeVO> listByCode(String rangeCode);

    /**
     * 根据参数获取场地列表
     *
     * @param rangeDTO
     * @return
     */
    List<RangeVO> list(RangeDTO rangeDTO);


}
