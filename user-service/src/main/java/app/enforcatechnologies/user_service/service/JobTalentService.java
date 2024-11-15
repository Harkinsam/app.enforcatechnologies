package app.enforcatechnologies.user_service.service;

import app.enforcatechnologies.user_service.exception.ConflictException;
import app.enforcatechnologies.user_service.exception.JobTalentNotFoundException;
import app.enforcatechnologies.user_service.model.JobTalent;
import app.enforcatechnologies.user_service.repository.JobTalentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class JobTalentService {
   private final JobTalentRepository jobTalentRepository;
    @Autowired
    public JobTalentService(JobTalentRepository jobTalentRepository) {
        this.jobTalentRepository = jobTalentRepository;
    }


    public JobTalent createJobTalent(JobTalent jobTalent) {
        Optional<JobTalent> existingJobTalent = jobTalentRepository.findJobTalentByEmail(jobTalent.getEmail());
        if (existingJobTalent.isPresent()) {
            log.warn("JobTalent creation failed: JobTalent with name '{}' already exists", jobTalent.getName());
            throw new ConflictException(jobTalent.getName() + " already exists");
        }
        JobTalent newJobTalent = new JobTalent();
        newJobTalent.setName(jobTalent.getName());
        newJobTalent.setEmail(jobTalent.getEmail());
        newJobTalent.setExpertise(jobTalent.getExpertise());
        newJobTalent.setLocation(jobTalent.getLocation());

        JobTalent saveJobTalent = jobTalentRepository.save(newJobTalent);
        log.info("JobTalent '{}' created successfully with ID: {}", saveJobTalent.getName(), saveJobTalent.getId());

        return saveJobTalent;


    }

    public JobTalent viewProfile(String email){
        Optional<JobTalent> jobTalent = jobTalentRepository.findJobTalentByEmail(email);
        if (jobTalent.isEmpty()){
            throw new JobTalentNotFoundException("JobTalent with email " + email + " not found");
        }
        return jobTalent.get();

    }

    public List<JobTalent> getAllJobTalents() {

        List<JobTalent> jobTalents = jobTalentRepository.findAll();
        if (jobTalents.isEmpty()) {
            throw new JobTalentNotFoundException("No job talents found");
        }
        return jobTalents;
    }


    public List<JobTalent> searchJobTalents(String searchTerm) {
        return jobTalentRepository.findByNameContainingIgnoreCaseOrExpertiseContainingIgnoreCase(searchTerm, searchTerm);
    }
}
