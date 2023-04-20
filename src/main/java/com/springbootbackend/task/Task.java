package com.springbootbackend.task;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table
public class Task {

    @Id
    @SequenceGenerator(
            name = "task_sequence",
            sequenceName = "task_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_sequence"
    )
    private Long task_id;
    private Long user_id;
    private String title;

    private StatusTask status;
    private Date inicio;
    private Date fim;
    private String objetive;


}
