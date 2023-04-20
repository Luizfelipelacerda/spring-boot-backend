package com.springbootbackend.task;

import com.springbootbackend.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/task")
public class TaskController {

    private final TaskService taskService;


    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> allTasks = this.taskService.getAllTasks();
        if(!allTasks.isEmpty()){
            return new ResponseEntity<>(allTasks, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(path = "/{user_id}")
    public ResponseEntity<List<TaskDTO>> getTasks(@PathVariable Long user_id){
        List<TaskDTO> tasks = taskService.getTasks(user_id);
        if(!tasks.isEmpty()){
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity addTask(@RequestBody Task task){
        this.taskService.addTask(task);
        return new ResponseEntity<>("Task Created!", HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{task_id}")
    public ResponseEntity deleteTask(@PathVariable("task_id") Long task_id){
        this.taskService.deleteTask(task_id);
        return new ResponseEntity<>("Task Deleted!", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> editeTask(@RequestBody Task task){
        taskService.editeTask(task);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
