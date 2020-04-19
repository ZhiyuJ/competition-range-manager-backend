package cn.edu.sdu.ise.labs.service.impl;

import cn.edu.sdu.ise.labs.dao.RangeMapper;
import cn.edu.sdu.ise.labs.dto.RangeDTO;
import cn.edu.sdu.ise.labs.model.Range;
import cn.edu.sdu.ise.labs.model.Token;
import cn.edu.sdu.ise.labs.service.RangeService;
import cn.edu.sdu.ise.labs.service.utils.RangeUtils;
import cn.edu.sdu.ise.labs.utils.TokenContextHolder;
import cn.edu.sdu.ise.labs.vo.RangeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 姜治羽
 * @create 2020-04-19 19:55
 */
@Service
public class RangeServiceImpl implements RangeService {
    /**
     * 注入rangeMapper,mybatis采用自动代理技术自动生成一个类
     */
    @Autowired
    private RangeMapper rangeMapper;

    /**
     * 根据场地编码获取详情
     *
     * @param rangeCode 场地编码
     * @return 场地详情
     */
    @Override
    public List<RangeVO> listByCode(String rangeCode) {
        Token token = TokenContextHolder.getToken();
        List<Range> rangeList = rangeMapper.listByCode(token.getRangeCode());
        return rangeList.stream()
                .map(item -> RangeUtils.convertToVO(item))
                .collect(Collectors.toList());
    }

    /**
     * 根据参数获取场地列表
     *
     * @param rangeDTO
     * @return
     */
    @Override
    public List<RangeVO> list(RangeDTO rangeDTO) {
        return null;
    }
}
