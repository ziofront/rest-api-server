package com.ziofront.restapi.repository;

import com.ziofront.restapi.vo.OAuthInfo;
import com.ziofront.restapi.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OAuthInfoRepository oAuthInfoRepository;


    @Test
    public void test1_add() {


        User user = userRepository.save(User.builder().name("Jiho").email("ziofront@gmail.com").build());
        log.debug("user.getUid()={}", user.getUid());

        assertTrue(user != null);

        OAuthInfo oAuthInfo = oAuthInfoRepository.save(OAuthInfo.builder().accessToken("123123123123").expires("11111").refreshToken("087987687587").user(user).build());
        log.debug("oAuthInfo.getUid()={}", oAuthInfo.getUid());
        assertTrue(oAuthInfo != null);


    }

    @Test
    @Transactional
    public void test2_findAll() {
//        User user = userRepository.getOne("1");
        List<User> userList = userRepository.findAll();
        log.debug("userList={}", userList);
        assertTrue(userList != null);


        List<OAuthInfo> oAuthInfoList = oAuthInfoRepository.findAll();
        log.debug("oAuthInfoList={}", oAuthInfoList);
        assertTrue(oAuthInfoList != null);
    }


}
