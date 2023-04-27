package com.springbootbackend.task;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TaskObjectivesDTO {


    private Long objective_id;
    private Long task;
    private String objectiveText;
    private Boolean done;
    private Date objectiveEnd;

}
