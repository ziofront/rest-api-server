package com.ziofront.restapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestAPIException extends RuntimeException {

    private int responseStatusCode;

    private String responseStatusMessage;



}
