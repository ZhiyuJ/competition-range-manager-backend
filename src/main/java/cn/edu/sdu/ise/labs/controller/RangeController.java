package cn.edu.sdu.ise.labs.controller;

import cn.edu.sdu.ise.labs.model.ResultContext;
import cn.edu.sdu.ise.labs.service.RangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 姜治羽
 * @create 2020-04-19 20:31
 */
@RestController
@RequestMapping("range")
public class RangeController {
    @Autowired
    private RangeService rangeService;

    @GetMapping("listByCode")
    public ResultContext listByCode(String rangeName) {
        return ResultContext.returnSuccess(rangeService.listByCode(rangeName));
    }
}
