package com.ziofront.restapi.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KakaoOAuthServiceTest {

    @Autowired
    @Qualifier("KAKAO_OAUTH")
    OAuthService oAuthService;


    @Test
    public void test1() {

        try {
            oAuthService.getToken("Y96-len4Gbz5rhkT3hFPnnOlchEChD0XsyLCwcPAryItkr0tNGHoY0nPaKa9Dqsl5CDXOwo9dZsAAAFpf7FgWg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2_logout() {
        try {
            oAuthService.logout("TuTyJmlSQFwzZ8ugzQSLb1GXzbet6FjEM99SqAo9dNsAAAFpgC2tYg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
