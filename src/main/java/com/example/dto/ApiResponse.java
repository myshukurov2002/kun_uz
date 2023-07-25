package com.example.dto;import com.fasterxml.jackson.annotation.JsonInclude;import lombok.Data;@Data@JsonInclude(JsonInclude.Include.NON_NULL)public class ApiResponse {    private Boolean status;    private String message;    private Object data;    public ApiResponse(Boolean status, String message) {        this.status = status;        this.message = message;    }    public ApiResponse(Boolean status, Object data) {        this.status = status;        this.data = data;    }}