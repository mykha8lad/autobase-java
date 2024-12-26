package com.example.AutoBase.service.busines.roleservice;

import com.example.AutoBase.model.Role;
import java.util.List;
import java.util.Optional;

public interface RoleService {
    void save(Role role);
    int[] saveRolesList(List<Role> roles);
    void update(Role role);
    void delete(Role role);
    List<Role> findAll();
    void deleteAll();

    Optional<Role> findRoleByName(String name);
}
