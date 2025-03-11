package com.rajeevcode.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ErrorResponseDto {


    private String apiPath;


    private HttpStatus httpStatus;

    private String errorMessage;

    private LocalDateTime errorTimeStamp;
}
