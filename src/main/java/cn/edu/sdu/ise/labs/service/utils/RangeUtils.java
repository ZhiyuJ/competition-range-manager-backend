package cn.edu.sdu.ise.labs.service.utils;

import cn.edu.sdu.ise.labs.dao.CompetitionEventMapper;
import cn.edu.sdu.ise.labs.dao.RangeMapper;
import cn.edu.sdu.ise.labs.dto.RangeDTO;
import cn.edu.sdu.ise.labs.model.Range;
import cn.edu.sdu.ise.labs.utils.FormatUtils;
import cn.edu.sdu.ise.labs.vo.RangeVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;

/**
 * @author 姜治羽
 * @create 2020-04-19 20:20
 */
public class RangeUtils {
    @Autowired
    private RangeMapper rangeExtMapper;

    @Autowired
    private CompetitionEventMapper competitionEventMapper;

    private static RangeMapper rangeMapperNew;

    private static CompetitionEventMapper competitionEventMapperNew;

    public static final int closeStatus = 2;

    @PostConstruct
    public void init() {
        rangeMapperNew = rangeExtMapper;
        competitionEventMapperNew = competitionEventMapper;
    }

    public static String delete(String rangeCode) {
        Integer rangeNum = competitionEventMapperNew.countByRangeCode(rangeCode);
        if (rangeNum > 0) {
            throw new RuntimeException("该场地已经被比赛使用，不能删除");
        }
        rangeMapperNew.deleteByCode(rangeCode);
        return null;
    }

    /**
     * 规范并校验rangeDTO
     *
     * @param rangeDTO
     */
    public static void validateRange(RangeDTO rangeDTO) {
        // 输入参数首先调用trim()方法处理去掉两边的空格或者制表符
        FormatUtils.trimFieldToNull(rangeDTO);
        Assert.notNull(rangeDTO, "场地输入数据不能为空");
        if (rangeDTO.getStatus() == closeStatus) {
            Assert.hasText(rangeDTO.getCloseReason(), "关闭原因不能为空");
        }
    }

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
