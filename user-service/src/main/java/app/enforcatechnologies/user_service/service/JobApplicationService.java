package app.enforcatechnologies.user_service.service;

import app.enforcatechnologies.user_service.enums.ApplicationStatus;
import app.enforcatechnologies.user_service.model.JobApplication;
import app.enforcatechnologies.user_service.repository.JobApplicationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JobApplicationService {

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    public JobApplication createJobApplication(JobApplication application) {
        application.setApplicationDate(LocalDate.now());
        application.setStatus(ApplicationStatus.APPLIED);
        return jobApplicationRepository.save(application);
    }

    public void updateApplicationStatus(Long applicationId, ApplicationStatus newStatus) {
        JobApplication application = jobApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new EntityNotFoundException("Application not found"));
        application.setStatus(newStatus);
        jobApplicationRepository.save(application);
    }

    public List<JobApplication> getApplicationsByStatus(ApplicationStatus status) {
        return jobApplicationRepository.findAllByStatus(status);
    }

    public Map<ApplicationStatus, Long> getApplicationCountsByStatus() {
        Map<ApplicationStatus, Long> counts = new HashMap<>();
        for (ApplicationStatus status : ApplicationStatus.values()) {
            counts.put(status, jobApplicationRepository.countByStatus(status));
        }
        return counts;
    }
}

