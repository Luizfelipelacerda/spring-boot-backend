package com.springbootbackend.task;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "taskobjectives")
public class TaskObjectives {

    @Id
    @SequenceGenerator(
            name = "objective_id_sequence",
            sequenceName = "objective_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "objective_id_sequence"
    )
    private Long objective_id;
    private Long task;
    private String objective_text;
    private boolean done;
    private Date objective_end;

}
