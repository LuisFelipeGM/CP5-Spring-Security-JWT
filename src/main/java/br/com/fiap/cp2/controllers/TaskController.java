package br.com.fiap.cp2.controllers;

import br.com.fiap.cp2.dtos.TaskDto;
import br.com.fiap.cp2.dtos.TaskPutDto;
import br.com.fiap.cp2.models.TaskModel;
import br.com.fiap.cp2.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TaskController extends GenericController{

    final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public ResponseEntity<Object> get() {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskModel> getById(@PathVariable Long id) {
        Optional<TaskModel> optional = taskService.findById(id);
        return optional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Object> save(@Valid @RequestBody TaskDto taskDto, BindingResult result){
        try {
            return result.hasErrors() ? ResponseEntity.unprocessableEntity().body(getErrors(result))
                    : ResponseEntity.status(HttpStatus.CREATED)
                    .body(taskService.addTask(taskDto));
        } catch (Exception e) {
            return handleErrors(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        Optional<TaskModel> optional = taskService.findById(id);
        return optional.map(task -> {
            taskService.deleteById(id);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> put(@PathVariable Long id, @Valid @RequestBody TaskPutDto taskPutDto, BindingResult result){
        Optional<TaskModel> optional = taskService.findById(id);
        if (optional.isEmpty())
            return ResponseEntity.notFound().build();
        try {
            return result.hasErrors() ? ResponseEntity.unprocessableEntity().body(getErrors(result))
                    : ResponseEntity.status(HttpStatus.CREATED)
                    .body(taskService.putTask(taskPutDto, id));
        } catch (Exception e) {
            return handleErrors(e);
        }
    }


}
