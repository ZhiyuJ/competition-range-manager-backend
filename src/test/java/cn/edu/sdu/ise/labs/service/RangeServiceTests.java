package cn.edu.sdu.ise.labs.service;

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

    private void initToken() {
        Token token = new Token();
        token.setRangeCode("123");
        token.setTeacherCode("TE00000001");
        TokenContextHolder.setToken(token);
    }
}
