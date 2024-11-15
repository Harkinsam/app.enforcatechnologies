package app.enforcatechnologies.user_service.controller;

import app.enforcatechnologies.user_service.enums.ApplicationStatus;
import app.enforcatechnologies.user_service.model.JobApplication;
import app.enforcatechnologies.user_service.service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/job-applications")
public class JobApplicationController {

    @Autowired
    private JobApplicationService jobApplicationService;

    @PostMapping("/upload")
    //Upload Job Application in job page
    public ResponseEntity<JobApplication> uploadJobApplication(@RequestBody JobApplication application) {
        JobApplication createdApplication = jobApplicationService.createJobApplication(application);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdApplication);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable Long id, @RequestParam ApplicationStatus status) {
        jobApplicationService.updateApplicationStatus(id, status);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<JobApplication>> getApplicationsByStatus(@RequestParam ApplicationStatus status) {
        List<JobApplication> applications = jobApplicationService.getApplicationsByStatus(status);
        return ResponseEntity.ok(applications);
    }

    @GetMapping("/counts")
    public ResponseEntity<Map<ApplicationStatus, Long>> getApplicationCounts() {
        Map<ApplicationStatus, Long> counts = jobApplicationService.getApplicationCountsByStatus();
        return ResponseEntity.ok(counts);
    }
}

