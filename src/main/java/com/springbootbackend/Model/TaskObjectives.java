package com.springbootbackend.Model;

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
@Table(name = "TaskObjectives")
public class TaskObjectives {

    @Id
    @SequenceGenerator(
            name = "objective_sequence",
            sequenceName = "objective_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "objective_sequence"
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Long task;

    private String objectiveText;
    private boolean isDone;
    private Date objectiveEnd;
}
