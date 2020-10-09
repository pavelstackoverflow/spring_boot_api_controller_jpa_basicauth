package com.petprojects.webapi.repository.security;

import com.petprojects.webapi.entity.security.ApplicationUserPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationPermissionRepository extends JpaRepository<ApplicationUserPermission, Long> {

}
