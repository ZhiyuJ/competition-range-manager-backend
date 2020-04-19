package cn.edu.sdu.ise.labs.service.utils;

import cn.edu.sdu.ise.labs.model.Range;
import cn.edu.sdu.ise.labs.utils.FormatUtils;
import cn.edu.sdu.ise.labs.vo.RangeVO;
import org.springframework.beans.BeanUtils;

/**
 * @author 姜治羽
 * @create 2020-04-19 20:20
 */
public class RangeUtils {
    /**
     * 将实体对象转换为VO对象
     *
     * @param range 实体对象
     * @return VO对象
     */
    public static RangeVO convertToVO(Range range) {
        RangeVO rangeVO = new RangeVO();
        BeanUtils.copyProperties(range, rangeVO);
        rangeVO.setCreatedAt(FormatUtils.formatFullDate(range.getCreatedAt()));
        rangeVO.setUpdatedAt(FormatUtils.formatFullDate(range.getUpdatedAt()));
        return rangeVO;
    }
}
