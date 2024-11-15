package app.enforcatechnologies.user_service.repository;

import app.enforcatechnologies.user_service.model.JobTalent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobTalentRepository extends JpaRepository<JobTalent, Long> {

    Optional<JobTalent> findJobTalentByEmail(String email);
    List<JobTalent> findAll();
    Optional<JobTalent> findByEmail(String email);
    List<JobTalent> findByNameContainingIgnoreCaseOrExpertiseContainingIgnoreCase(String name, String expertise);
}
