package com.petprojects.webapi.service.security;

import com.petprojects.webapi.entity.security.ApiLog;
import com.petprojects.webapi.repository.CourseRepository;
import com.petprojects.webapi.repository.security.ApiLogRepository;
import com.petprojects.webapi.utils.QueryParameterMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApiLogService {

    @Autowired
    ApiLogRepository apiLogRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<ApiLog> getApiLog(Integer code, String connectionDetails, String error, String information,
                                  Integer changeAction, Integer errorResult) {
        String filterString = filterParams(code, connectionDetails, error, information, changeAction, errorResult);
        TypedQuery<ApiLog> query = entityManager.createQuery("SELECT a from ApiLog a " +
                filterString, ApiLog.class)
                .setParameter("code", code)
                .setParameter("connectionDetails", connectionDetails)
        .setParameter("error", error)
        .setParameter("information", information)
        .setParameter("changeAction", changeAction)
        .setParameter("errorResult", errorResult);

        List<ApiLog> apiLogs = query.getResultList();
        return apiLogs;
    }

    @Transactional
    @Modifying
    public void deleteApiLog(Integer code, String connectionDetails, String error, String information,
                                  Integer changeAction, Integer errorResult) {
        String filterString = filterParams(code, connectionDetails, error, information, changeAction, errorResult);
        entityManager.createQuery("SELECT a from ApiLog a " +
                filterString, ApiLog.class)
                .setParameter("code", code)
                .setParameter("connectionDetails", connectionDetails)
                .setParameter("error", error)
                .setParameter("information", information)
                .setParameter("changeAction", changeAction)
                .setParameter("errorResult", errorResult)
                .executeUpdate();
    }

    private String filterParams(Integer code, String connectionDetails, String error, String information,
                                Integer changeAction, Integer errorResult) {
        ArrayList<QueryParameterMatcher> queryParameterMatchers = new ArrayList<>();
        if (code !=null) {
            queryParameterMatchers.add(new QueryParameterMatcher("a", "code", "=", code.toString()));
        }
        if (connectionDetails !=null) {
            queryParameterMatchers.add(new QueryParameterMatcher("a", "connectionDetails", "like", connectionDetails));
        }
        if (error !=null) {
            queryParameterMatchers.add(new QueryParameterMatcher("a", "error", "like", error));
        }
        if (information !=null) {
            queryParameterMatchers.add(new QueryParameterMatcher("a", "information", "like", information));
        }
        if (changeAction !=null) {
            if (changeAction.equals(0)) {
                queryParameterMatchers.add(new QueryParameterMatcher("a", "changeAction", "=", "false"));
            } else if (changeAction.equals(0)) {
                queryParameterMatchers.add(new QueryParameterMatcher("a", "changeAction", "=", "true"));
            }
        }
        if (errorResult !=null) {
            if (errorResult.equals(0)) {
                queryParameterMatchers.add(new QueryParameterMatcher("a", "errorResult", "=", "false"));
            } else if (errorResult.equals(0)) {
                queryParameterMatchers.add(new QueryParameterMatcher("a", "errorResult", "=", "true"));
            }
        }
        return QueryParameterMatcher.getWhereQueryWithAnd(queryParameterMatchers);
    }

    public void addLog(ApiLog apiLog) {
        apiLogRepository.saveAndFlush(apiLog);
    }

}
