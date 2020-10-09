package com.petprojects.webapi.repository.security;

import com.petprojects.webapi.entity.security.ApiLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiLogRepository extends JpaRepository<ApiLog, Long> {

}
