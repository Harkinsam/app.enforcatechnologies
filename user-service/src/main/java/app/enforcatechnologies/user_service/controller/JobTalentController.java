package app.enforcatechnologies.user_service.controller;

import app.enforcatechnologies.user_service.model.JobTalent;
import app.enforcatechnologies.user_service.response.Response;
import app.enforcatechnologies.user_service.service.JobTalentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home/Job-talent")
public class JobTalentController {
    private final JobTalentService jobTalentService;
    @Autowired
    public JobTalentController(JobTalentService jobTalentService) {
        this.jobTalentService = jobTalentService;
    }

    @PostMapping(value = "/add") // home job talent
    public ResponseEntity<Response> addJobTalent( @RequestBody JobTalent jobTalent){
        JobTalent newjobTalent = jobTalentService.createJobTalent(jobTalent);
        Response response = new Response();
        response.setResponse(newjobTalent.getName() + " added successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
    @GetMapping("/profile")
    public ResponseEntity<JobTalent> viewProfile(@RequestParam String email) {
        JobTalent jobTalent = jobTalentService.viewProfile(email);
        return ResponseEntity.ok(jobTalent);
    }

    @GetMapping("/all")
    public ResponseEntity<List<JobTalent>> getJobTalents() {
        List<JobTalent> jobTalents = jobTalentService.getAllJobTalents();
        return ResponseEntity.ok(jobTalents);
    }

    @GetMapping("/search")
    public ResponseEntity<List<JobTalent>> searchJobTalents(@RequestParam String searchTerm) {
        List<JobTalent> results = jobTalentService.searchJobTalents(searchTerm);

        if (results.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(results);
        }
        return ResponseEntity.ok(results);
    }



}
