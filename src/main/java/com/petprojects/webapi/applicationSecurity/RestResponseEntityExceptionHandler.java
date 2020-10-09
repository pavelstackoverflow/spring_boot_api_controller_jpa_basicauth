package com.petprojects.webapi.applicationSecurity;

import com.sun.istack.Nullable;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullApi;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
        String connectionDetails = null;
        String username = null;
        String clientAddress = null;
        String error = "API_ERROR:" + ex.getMessage();
        ArrayList<String> information = new ArrayList<>();

        clientAddress = HttpRequestInformation.getClientIpAddressFromWebRequest(request);

        if (request.getUserPrincipal() != null && request.getUserPrincipal().getName() != null) {
            username = request.getUserPrincipal().getName();
        }

        if (username != null) {
            connectionDetails = "API_Connection:" + request.getDescription(true) + " user:" + username;
        } else {
            connectionDetails = "API_Connection:" + request.getDescription(true);
        }
        if (clientAddress != null) {
            connectionDetails += " client ip:" + clientAddress;
        }
        information.add("Access denied. Role for user:" + username +
                " has no permission for operation:" + request.getDescription(false));

        ApiError apiError = new ApiError(HttpStatus.FORBIDDEN, connectionDetails, error, information);
        System.out.println(apiError);
        return new ResponseEntity<Object>(apiError, HttpStatus.FORBIDDEN);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String connectionDetails = null;
        String username = null;
        String clientAddress = null;
        String error = "API_ERROR:" + ex.getMessage();
        ArrayList<String> information = new ArrayList<>();

        clientAddress = HttpRequestInformation.getClientIpAddressFromWebRequest(request);

        if (request.getUserPrincipal() != null && request.getUserPrincipal().getName() != null) {
            username = request.getUserPrincipal().getName();
        }

        if (username != null) {
            connectionDetails = "API_Connection:" + request.getDescription(true) + " user:" + username;
        } else {
            connectionDetails = "API_Connection:" + request.getDescription(true);
        }
        if (clientAddress != null) {
            connectionDetails += " client ip:" + clientAddress;
        }
        if (ex.getSupportedHttpMethods() != null) {
            String httpMethods = ex.getMethod() + " method not supported, Supported http methods:";
            for (HttpMethod httpMethod : ex.getSupportedHttpMethods()) {
                httpMethods += httpMethod.name();
            }
            information.add(httpMethods);
        }

        ApiError apiError = new ApiError(status, connectionDetails, error, information);

        System.out.println(apiError);

        return new ResponseEntity<Object>(apiError, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String connectionDetails = null;
        String username = null;
        String clientAddress = null;
        String error = "API_ERROR:" + ex.getMessage();
        ArrayList<String> information = new ArrayList<>();

        clientAddress = HttpRequestInformation.getClientIpAddressFromWebRequest(request);

        if (request.getUserPrincipal() != null && request.getUserPrincipal().getName() != null) {
            username = request.getUserPrincipal().getName();
        }

        if (username != null) {
            connectionDetails = "API_Connection:" + request.getDescription(true) + " user:" + username;
        } else {
            connectionDetails = "API_Connection:" + request.getDescription(true);
        }
        if (clientAddress != null) {
            connectionDetails += " client ip:" + clientAddress;
        }
        String supportedMediaTypes = ex.getContentType() + " media type not supported, Supported media types:";
        for (MediaType mediaType : ex.getSupportedMediaTypes()) {
            supportedMediaTypes += mediaType;
        }
        information.add(supportedMediaTypes);

        ApiError apiError = new ApiError(status, connectionDetails, error, information);

        System.out.println(apiError);

        return new ResponseEntity<Object>(apiError, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String connectionDetails = null;
        String username = null;
        String clientAddress = null;
        String error = "API_ERROR:" + ex.getMessage();
        ArrayList<String> information = new ArrayList<>();

        clientAddress = HttpRequestInformation.getClientIpAddressFromWebRequest(request);

        if (request.getUserPrincipal() != null && request.getUserPrincipal().getName() != null) {
            username = request.getUserPrincipal().getName();
        }

        if (username != null) {
            connectionDetails = "API_Connection:" + request.getDescription(true) + " user:" + username;
        } else {
            connectionDetails = "API_Connection:" + request.getDescription(true);
        }
        if (clientAddress != null) {
            connectionDetails += " client ip:" + clientAddress;
        }
        String supportedMediaTypes = "Media type not acceptable, Supported media types:";
        for (MediaType mediaType : ex.getSupportedMediaTypes()) {
            supportedMediaTypes += mediaType;
        }
        information.add(supportedMediaTypes);

        ApiError apiError = new ApiError(status, connectionDetails, error, information);

        System.out.println(apiError);

        return new ResponseEntity<Object>(apiError, status);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String connectionDetails = null;
        String username = null;
        String clientAddress = null;
        String error = "API_ERROR:" + ex.getMessage();
        ArrayList<String> information = new ArrayList<>();

        clientAddress = HttpRequestInformation.getClientIpAddressFromWebRequest(request);

        if (request.getUserPrincipal() != null && request.getUserPrincipal().getName() != null) {
            username = request.getUserPrincipal().getName();
        }

        if (username != null) {
            connectionDetails = "API_Connection:" + request.getDescription(true) + " user:" + username;
        } else {
            connectionDetails = "API_Connection:" + request.getDescription(true);
        }
        if (clientAddress != null) {
            connectionDetails += " client ip:" + clientAddress;
        }
        String missPathVariable = "Missing path variable:" + ex.getVariableName();
        information.add(missPathVariable);

        ApiError apiError = new ApiError(status, connectionDetails, error, information);

        System.out.println(apiError);

        return new ResponseEntity<Object>(apiError, status);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String connectionDetails = null;
        String username = null;
        String clientAddress = null;
        String error = "API_ERROR:" + ex.getMessage();
        ArrayList<String> information = new ArrayList<>();

        clientAddress = HttpRequestInformation.getClientIpAddressFromWebRequest(request);

        if (request.getUserPrincipal() != null && request.getUserPrincipal().getName() != null) {
            username = request.getUserPrincipal().getName();
        }

        if (username != null) {
            connectionDetails = "API_Connection:" + request.getDescription(true) + " user:" + username;
        } else {
            connectionDetails = "API_Connection:" + request.getDescription(true);
        }
        if (clientAddress != null) {
            connectionDetails += " client ip:" + clientAddress;
        }
        String missRequestParameter = "Missing request parameter:" + ex.getParameterName() + " type:" + ex.getParameterType();
        information.add(missRequestParameter);

        ApiError apiError = new ApiError(status, connectionDetails, error, information);

        System.out.println(apiError);

        return new ResponseEntity<Object>(apiError, status);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String connectionDetails = null;
        String username = null;
        String clientAddress = null;
        String error = "API_ERROR:" + ex.getMessage();
        ArrayList<String> information = new ArrayList<>();

        clientAddress = HttpRequestInformation.getClientIpAddressFromWebRequest(request);

        if (request.getUserPrincipal() != null && request.getUserPrincipal().getName() != null) {
            username = request.getUserPrincipal().getName();
        }

        if (username != null) {
            connectionDetails = "API_Connection:" + request.getDescription(true) + " user:" + username;
        } else {
            connectionDetails = "API_Connection:" + request.getDescription(true);
        }
        if (clientAddress != null) {
            connectionDetails += " client ip:" + clientAddress;
        }
        String informationDetail = "Request binding exception";
        information.add(informationDetail);

        ApiError apiError = new ApiError(status, connectionDetails, error, information);

        System.out.println(apiError);

        return new ResponseEntity<Object>(apiError, status);
    }

    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String connectionDetails = null;
        String username = null;
        String clientAddress = null;
        String error = "API_ERROR:" + ex.getMessage();
        ArrayList<String> information = new ArrayList<>();

        clientAddress = HttpRequestInformation.getClientIpAddressFromWebRequest(request);

        if (request.getUserPrincipal() != null && request.getUserPrincipal().getName() != null) {
            username = request.getUserPrincipal().getName();
        }

        if (username != null) {
            connectionDetails = "API_Connection:" + request.getDescription(true) + " user:" + username;
        } else {
            connectionDetails = "API_Connection:" + request.getDescription(true);
        }
        if (clientAddress != null) {
            connectionDetails += " client ip:" + clientAddress;
        }
        String informationDetail = "Conversion not supported error code:" + ex.getErrorCode() + " property:" + ex.getPropertyName();
        information.add(informationDetail);

        ApiError apiError = new ApiError(status, connectionDetails, error, information);

        System.out.println(apiError);

        return new ResponseEntity<Object>(apiError, status);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String connectionDetails = null;
        String username = null;
        String clientAddress = null;
        String error = "API_ERROR:" + ex.getMessage();
        ArrayList<String> information = new ArrayList<>();

        clientAddress = HttpRequestInformation.getClientIpAddressFromWebRequest(request);

        if (request.getUserPrincipal() != null && request.getUserPrincipal().getName() != null) {
            username = request.getUserPrincipal().getName();
        }

        if (username != null) {
            connectionDetails = "API_Connection:" + request.getDescription(true) + " user:" + username;
        } else {
            connectionDetails = "API_Connection:" + request.getDescription(true);
        }
        if (clientAddress != null) {
            connectionDetails += " client ip:" + clientAddress;
        }
        String informationDetail = "Type mismatch error code:" + ex.getErrorCode() + " property:" + ex.getPropertyName();
        information.add(informationDetail);

        ApiError apiError = new ApiError(status, connectionDetails, error, information);

        System.out.println(apiError);

        return new ResponseEntity<Object>(apiError, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String connectionDetails = null;
        String username = null;
        String clientAddress = null;
        String error = "API_ERROR:" + ex.getMessage();
        ArrayList<String> information = new ArrayList<>();

        clientAddress = HttpRequestInformation.getClientIpAddressFromWebRequest(request);

        if (request.getUserPrincipal() != null && request.getUserPrincipal().getName() != null) {
            username = request.getUserPrincipal().getName();
        }

        if (username != null) {
            connectionDetails = "API_Connection:" + request.getDescription(true) + " user:" + username;
        } else {
            connectionDetails = "API_Connection:" + request.getDescription(true);
        }
        if (clientAddress != null) {
            connectionDetails += " client ip:" + clientAddress;
        }
        String informationDetail = "Message not readable";
        information.add(informationDetail);

        ApiError apiError = new ApiError(status, connectionDetails, error, information);

        System.out.println(apiError);

        return new ResponseEntity<Object>(apiError, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String connectionDetails = null;
        String username = null;
        String clientAddress = null;
        String error = "API_ERROR:" + ex.getMessage();
        ArrayList<String> information = new ArrayList<>();

        clientAddress = HttpRequestInformation.getClientIpAddressFromWebRequest(request);

        if (request.getUserPrincipal() != null && request.getUserPrincipal().getName() != null) {
            username = request.getUserPrincipal().getName();
        }

        if (username != null) {
            connectionDetails = "API_Connection:" + request.getDescription(true) + " user:" + username;
        } else {
            connectionDetails = "API_Connection:" + request.getDescription(true);
        }
        if (clientAddress != null) {
            connectionDetails += " client ip:" + clientAddress;
        }
        String informationDetail = "Message not writable";
        information.add(informationDetail);

        ApiError apiError = new ApiError(status, connectionDetails, error, information);

        System.out.println(apiError);

        return new ResponseEntity<Object>(apiError, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String connectionDetails = null;
        String username = null;
        String clientAddress = null;
        String error = "API_ERROR:" + ex.getMessage();
        ArrayList<String> information = new ArrayList<>();

        clientAddress = HttpRequestInformation.getClientIpAddressFromWebRequest(request);

        if (request.getUserPrincipal() != null && request.getUserPrincipal().getName() != null) {
            username = request.getUserPrincipal().getName();
        }

        if (username != null) {
            connectionDetails = "API_Connection:" + request.getDescription(true) + " user:" + username;
        } else {
            connectionDetails = "API_Connection:" + request.getDescription(true);
        }
        if (clientAddress != null) {
            connectionDetails += " client ip:" + clientAddress;
        }
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            information.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
        }
        for (ObjectError objectError : ex.getBindingResult().getGlobalErrors()) {
            information.add(objectError.getObjectName() + ": " + objectError.getDefaultMessage());
        }

        ApiError apiError = new ApiError(status, connectionDetails, error, information);

        System.out.println(apiError);

        return new ResponseEntity<Object>(apiError, status);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String connectionDetails = null;
        String username = null;
        String clientAddress = null;
        String error = "API_ERROR:" + ex.getMessage();
        ArrayList<String> information = new ArrayList<>();

        clientAddress = HttpRequestInformation.getClientIpAddressFromWebRequest(request);

        if (request.getUserPrincipal() != null && request.getUserPrincipal().getName() != null) {
            username = request.getUserPrincipal().getName();
        }

        if (username != null) {
            connectionDetails = "API_Connection:" + request.getDescription(true) + " user:" + username;
        } else {
            connectionDetails = "API_Connection:" + request.getDescription(true);
        }
        if (clientAddress != null) {
            connectionDetails += " client ip:" + clientAddress;
        }
        String informationDetail = "Request part missing:" + ex.getRequestPartName();
        information.add(informationDetail);

        ApiError apiError = new ApiError(status, connectionDetails, error, information);

        System.out.println(apiError);

        return new ResponseEntity<Object>(apiError, status);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String connectionDetails = null;
        String username = null;
        String clientAddress = null;
        String error = "API_ERROR:" + ex.getMessage();
        ArrayList<String> information = new ArrayList<>();

        clientAddress = HttpRequestInformation.getClientIpAddressFromWebRequest(request);

        if (request.getUserPrincipal() != null && request.getUserPrincipal().getName() != null) {
            username = request.getUserPrincipal().getName();
        }

        if (username != null) {
            connectionDetails = "API_Connection:" + request.getDescription(true) + " user:" + username;
        } else {
            connectionDetails = "API_Connection:" + request.getDescription(true);
        }
        if (clientAddress != null) {
            connectionDetails += " client ip:" + clientAddress;
        }
        String informationDetail = "Bind exception error count:" + ex.getErrorCount();
        information.add(informationDetail);

        for (FieldError fieldError : ex.getFieldErrors()) {
            information.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
        }
        for (ObjectError objectError : ex.getBindingResult().getGlobalErrors()) {
            information.add(objectError.getObjectName() + ": " + objectError.getDefaultMessage());
        }

        ApiError apiError = new ApiError(status, connectionDetails, error, information);

        System.out.println(apiError);

        return new ResponseEntity<Object>(apiError, status);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String connectionDetails = null;
        String username = null;
        String clientAddress = null;
        String error = "API_ERROR:" + ex.getMessage();
        ArrayList<String> information = new ArrayList<>();

        clientAddress = HttpRequestInformation.getClientIpAddressFromWebRequest(request);

        if (request.getUserPrincipal() != null && request.getUserPrincipal().getName() != null) {
            username = request.getUserPrincipal().getName();
        }

        if (username != null) {
            connectionDetails = "API_Connection:" + request.getDescription(true) + " user:" + username;
        } else {
            connectionDetails = "API_Connection:" + request.getDescription(true);
        }
        if (clientAddress != null) {
            connectionDetails += " client ip:" + clientAddress;
        }
        String informationDetail = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();
        information.add(informationDetail);

        ApiError apiError = new ApiError(status, connectionDetails, error, information);

        System.out.println(apiError);

        return new ResponseEntity<Object>(apiError, status);
    }

    @Override
    protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String connectionDetails = null;
        String username = null;
        String clientAddress = null;
        String error = "API_ERROR:" + ex.getMessage();
        ArrayList<String> information = new ArrayList<>();

        clientAddress = HttpRequestInformation.getClientIpAddressFromWebRequest(request);

        if (request.getUserPrincipal() != null && request.getUserPrincipal().getName() != null) {
            username = request.getUserPrincipal().getName();
        }

        if (username != null) {
            connectionDetails = "API_Connection:" + request.getDescription(true) + " user:" + username;
        } else {
            connectionDetails = "API_Connection:" + request.getDescription(true);
        }
        if (clientAddress != null) {
            connectionDetails += " client ip:" + clientAddress;
        }
        String informationDetail = "Async request timeout";
        information.add(informationDetail);

        ApiError apiError = new ApiError(status, connectionDetails, error, information);

        System.out.println(apiError);

        return new ResponseEntity<Object>(apiError, status);
    }

    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<Object> handleResponseStatusException(Exception ex, WebRequest request) {
        String connectionDetails = null;
        String username = null;
        String clientAddress = null;
        String error = "API_ERROR:" + ex.getMessage();
        ArrayList<String> information = new ArrayList<>();

        clientAddress = HttpRequestInformation.getClientIpAddressFromWebRequest(request);

        if (request.getUserPrincipal() != null && request.getUserPrincipal().getName() != null) {
            username = request.getUserPrincipal().getName();
        }

        if (username != null) {
            connectionDetails = "API_Connection:" + request.getDescription(true) + " user:" + username;
        } else {
            connectionDetails = "API_Connection:" + request.getDescription(true);
        }
        if (clientAddress != null) {
            connectionDetails += " client ip:" + clientAddress;
        }
        String informationDetail = "Validation error, object values wrong";
        information.add(informationDetail);

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, connectionDetails, error, information);

        System.out.println(apiError);

        return new ResponseEntity<Object>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        String connectionDetails = null;
        String username = null;
        String clientAddress = null;
        String error = "API_ERROR:" + ex.getMessage();
        ArrayList<String> information = new ArrayList<>();

        clientAddress = HttpRequestInformation.getClientIpAddressFromWebRequest(request);

        if (request.getUserPrincipal() != null && request.getUserPrincipal().getName() != null) {
            username = request.getUserPrincipal().getName();
        }

        if (username != null) {
            connectionDetails = "API_Connection:" + request.getDescription(true) + " user:" + username;
        } else {
            connectionDetails = "API_Connection:" + request.getDescription(true);
        }
        if (clientAddress != null) {
            connectionDetails += " client ip:" + clientAddress;
        }
        String informationDetail = "Unknown error, last default handler captured";
        information.add(informationDetail);

        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, connectionDetails, error, information);

        System.out.println(apiError);

        return new ResponseEntity<Object>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
