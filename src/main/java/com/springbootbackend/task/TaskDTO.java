package com.springbootbackend.task;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;

import java.util.Date;


@Data
@Builder
public class TaskDTO {
    private Long id;
    private Long userId;
    private String title;

    private StatusTask status;
    private Date inicio;
    private Date fim;
    private String objetive;
}
