package com.example.AutoBase.service.securityservice;

import com.example.AutoBase.dto.UserInfoDto;
import com.example.AutoBase.model.Driver;
import com.example.AutoBase.service.busines.driverservice.DriverService;
import com.example.AutoBase.service.busines.userroleservice.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private UserRoleService userRoleService;


    @Override
    public String findLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }
        return "";
    }

    @Override
    public void autoLogin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }

    @Override
    public Optional<UserInfoDto> getCurrentUserInfo() {
        String userName = findLoggedInUsername();  // имя залогиненного пользователя

        Optional<Driver> optionalDriver = driverService.findByName(userName);
        if (optionalDriver.isEmpty()) {
            return Optional.of(new UserInfoDto());
        }

        Driver driver = optionalDriver.get();
        List<String> roles = userRoleService.getRoleNames(driver.getId());  // список ролей пользователя

        UserInfoDto userInfoDto = new UserInfoDto(
                driver.getName(),
                driver.getNumTel(),
                driver.getTotalSum(),
                roles
        );

        return Optional.of(userInfoDto);
    }

    @Override
    public int getCurrentUserId() {
        String username = findLoggedInUsername();
        return driverService.findByName(username)
                .map(Driver::getId)
                .orElse(-1);
    }
}
