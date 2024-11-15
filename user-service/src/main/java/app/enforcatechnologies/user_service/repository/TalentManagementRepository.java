package app.enforcatechnologies.user_service.repository;

import app.enforcatechnologies.user_service.model.TalentManagement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TalentManagementRepository extends JpaRepository<TalentManagement, Long> {

    Optional<TalentManagement> findTalentManagementByEmail(String email);

    List<TalentManagement> findAll();
}
