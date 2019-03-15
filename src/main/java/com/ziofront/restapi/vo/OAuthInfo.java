package com.ziofront.restapi.vo;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
public class OAuthInfo {

    @Column(name = "OAUTH_UID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String uid;

    @Column(name = "OAUTH_CODE")
    private String code;

    @Column(name = "ACCESS_TOKEN")
    @SerializedName("access_token")
    private String accessToken;

    @Column(name = "REFRESH_TOKEN")
    @SerializedName("refresh_token")
    private String refreshToken;

    @Column(name = "TOKEN_TYPE")
    @SerializedName("token_type")
    private String tokenType;

    @Column(name = "EXPIRES")
    @SerializedName("expires_in")
    private String expires;

    @Column(name = "SCOPE")
    @SerializedName("scope")
    private String scope;

    @Column(name = "OAUTH_PROVIDER")
    private String provider;

    @ManyToOne(optional = false)
    @JoinColumn(name = "USER_UID")
    private User user;



}
