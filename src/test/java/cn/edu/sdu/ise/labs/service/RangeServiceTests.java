package cn.edu.sdu.ise.labs.service;

import cn.edu.sdu.ise.labs.dto.RangeDTO;
import cn.edu.sdu.ise.labs.model.Token;
import cn.edu.sdu.ise.labs.utils.TokenContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void testListByCode() {
        initToken();
    }

    @Test
    public void testGetRange() {


    }

    @Test
    public void testListRange() {

    }

    @Test
    public void testAdd() {
        initToken();
        RangeDTO rangeDTO = new RangeDTO();
        rangeDTO.setRangeName("大操场");
        rangeDTO.setRangeLocation("临西一路");
        rangeDTO.setStatus(1);
        rangeDTO.setDescription("这是一条备注");
        // assert断言，是调试的一部分，并不出现在正式版本中
        assert rangeService.addRange(rangeDTO) != null;

    }

    @Test
    public void testUpdate() {

    }

    @Test
    public void testDelete() {

    }


    private void initToken() {
        Token token = new Token();
        token.setRangeCode("123");
        token.setRangeName("风雨操场");
        token.setStatus(1);
        token.setRangeLocation("风雨大道");
        TokenContextHolder.setToken(token);
    }
}
