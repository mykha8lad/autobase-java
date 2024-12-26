package com.example.AutoBase.service.busines.roleservice;

import com.example.AutoBase.dao.role.RoleRepository;
import com.example.AutoBase.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public int[] saveRolesList(List<Role> roles) {
        roleRepository.saveAll(roles);
        return new int[0];
    }

    @Override
    public void update(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void delete(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public void deleteAll() {
        roleRepository.deleteAll();
    }

    @Override
    public Optional<Role> findRoleByName(String name) {
        return roleRepository.findRoleByRoleName(name);
    }
}
