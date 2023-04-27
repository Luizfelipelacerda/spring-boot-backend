package com.springbootbackend.task;

import com.springbootbackend.ExceptionHandler.TaskErrorResponse;
import com.springbootbackend.ExceptionHandler.TaskNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            throw new IllegalStateException("Task not Found - " + user_id );
        }
    }

    @PostMapping
    public ResponseEntity addTask(@RequestBody TaskDTO task){
        this.taskService.addTask(task);
        return new ResponseEntity<>("Task Created!", HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{task_id}")
    public ResponseEntity deleteTask(@PathVariable("task_id") Long task_id){
        this.taskService.deleteTask(task_id);
        return new ResponseEntity<>("Task Deleted!", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> editeTask(@RequestBody TaskDTO task){
        taskService.editeTask(task);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


    @ExceptionHandler
    public ResponseEntity<TaskErrorResponse> handleException(TaskNotFoundException exc){
        TaskErrorResponse error = new TaskErrorResponse()
                .builder().status(HttpStatus.NOT_FOUND.value())
                .timeStamp(System.currentTimeMillis())
                .message(exc.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<TaskErrorResponse> handleExcepetion(Exception exc){
        TaskErrorResponse error = new TaskErrorResponse()
                .builder().status(HttpStatus.BAD_REQUEST.value())
                .timeStamp(System.currentTimeMillis())
                .message(exc.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
