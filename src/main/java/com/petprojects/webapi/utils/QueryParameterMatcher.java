package com.petprojects.webapi.utils;

import java.util.ArrayList;

public class QueryParameterMatcher {

    String aliasName;
    String propertyName;
    String comparision;
    String value;

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getComparision() {
        return comparision;
    }

    public void setComparision(String comparision) {
        this.comparision = comparision;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public QueryParameterMatcher(String aliasName, String propertyName, String comparision, String value) {
        this.aliasName = aliasName;
        this.propertyName = propertyName;
        this.comparision = comparision;
        this.value = value;
    }

    public static String getWhereQueryWithAnd(ArrayList<QueryParameterMatcher> queryParameterMatchers) {
        String filterString = "";
        boolean firstMatch = false;
        for (QueryParameterMatcher queryParameterMatcher : queryParameterMatchers ) {
            String checkResult = queryParameterMatcher.checkParameter();
            if (checkResult!=null) {
                if (firstMatch) {
                    filterString+=" AND ";
                    filterString+=checkResult;
                } else {
                    firstMatch = true;
                    filterString+="WHERE ";
                    filterString+=checkResult;
                }
            }
        }
        return filterString;
    }

    private String checkParameter() {
        if (this.aliasName!=null && this.aliasName.length()>0
        && this.propertyName!=null && this.propertyName.length()>0
                && this.comparision!=null && this.comparision.length()>0
                && this.value!=null && this.value.length()>0
                ) {
            if (this.comparision.contains("like")) {
                return this.aliasName + "." + this.propertyName + " " + this.comparision + " %" + ":" + this.value + "%";
            } else {
                return this.aliasName + "." + this.propertyName + this.comparision + ":" + this.value;
            }

        } else {
            return null;
        }
    }
}
