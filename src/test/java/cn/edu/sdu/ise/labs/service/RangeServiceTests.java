package cn.edu.sdu.ise.labs.service;

import cn.edu.sdu.ise.labs.dto.RangeDTO;
import cn.edu.sdu.ise.labs.dto.RangeQueryDTO;
import cn.edu.sdu.ise.labs.model.Page;
import cn.edu.sdu.ise.labs.model.Token;
import cn.edu.sdu.ise.labs.utils.TokenContextHolder;
import cn.edu.sdu.ise.labs.vo.RangeVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 姜治羽
 * @create 2020-04-19 20:45
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("cn.edu.sdu.ise.labs.dao")
@Slf4j
public class RangeServiceTests {
    @Autowired
    private RangeService rangeService;


    @Test
    public void testGetRange() {
        initToken();
        String rangeCode = "123";
//        List<RangeVO> list = rangeService.getRange(rangeCode);
//        assert list.size() != 0;

    }

    @Test
    public void testListRange() {
        initToken();
        RangeQueryDTO queryDTO = new RangeQueryDTO();
        queryDTO.setPage(1);
        queryDTO.setPageSize(10);
        queryDTO.setRangeName("乒乓球");
        queryDTO.setRangeLocation("山东大学威海");
        queryDTO.setStatus(2);
        Page<RangeVO> pageDate = rangeService.listRange(queryDTO);
        assert pageDate.getList().size() > 0;

    }

    @Test
    public void testAdd() {
        initToken();
        RangeDTO rangeDTO = new RangeDTO();
        rangeDTO.setRangeName("乒乓球场4");
        rangeDTO.setRangeLocation("山东大学青岛");
        rangeDTO.setStatus(1);
//        rangeDTO.setCloseReason("我就是想关闭");
        rangeDTO.setDescription("这是一条备注");
        // assert断言，是调试的一部分，并不出现在正式版本中
        assert rangeService.addRange(rangeDTO) != null;

    }


    @Test
    public void testUpdate() {
        initToken();
        RangeDTO rangeDTO = new RangeDTO();
        rangeDTO.setRangeCode("RG2004270015");
        rangeDTO.setRangeName("乒乓球场5");
        rangeDTO.setRangeLocation("山东大学青岛");
        rangeDTO.setStatus(2);
        rangeDTO.setCloseReason("因为下雨了");
        rangeDTO.setDescription("这是测试更新接口的备注");
        assert rangeService.updateRange(rangeDTO) != null;

    }

    @Test
    public void testDelete() {
        initToken();
        List<String> rangeCodes = new ArrayList<>();
//        rangeCodes.add("RG2004260003");
        rangeCodes.add("RG2004270012");
        rangeCodes.add("RG2004270015");
//        int deleteNum = rangeService.deleteRange(rangeCodes);

        int deleteNum = rangeService.deleteRange(rangeCodes);
        System.out.println(deleteNum);
        assert deleteNum > 0;
    }

    @Test
    public void testListByCode() {
        initToken();
        List<String> codeList = new ArrayList<>();
        codeList.add("123");
        List<RangeVO> list = rangeService.listByCode("RG2004270013");
        assert list.size() > 0;
    }

    private void initToken() {
        Token token = new Token();
        token.setTenantCode("001");
        token.setTeacherCode("TE001");
        TokenContextHolder.setToken(token);
    }
}
