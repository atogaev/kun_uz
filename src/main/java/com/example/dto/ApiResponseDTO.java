package com.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseDTO {
    private Boolean status;
    private String message;
    private Object response;

    public ApiResponseDTO(Boolean status,String message){
        this.status = status;
        this.message = message;
    }
    public ApiResponseDTO(Boolean status,Object response){
        this.status = status;
        this.response = response;
    }
}
