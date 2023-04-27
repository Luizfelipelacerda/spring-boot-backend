package com.springbootbackend.ExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TaskErrorResponse{
    private int status;
    private String message;
    private long timeStamp;


}
