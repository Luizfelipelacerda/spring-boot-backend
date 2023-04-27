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
    private final TaskObjectiveRepository taskObjectiveRepository;

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

    public void addTask(TaskDTO taskDTO) {
        Task task = Task.builder()
                .user_id(taskDTO.getUserId())
                .title(taskDTO.getTitle())
                .status(taskDTO.getStatus())
                .inicio(taskDTO.getInicio())
                .fim(taskDTO.getFim())
                .objetive(taskDTO.getObjetive())
                .build();
        Task savedTask = this.taskRepository.save(task);
        if(!taskDTO.getListObjetives().isEmpty()){
            List<TaskObjectives> taskObjectivesList = taskDTO.getListObjetives().stream().map(
                    taskObj -> {
                        return TaskObjectives.builder()
                                .task(savedTask.getTask_id())
                                .done(taskObj.getDone())
                                .objective_text(taskObj.getObjectiveText())
                                .objective_end(taskObj.getObjectiveEnd()).build();
                    }).collect(Collectors.toList());
            taskObjectiveRepository.saveAll(taskObjectivesList);
        }


    }

    public void deleteTask(Long taskId) {
        this.taskRepository.deleteById(taskId);
        List<TaskObjectives> allByTask = this.taskObjectiveRepository.findAllByTask(taskId);
        this.taskObjectiveRepository.deleteAll(allByTask);
    }

    public void editeTask(TaskDTO taskDTO) {
        Task task = map(taskDTO);
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
    private Task map(TaskDTO task){
        return Task.builder()
                .task_id(task.getUserId())
                .user_id(task.getUserId())
                .status(task.getStatus())
                .title(task.getTitle())
                .inicio(task.getInicio())
                .fim(task.getFim())
                .objetive(task.getObjetive())
                .build();
    }
}
