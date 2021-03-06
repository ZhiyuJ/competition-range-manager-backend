package cn.edu.sdu.ise.labs.controller;

import cn.edu.sdu.ise.labs.dto.RangeDTO;
import cn.edu.sdu.ise.labs.dto.RangeQueryDTO;
import cn.edu.sdu.ise.labs.model.ResultContext;
import cn.edu.sdu.ise.labs.service.RangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 姜治羽
 * @create 2020-04-19 20:31
 */
@RestController
@RequestMapping("range")
public class RangeController {
    /**
     * 注入rangeService服务对象
     */
    @Autowired
    private RangeService rangeService;

    @GetMapping("get")
    public ResultContext listByCode(String rangeCode) {
        return ResultContext.returnSuccess(rangeService.listByCode(rangeCode));
    }

    @PostMapping("list")
    /**
     * 调用分页查询接口
     */
    public ResultContext listRange(@RequestBody RangeQueryDTO rangeQueryDTO) {
        return ResultContext.returnSuccess(rangeService.listRange(rangeQueryDTO));
    }

    @PostMapping("add")
    /**
     * 调用增加接口
     */
    public ResultContext addRange(@RequestBody RangeDTO rangeDTO) {
        return ResultContext.returnSuccess(rangeService.addRange(rangeDTO));
    }

    @PostMapping("update")
    /**
     * 调用修改接口
     */
    public ResultContext updateRange(@RequestBody RangeDTO rangeDTO) {
        return ResultContext.returnSuccess(rangeService.updateRange(rangeDTO));
    }

    @PostMapping("delete")
    /**
     * 调用删除接口
     */
    public ResultContext deleteRange(@RequestBody List<String> rangeCodes) {
        return ResultContext.returnSuccess(rangeService.deleteRange(rangeCodes));
    }

}
