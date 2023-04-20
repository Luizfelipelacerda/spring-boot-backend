package com.springbootbackend.task;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public List<TaskDTO> getTasks(Long userId){
        Optional<List<Task>> optionalTasks = this.taskRepository.findByUser_id(userId);
        List<TaskDTO> taskDTOS = new ArrayList<>();
        if(optionalTasks.isPresent()){
            if(!optionalTasks.get().isEmpty())
                return optionalTasks.get().stream().map(task -> map(task)).collect(Collectors.toList());
        }
        return taskDTOS;
    }

    public List<Task> getAllTasks() {
        return this.taskRepository.findAll();
    }

    public void addTask(Task task) {
        this.taskRepository.save(task);
    }

    public void deleteTask(Long taskId) {
        this.taskRepository.deleteById(taskId);
    }

    public void editeTask(Task task) {
        this.taskRepository.save(task);
    }

    private TaskDTO map(Task task){
        return TaskDTO.builder().id(task.getTask_id())
                .userId(task.getUser_id())
                .status(task.getStatus())
                .title(task.getTitle())
                .inicio(task.getInicio())
                .fim(task.getFim())
                .objetive(task.getObjetive())
                .build();
    }
}
