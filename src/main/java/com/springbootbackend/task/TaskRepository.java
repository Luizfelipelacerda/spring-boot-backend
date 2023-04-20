package com.springbootbackend.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository
    extends JpaRepository<Task, Long> {


    @Query(value = "SELECT * FROM task WHERE user_id = :user_id",
            nativeQuery = true)
    Optional<List<Task>> findByUser_id(@Param("user_id") Long user_id);

    @Query(value = "SELECT * FROM task",
            nativeQuery = true)
    Optional<List<Task>> findAllTask();
}
