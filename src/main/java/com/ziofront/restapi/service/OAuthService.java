package com.ziofront.restapi.service;

import com.ziofront.restapi.vo.OAuthInfo;
import com.ziofront.restapi.vo.OAuthLogout;

import java.io.IOException;

public interface OAuthService {

    /**
     *
     * @param code
     * @return
     * @throws IOException
     */
    public OAuthInfo getToken(String code) throws IOException;

    /**
     *
     * @param accessToken
     * @return
     * @throws IOException
     */
    public OAuthLogout logout(String accessToken) throws IOException;
}
