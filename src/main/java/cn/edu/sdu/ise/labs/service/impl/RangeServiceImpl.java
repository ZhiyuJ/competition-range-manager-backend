package cn.edu.sdu.ise.labs.service.impl;

import cn.edu.sdu.ise.labs.constant.PrefixConstant;
import cn.edu.sdu.ise.labs.dao.RangeMapper;
import cn.edu.sdu.ise.labs.dto.RangeDTO;
import cn.edu.sdu.ise.labs.dto.RangeQueryDTO;
import cn.edu.sdu.ise.labs.model.Page;
import cn.edu.sdu.ise.labs.model.Range;
import cn.edu.sdu.ise.labs.model.Token;
import cn.edu.sdu.ise.labs.service.KeyMaxValueService;
import cn.edu.sdu.ise.labs.service.RangeService;
import cn.edu.sdu.ise.labs.service.utils.RangeUtils;
import cn.edu.sdu.ise.labs.utils.FormatUtils;
import cn.edu.sdu.ise.labs.utils.PageUtils;
import cn.edu.sdu.ise.labs.utils.TokenContextHolder;
import cn.edu.sdu.ise.labs.vo.RangeVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 实现接口
 *
 * @author 姜治羽
 * @create 2020-04-19 19:55
 */
@Service
public class RangeServiceImpl implements RangeService {
    @Autowired
    private RangeMapper rangeMapper;

    @Autowired
    private KeyMaxValueService keyMaxValueService;

    /**
     * 根据检索条件查询场地表(range)，返回结果记录
     *
     * @param rangeCode
     * @return RangeVO
     */
    @Override
    public RangeVO getRange(String rangeCode) {
        // 对请求的合法性进行判断
        if (rangeCode == null) {
            throw new RuntimeException("请输入场地编码");
        }
        //到这里可以开始实现功能
        Range range = rangeMapper.getByCode(rangeCode);
        // 返回的是range表中符合条件的全部字段，但返回对象必须是一个VO类，所以可以使用工具类里的转换方法
        return RangeUtils.convertToVO(range);
    }

    /**
     * 根据检索条件查询场地表（range），返回结果记录列表
     *
     * @param queryDTO
     * @return 分页查询结果
     */
    @Override
    public Page<RangeVO> listRange(RangeQueryDTO queryDTO) {
        if (queryDTO == null) {
            queryDTO = new RangeQueryDTO();
        }
        if (queryDTO.getRangeName() != null) {
            queryDTO.setRangeName(FormatUtils.makeFuzzySearchTerm(queryDTO.getRangeName()));
        }
        if (queryDTO.getRangeLocation() != null) {
            queryDTO.setRangeLocation(FormatUtils.makeFuzzySearchTerm(queryDTO.getRangeLocation()));
        }
        // 查询有多少条满足查询条件的记录
        Integer size = rangeMapper.count(queryDTO);
        PageUtils pageUtils = new PageUtils(queryDTO.getPage(), queryDTO.getPageSize(), size);
        Page<RangeVO> pageData = new Page<>(pageUtils.getPage(), pageUtils.getPageSize(), pageUtils.getTotal(), new ArrayList<>());
        if (size == 0) {
            //没有查询到记录，返回空数据
            return pageData;
        }
        List<Range> rangeList = rangeMapper.list(queryDTO, pageUtils.getOffset(), pageUtils.getLimit());
        for (Range range : rangeList) {
            pageData.getList().add(RangeUtils.convertToVO(range));
        }
        return pageData;
    }

    /**
     * 1、	校验场地称的唯一性，若存在报错“场地名称已经存在”
     * 2、	创建场地记录，场地编码自动生成，将输入的相应字段填入
     *
     * @param rangeDTO
     * @return 场地编码
     */
    @Override
    public String addRange(RangeDTO rangeDTO) {
        // 校验输入数据正确性
        RangeUtils.validateRange(rangeDTO);
        // 唯一性检验，先查询是否有记录数
        List<Range> rangeList = rangeMapper.listByRangeName(rangeDTO.getRangeName());
        if (rangeList.size() > 0) {
            //这里是否可用Assert代替（后续测试一下）
            throw new RuntimeException("场地名称已经存在");
        } else {
            // 创建实体对象，用以保存到数据库
            Range range = new Range();
            // 将输入的字段全部复制到实体对象中
            BeanUtils.copyProperties(rangeDTO, range);
            // 自动生成场地编码
            range.setRangeCode(keyMaxValueService.generateBusinessCode(PrefixConstant.RANGE));
            // 将token相关信息填入range对象
            TokenContextHolder.formatInsert(range);
            Date date = new Date();
            range.setCreatedAt(date);
            range.setUpdatedAt(date);
            // 调用DAO方法保存到数据库表
            rangeMapper.insert(range);
            // 返回场地编码
            return range.getRangeCode();
        }
    }

    /**
     * 1、	根据场地编码查询场地记录，若不存在则报错
     * 2、	校验场地名称的唯一性，若存在报错“场地名称已经存在”
     * 3、	创建场地记录，场地编码自动生成，将输入的相应字段填入
     *
     * @param rangeDTO
     * @return 场地编码
     */
    @Override
    public String updateRange(RangeDTO rangeDTO) {
        Token token = TokenContextHolder.getToken();
        // 校验输入数据的正确性
        RangeUtils.validateRange(rangeDTO);
        Assert.hasText(rangeDTO.getRangeCode(), "场地编码不能为空");
        Range range = rangeMapper.getByCode(rangeDTO.getRangeCode());
        Assert.notNull(range, "未找到场地，编码为：" + rangeDTO.getRangeCode());

        BeanUtils.copyProperties(rangeDTO, range);
        range.setUpdatedBy(token.getTenantCode());
        range.setUpdatedAt(new Date());
        rangeMapper.updateByPrimaryKey(range);
        return range.getRangeCode();
    }

    /**
     * 1、根据场地编码查询competition_event表中status！=3的记录，如果有记录，则报错“该场地已经被比赛使用，不能删除”
     * 2、根据场地代码，删除场地记录信息
     *
     * @param rangeCode
     * @return 记录数
     */
    @Override
    public int deleteRange(List<String> rangeCodes) {
        Assert.notEmpty(rangeCodes, "场地编码列表不能为空");
        Token token = TokenContextHolder.getToken();
        /*
        生成流：rangeCodes.stream()
        中间操作：.map(RangeUtils::delete)
        终端操作：.count()
         */
        return (int) rangeCodes.stream()
                .map(RangeUtils::delete)
                .count();
    }
}
