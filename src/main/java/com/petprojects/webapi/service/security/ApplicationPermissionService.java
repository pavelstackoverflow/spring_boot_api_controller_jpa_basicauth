package com.petprojects.webapi.service.security;

import com.petprojects.webapi.entity.security.ApplicationUser;
import com.petprojects.webapi.entity.security.ApplicationUserPermission;
import com.petprojects.webapi.repository.security.ApplicationPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationPermissionService {

    @Autowired
    ApplicationPermissionRepository applicationPermissionRepository;

    public void addPermission(ApplicationUserPermission applicationUserPermission) {
        applicationPermissionRepository.saveAndFlush(applicationUserPermission);
    }

    public void deletePermission(Long permissionId) {
        applicationPermissionRepository.deleteById(permissionId);
    }
}
