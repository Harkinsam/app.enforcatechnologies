package app.enforcatechnologies.user_service.service;

import app.enforcatechnologies.user_service.exception.ConflictException;
import app.enforcatechnologies.user_service.exception.JobTalentNotFoundException;
import app.enforcatechnologies.user_service.model.JobTalent;
import app.enforcatechnologies.user_service.model.TalentManagement;
import app.enforcatechnologies.user_service.repository.TalentManagementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Service
@Slf4j
public class TalentManagementService {

    private final TalentManagementRepository talentManagementRepository;
    @Autowired
    public TalentManagementService(TalentManagementRepository talentManagementRepository) {
        this.talentManagementRepository = talentManagementRepository;
    }

    public TalentManagement createTalentInTalentManagement(TalentManagement talentManagement){
        Optional<TalentManagement> existingTalent = talentManagementRepository.findTalentManagementByEmail(talentManagement.getEmail());
        if (existingTalent.isPresent()){
            log.warn("JobTalent creation failed: JobTalent with name '{}' already exists", talentManagement.getName());
            throw new ConflictException(talentManagement.getName() + " already exists");
        }
        TalentManagement newTalent = new TalentManagement();
        newTalent.setName(talentManagement.getName());
        newTalent.setEmail(talentManagement.getEmail());
        newTalent.setCompany(talentManagement.getCompany());
        newTalent.setRole(talentManagement.getRole());
        newTalent.setLocalDate(LocalDate.now());

        TalentManagement saveTalent = talentManagementRepository.save(newTalent);
        log.info("JobTalent '{}' created successfully with name: {}", saveTalent.getName(), saveTalent.getRole());

        return saveTalent;

    }

    public List<TalentManagement> findAllTalent(){
        List<TalentManagement> jobTalents = talentManagementRepository.findAll();

        if (jobTalents.isEmpty()) {
            throw new JobTalentNotFoundException("No job talents found");
        }
        return jobTalents;

    }
}
