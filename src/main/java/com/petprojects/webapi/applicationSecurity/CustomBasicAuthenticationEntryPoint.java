package com.petprojects.webapi.applicationSecurity;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

@Component
public class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
            throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        String connectionDetails;
        String username = null;
        String clientAddress;
        String error = "API_ERROR:" + authEx.getMessage();
        String informationDetail;
        ArrayList<String> information = new ArrayList<>();

        clientAddress = HttpRequestInformation.getClientIpAddressFromServletRequest(request);

        if (request.getUserPrincipal() != null && request.getUserPrincipal().getName() != null) {
            username = request.getUserPrincipal().getName();
        }

        if (username != null) {
            connectionDetails = "API_Connection:" + request.getMethod() + " " + request.getRequestURI() + " user:" + username;
        } else {
            connectionDetails = "API_Connection:" + request.getMethod() + " " + request.getRequestURI();
        }
        if (clientAddress != null) {
            connectionDetails += " client ip:" + clientAddress;
        }
        if (request.getHeader("authorization") != null && request.getHeader("authorization").startsWith("Basic ")) {
            String authString = request.getHeader("authorization");
            authString = authString.replaceAll("Basic ", "");
            byte[] bytes = null;
            bytes = Base64.getDecoder().decode(authString);
            String credentials = new String(bytes);
            String[] credentialsPairs = credentials.split(":");
            if (credentialsPairs.length == 2) {
                username = credentialsPairs[0];
                informationDetail = "Access denied. Wrong user login/password, username:" + username;
            } else {
                informationDetail = "Access denied. Wrong user login/password.";
            }
        } else {
            informationDetail = "Access denied. Basic Authentication required.";
        }
        information.add(informationDetail);

        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, connectionDetails, error, information);

        System.out.println(apiError);

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.getOutputStream().println(objectMapper.writeValueAsString(apiError));
    }

    @Override
    public void afterPropertiesSet() {
        setRealmName("Basic Authentication required");
    }


}
