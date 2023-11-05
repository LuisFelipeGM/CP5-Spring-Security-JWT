package br.com.fiap.cp2.services;

import br.com.fiap.cp2.dtos.TaskDto;
import br.com.fiap.cp2.dtos.TaskPutDto;
import br.com.fiap.cp2.models.TaskModel;
import br.com.fiap.cp2.repositories.TaskRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService extends EntityService<TaskModel> {

    private final TaskRepository taskRepository;

    TaskService(JpaRepository<TaskModel, Long> repository, TaskRepository taskRepository) {
        super(repository);
        this.taskRepository = taskRepository;
    }

    public TaskModel addTask(TaskDto taskDto){
        try {
            TaskModel task = new TaskModel();
            BeanUtils.copyProperties(taskDto, task);
            return repository.save(task);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public TaskModel putTask(TaskPutDto taskPutDto, Long id){
        try {
            Optional<TaskModel> taskOptional = taskRepository.findById(id);
            TaskModel task = taskOptional.get();
            BeanUtils.copyProperties(taskPutDto, task);
            return repository.save(task);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
