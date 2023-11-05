package br.com.fiap.cp2.repositories;

import br.com.fiap.cp2.models.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, Long> {
}
