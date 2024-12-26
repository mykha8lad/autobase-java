package com.example.AutoBase.service.busines.userroleservice;

import com.example.AutoBase.model.Order;
import com.example.AutoBase.model.UserRole;

import java.util.List;

public interface UserRoleService {
    void save(UserRole userRole);
    int[] saveUserRolesList(List<UserRole> userRoles);
    void update(UserRole userRole);
    void delete(UserRole userRole);
    List<UserRole> findAll();
    void deleteAll();

    List<String> getRoleNames(Integer driverId);
}
