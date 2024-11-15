package app.enforcatechnologies.user_service.repository;

import app.enforcatechnologies.user_service.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByJobTalentId(Long jobTalentId);
}
