package com.petprojects.webapi.applicationSecurity;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ApiError {

    private int code;
    private HttpStatus status;
    private String connectionDetails;
    private String error;
    private List<String> information;

    public ApiError(HttpStatus status, String connectionDetails, String error, List<String> information) {
        this.code = status.value();
        this.status = status;
        this.connectionDetails = connectionDetails;
        this.error = error;
        this.information = information;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getConnectionDetails() {
        return connectionDetails;
    }

    public void setConnectionDetails(String connectionDetails) {
        this.connectionDetails = connectionDetails;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<String> getInformation() {
        return information;
    }

    public void setInformation(List<String> information) {
        this.information = information;
    }

    @Override
    public String toString() {
        String result="";
        result+="ApiError{";
        result+="code=" + code;
        result+=",status=" + status;
        result+=",connectionDetails=" + connectionDetails;
        if (error!=null) {
            result+=",error=" + error;
        } else {
            result+=",error=null";
        }
        if (information != null) {
            if (information.size() == 0) {
                result += ",information=0 messages";
            } else {
                result += ",information=";
                for (int i = 0; i < information.size(); i++) {
                    if (information.get(i)!=null) {
                        result += "message=("+information.get(i)+")";
                    } else {
                        result += "message(null)";
                    }
                }
            }
        } else {
            result += ",information=null";
        }
        result+="}";
        return result;
    }
}
