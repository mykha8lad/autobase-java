package com.example.AutoBase.dao.userrole;

import com.example.AutoBase.model.Order;
import com.example.AutoBase.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    @Query("Select ur.role.roleName from UserRole ur where ur.driver.id = ?1")
    List<String> getRoleNames(Integer driverId);
}
