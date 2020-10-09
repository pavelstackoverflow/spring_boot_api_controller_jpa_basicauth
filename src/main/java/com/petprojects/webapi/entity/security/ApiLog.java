package com.petprojects.webapi.entity.security;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ApiLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int code;
    private String status;
    private boolean changeAction;
    private String connectionDetails;
    private boolean errorResult;
    private String error;
    private String information;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isChangeAction() {
        return changeAction;
    }

    public void setChangeAction(boolean changeAction) {
        this.changeAction = changeAction;
    }

    public String getConnectionDetails() {
        return connectionDetails;
    }

    public void setConnectionDetails(String connectionDetails) {
        this.connectionDetails = connectionDetails;
    }

    public boolean isErrorResult() {
        return errorResult;
    }

    public void setErrorResult(boolean errorResult) {
        this.errorResult = errorResult;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public ApiLog(Long id, int code, String status, boolean changeAction, String connectionDetails, boolean errorResult, String error, String information, LocalDateTime time) {
        this.id = id;
        this.code = code;
        this.status = status;
        this.changeAction = changeAction;
        this.connectionDetails = connectionDetails;
        this.errorResult = errorResult;
        this.error = error;
        this.information = information;
        this.time = time;
    }

    public ApiLog(Long id, int code, String status, boolean changeAction, String connectionDetails, boolean errorResult, String error, String information) {
        this.id = id;
        this.code = code;
        this.status = status;
        this.changeAction = changeAction;
        this.connectionDetails = connectionDetails;
        this.errorResult = errorResult;
        this.error = error;
        this.information = information;
    }



    public ApiLog() {
    }
}
