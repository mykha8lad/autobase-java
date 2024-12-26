package com.example.AutoBase.service.registrationuserservice;

import com.example.AutoBase.model.Driver;
import com.example.AutoBase.model.Role;
import com.example.AutoBase.model.UserRole;
import com.example.AutoBase.service.busines.driverservice.DriverService;
import com.example.AutoBase.service.busines.roleservice.RoleService;
import com.example.AutoBase.service.busines.userroleservice.UserRoleService;
import com.example.AutoBase.service.securityservice.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationUserServiceImpl implements RegistrationUserService {

    @Autowired
    private DriverService driverService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public Optional<String> registerUser(Driver driver) {
        // если с таким именем пользователь есть
        Driver findDriver = driverService.findByName(driver.getName()).orElse(null);
        if (findDriver != null) {
            return Optional.of("A user with the same name already exists");
        }

        // если с таким номером телефона есть
        findDriver = driverService.findByNumTel(driver.getNumTel()).orElse(null);
        if (findDriver != null) {
            return Optional.of("A user with the same num tel already exists");
        }

        // шифруем пароль и добавляем пользователя
        String password = driver.getEncryptedPassword();
        String encPassword = passwordEncoder.encode(password);
        driver.setEncryptedPassword(encPassword);
        Driver addDriver = driverService.save(driver);

        // добавляем роль
        Role roleUser = roleService.findRoleByName("ROLE_USER").orElse(null);
        if (roleUser == null) {
            throw new RuntimeException("Role not found...");
        }

        // связка роли и пользователя
        UserRole userRole = new UserRole(0, addDriver, roleUser);
        userRoleService.save(userRole);

        // авторизация пользователя после регистрации
        securityService.autoLogin(addDriver.getName(), password);

        return Optional.empty();
    }
}
