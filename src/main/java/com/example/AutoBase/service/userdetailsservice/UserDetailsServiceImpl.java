package com.example.AutoBase.service.userdetailsservice;

import com.example.AutoBase.model.Driver;
import com.example.AutoBase.service.busines.driverservice.DriverService;
import com.example.AutoBase.service.busines.userroleservice.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private DriverService driverService;

    @Autowired
    private UserRoleService userRoleService;


    // Метод вызовется при сабмите логин формы
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // находим пользователя по имени
        Driver driver = driverService.findByName(userName).orElse(null);

        if (driver == null) {
            throw new UsernameNotFoundException("User '" + userName + "' was not found in the database...");
        }

        // установка ролей для данного пользователя
        List<String> roleNames = userRoleService.getRoleNames(driver.getId());
        List<GrantedAuthority> grantList = new ArrayList<>();
        if (roleNames != null) {
            grantList = roleNames.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }

        // возвращаем объект внутреннего Spring User
        return new User(driver.getName(), driver.getEncryptedPassword(), grantList);
    }
}
