package com.example.AutoBase.service.securityservice;

import com.example.AutoBase.dto.UserInfoDto;

import java.util.Optional;

public interface SecurityService {
    String findLoggedInUsername();
    void autoLogin(String username, String password);
    Optional<UserInfoDto> getCurrentUserInfo();
    int getCurrentUserId();
}
