package com.ziofront.restapi.service.external;

import com.ziofront.restapi.exception.RestAPIException;
import com.ziofront.restapi.service.OAuthService;
import com.ziofront.restapi.vo.OAuthInfo;
import com.ziofront.restapi.vo.OAuthLogout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service("KAKAO_OAUTH")
@Slf4j
public class KakaoOAuthService implements OAuthService {

    public static final String REST_API_KEY = "83dcd479fa79c467b10e16cf70a523ab";

    /**
     *
     */
    public interface KakaoOAuthClient {

        public static final String BASE_URL = "https://kauth.kakao.com/oauth/";

        @POST("token")
        Call<OAuthInfo> token(@QueryMap Map<String, String> queryMap);
    }

    /**
     *
     */
    public interface KakaoAPIClient {

        public static final String BASE_URL = "https://kapi.kakao.com/";

        @POST("v1/user/logout")
        Call<OAuthLogout> logout(@Header("Authorization") String accessToken);
    }

    @Autowired
    KakaoOAuthClient kakaoOAuthClient;

    @Bean
    public KakaoOAuthClient kakaoOAuthClient() {
        log.debug("========== KakaoOAuthClient ==========");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(KakaoOAuthClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(KakaoOAuthClient.class);
    }

    @Autowired
    KakaoAPIClient kakaoAPIClient;

    @Bean
    public KakaoAPIClient kakaoAPIClient() {

        log.debug("========== KakaoAPIClient ==========");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(KakaoAPIClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(KakaoAPIClient.class);
    }


    /**
     * @param code
     * @return
     */
    @Override
    public OAuthInfo getToken(String code) throws IOException {

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("grant_type", "authorization_code");
        queryMap.put("client_id", REST_API_KEY);
        queryMap.put("code", code);

        Response<OAuthInfo> response = kakaoOAuthClient.token(queryMap).execute();
        boolean isSuccessful = response.isSuccessful();

        log.debug("response={}", response);
        log.debug("isSuccessful={}", isSuccessful);

        if (isSuccessful == false) {
            throw new RestAPIException(response.code(), response.message());
        }

        OAuthInfo oAuthInfo = response.body();

        Optional.ofNullable(oAuthInfo).ifPresent(oauth -> {
            oauth.setCode(code);
            oauth.setProvider("KAKAO");
        });

        log.debug("oAuthInfo={}", oAuthInfo);

        return oAuthInfo;

    }

    /**
     * @param accessToken
     * @return
     */
    @Override
    public OAuthLogout logout(String accessToken) throws IOException {

        Call<OAuthLogout> call = kakaoAPIClient.logout("Bearer " + accessToken);
        log.debug("call.request()={}", call.request());
        log.debug("call.request().method()={}", call.request().method());
        log.debug("call.request().headers()={}", call.request().headers());
        log.debug("call={}", call);

        Response<OAuthLogout> response = call.execute();
        boolean isSuccessful = response.isSuccessful();
        log.debug("response={}", response);
        log.debug("isSuccessful={}", isSuccessful);

        if (isSuccessful == false) {
            throw new RestAPIException(response.code(), response.message());
        }

        OAuthLogout result = response.body();

        log.debug("result={}", result);

        return result;

    }

}
