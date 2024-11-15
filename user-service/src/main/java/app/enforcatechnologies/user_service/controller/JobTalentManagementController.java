package app.enforcatechnologies.user_service.controller;


import app.enforcatechnologies.user_service.model.JobTalent;
import app.enforcatechnologies.user_service.model.TalentManagement;
import app.enforcatechnologies.user_service.response.Response;
import app.enforcatechnologies.user_service.service.TalentManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/talent-Management")
public class JobTalentManagementController {
    @Autowired
    private TalentManagementService talentManagementService;


    @PostMapping(value = "/add") // Talent Management page
    public ResponseEntity<Response> addJobTalent( @RequestBody TalentManagement talentManagement){
        TalentManagement talentManagement1 = talentManagementService.createTalentInTalentManagement(talentManagement);
        Response response = new Response();
        response.setResponse(talentManagement1.getName() + " added successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping
    public ResponseEntity<List<TalentManagement>> getJobTalents() {
        List<TalentManagement> jobTalents = talentManagementService.findAllTalent();
        return ResponseEntity.ok(jobTalents);
    }


}
