package com.ziofront.restapi.controller;

import com.ziofront.restapi.exception.RestAPIException;
import com.ziofront.restapi.service.OAuthService;
import com.ziofront.restapi.vo.OAuthInfo;
import com.ziofront.restapi.vo.OAuthLogout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;

@RestController
@Slf4j
@RequestMapping("/kakao/oauth")
public class KakaoOAuthController {

    @Autowired
    @Qualifier("KAKAO_OAUTH")
    OAuthService oAuthService;

    @RequestMapping(value = "/receiver")
    @Transactional
    public ResponseEntity kakaoOauth(@RequestParam(name = "code", required = true) String code
            , Model model) throws IOException {

        log.debug("code={}", code);

        OAuthInfo oAuthInfo = oAuthService.getToken(code);
        log.debug("oAuthInfo={}", oAuthInfo);

        model.addAttribute("oAuthInfo", oAuthInfo);

        return ResponseEntity.ok(model);
    }

    @RequestMapping(value = "/logout")
    public ResponseEntity kakaoOauthLogout(@RequestParam(name="access_token", required = true) String accessToken, Model model) throws IOException {

        log.debug("accessToken={}", accessToken);

        OAuthLogout oAuthLogout = oAuthService.logout(accessToken);

        model.addAttribute("oAuthLogout", oAuthLogout);

        return ResponseEntity.ok(model);
    }

    @ExceptionHandler(IOException.class)
    protected ResponseEntity handleException(HttpServletRequest request, Model model, Exception e) {
        log.error("e.getMessage()={}", e.getMessage(), e);

        model.addAttribute("error_message", e.getMessage());

        return ResponseEntity.ok(model);
    }


    @ExceptionHandler(RestAPIException.class)
    protected ResponseEntity handleRestAPIException(HttpServletRequest request, Model model, RestAPIException e) {
        log.error("e.getMessage()={}", e.getMessage(), e);

        model.addAttribute("code", e.getResponseStatusCode());
        model.addAttribute("message", e.getResponseStatusMessage());
        model.addAttribute("error_message", e.getMessage());

        return ResponseEntity.ok(model);
    }
}

