package app.enforcatechnologies.user_service.controller;

import app.enforcatechnologies.user_service.model.Task;
import app.enforcatechnologies.user_service.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/add/{jobTalentId}")
    public ResponseEntity<Task> addTask(@RequestBody Task task, @PathVariable Long jobTalentId) {
        Task newTask = taskService.addTaskToJobTalent(task, jobTalentId);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
    }

    // Get all tasks
    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    // Get tasks by JobTalent ID
    @GetMapping("/jobtalent/{jobTalentId}")
    public ResponseEntity<List<Task>> getTasksByJobTalent(@PathVariable Long jobTalentId) {
        List<Task> tasks = taskService.getTasksByJobTalent(jobTalentId);
        return ResponseEntity.ok(tasks);
    }
}
