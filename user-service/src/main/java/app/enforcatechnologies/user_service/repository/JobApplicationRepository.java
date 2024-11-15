package app.enforcatechnologies.user_service.repository;

import app.enforcatechnologies.user_service.enums.ApplicationStatus;
import app.enforcatechnologies.user_service.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findAllByStatus(ApplicationStatus status);
    Long countByStatus(ApplicationStatus status);
}

