package com.ziofront.restapi.repository;

import com.ziofront.restapi.vo.OAuthInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OAuthInfoRepository extends JpaRepository<OAuthInfo, String> {
}
