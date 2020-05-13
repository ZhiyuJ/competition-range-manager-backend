package cn.edu.sdu.ise.labs.service.impl;

import cn.edu.sdu.ise.labs.constant.PrefixConstant;
import cn.edu.sdu.ise.labs.dao.CompetitionEventMapper;
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
import java.util.stream.Collectors;

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

    @Autowired
    private CompetitionEventMapper competitionEventMapper;

    private static final int openStatus = 1;
    private static final int closeStatus = 2;


    /**
     * 根据检索条件查询场地表（range），返回结果记录列表
     * 实现分页查询
     * 刚进入网站的时候，因为没有任何查询参数，queryDTO是null，于是新建了一个新的对象，然后里面的参数页数和每一页展示的记录数默认设为1和10
     * 其他参数都是null，于是在调用查询方法的时候，有if条件过滤掉为null的查询条件，所以如果全是null，就成了无条件查询
     * 所以刚进入页面会查询到所有数据
     *
     * @param queryDTO
     * @return 分页查询结果
     */
    @Override
    public Page<RangeVO> listRange(RangeQueryDTO queryDTO) {
        //如果请求参数为空，则创建一个请求类
        if (queryDTO == null) {
            queryDTO = new RangeQueryDTO();
        }
        //如果场地名称不为空，则构建模糊查询
        if (queryDTO.getRangeName() != null) {
            queryDTO.setRangeName(FormatUtils.makeFuzzySearchTerm(queryDTO.getRangeName()));
        }
        //如果场地地点不为空，则构建模糊查询
        if (queryDTO.getRangeLocation() != null) {
            queryDTO.setRangeLocation(FormatUtils.makeFuzzySearchTerm(queryDTO.getRangeLocation()));
        }
        Token token = TokenContextHolder.getToken();
        // 查询有多少条满足查询条件的记录
        Integer size = rangeMapper.count(queryDTO);
        //创建一个pageUtils类
        PageUtils pageUtils = new PageUtils(queryDTO.getPage(), queryDTO.getPageSize(), size);
        //创建一个page类
        Page<RangeVO> pageData = new Page<>(pageUtils.getPage(), pageUtils.getPageSize(), pageUtils.getTotal(), new ArrayList<>());
        if (size == 0) {
            //没有查询到记录，返回空数据
            return pageData;
        }
        //调用list方法，查询记录放到一个列表里
        List<Range> rangeList = rangeMapper.list(queryDTO, pageUtils.getOffset(), pageUtils.getLimit());
        for (Range range : rangeList) {
            //使用增强for循环将实体对象转换为VO对象
            RangeVO rangeVO = RangeUtils.convertToVO(range);
            //因为数据库表对应的实体类没有状态描述，但是VO类有状态描述，所以单独根据状态编码，给VO类赋值
            if (rangeVO.getStatus() == openStatus) {
                rangeVO.setStatusDesc("open");
            } else {
                rangeVO.setStatusDesc("close");
            }
            //将数据添加到pageDate里
            pageData.getList().add(rangeVO);
        }
        System.out.println(pageData);
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
        Integer count = rangeMapper.countByRangeName(rangeDTO.getRangeName());
        if (count > 0) {
            //如果有记录数，则说明场地名称已存在，抛出运行时异常
            throw new RuntimeException("场地名称已存在");
        } else {
            // 创建实体对象，用以保存到数据库
            Range range = new Range();
            // 将输入的字段全部复制到实体对象中
            BeanUtils.copyProperties(rangeDTO, range);
            // 自动生成场地编码
            range.setRangeCode(keyMaxValueService.generateBusinessCode(PrefixConstant.RANGE));
            // 将token相关信息填入range对象
            TokenContextHolder.formatInsert(range);
            //创建一个date类型数据，传入当前时间
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
        //也是在校验数据的正确性
        Assert.hasText(rangeDTO.getRangeCode(), "场地编码不能为空");
        //根据场地编码查询场地记录，若不存在则报错
        Range range = rangeMapper.getByCode(rangeDTO.getRangeCode());
        Assert.notNull(range, "未找到场地，编码为：" + rangeDTO.getRangeCode());
        //查询是否有与输入的场地名称相同的记录
        Integer count = rangeMapper.countByRangeName(rangeDTO.getRangeName());
        //如果只是想把关闭状态修改为开启状态，则可以让场地名称不发生改变
        if (rangeDTO.getStatus() == closeStatus) {
            // 将输入的字段全部复制到实体对象中
            BeanUtils.copyProperties(rangeDTO, range);
            //将更新人字段写入到实体对象
            range.setUpdatedBy(token.getTenantCode());
            //将更新时间写入到实体对象
            range.setUpdatedAt(new Date());
            //调用DAO的方法，更新记录
            rangeMapper.updateByPrimaryKey(range);
            return range.getRangeCode();
        } else {
            if (count > 0) {
                throw new RuntimeException("场地名称已经存在");
            } else {
                // 将输入的字段全部复制到实体对象中
                BeanUtils.copyProperties(rangeDTO, range);
                //将更新人字段写入到实体对象
                range.setUpdatedBy(token.getTenantCode());
                //将更新时间写入到实体对象
                range.setUpdatedAt(new Date());
                //调用DAO的方法，更新记录
                rangeMapper.updateByPrimaryKey(range);
                return range.getRangeCode();
            }
        }
    }

    /**
     * 1、根据场地编码查询competition_event表中status！=3的记录，如果有记录，则报错“该场地已经被比赛使用，不能删除”
     * 2、根据场地编码列表，删除场地记录信息
     *
     * @param rangeCodes
     * @return 记录数
     */
    @Override
    public int deleteRange(List<String> rangeCodes) {
        //校验数据正确性
        Assert.notEmpty(rangeCodes, "场地编码列表不能为空");
        //获得前端传来的token信息
        Token token = TokenContextHolder.getToken();
        //定义一个int类型的变量，用来记录删除了多少条记录
        int deleteNum = 0;
        //增强for循环，遍历list集合，代码书写简单
        for (String rangeCode : rangeCodes) {
            //查询是否有competition_event表中status！=3的记录，如果有则不能删除；如果没有则可以删除
            Integer rangeNum = competitionEventMapper.countByRangeCode(rangeCode);
            if (rangeNum > 0) {
                throw new RuntimeException("该场地已经被比赛使用，不能删除");
            }
            List<Range> rangeList = rangeMapper.listByCode(rangeCode);
            if (rangeList.size() > 0) {
                //调用DAO方法，根据场地编码删除该记录
                rangeMapper.deleteByCode(rangeCode);
                //删除了的记录条数加1
                deleteNum = deleteNum + 1;
            } else {
                //如果没有这个场地编码，则报错
                throw new RuntimeException("该场地编码: " + rangeCode + " 不存在，请重新输入");
            }
        }
        return deleteNum;
    }

    /**
     * 根据场地编码获取下拉列表
     *
     * @param rangeCode 场地编码（模糊匹配）
     * @return 场地详情
     */
    @Override
    public List<RangeVO> listByCode(String rangeCode) {
        Token token = TokenContextHolder.getToken();
        rangeCode = FormatUtils.makeFuzzySearchTerm(rangeCode);
        List<Range> rangeList = rangeMapper.listByCode(rangeCode);
        return rangeList.stream()
                .map(item -> RangeUtils.convertToVO(item))
                .collect(Collectors.toList());
    }
}
