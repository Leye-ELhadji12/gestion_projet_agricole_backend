package com.projectmanagement.controller;

import com.projectmanagement.dto.ActivityDTO;
import com.projectmanagement.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @GetMapping("/{projectId}")
    public ResponseEntity<Page<ActivityDTO>> getAllActivities(
            @PathVariable Long projectId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<ActivityDTO> activities = activityService.getActivities(
                projectId, PageRequest.of(page, size));
        return ResponseEntity.ok(activities);
    }

    @PostMapping("/{projectID}/create")
    public ResponseEntity<ActivityDTO> createActivity(
            @PathVariable Long projectID,
            @RequestBody ActivityDTO activityDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(activityService.createActivity(projectID, activityDTO));
    }

    @PatchMapping("/update/{activityId}")
    public ResponseEntity<ActivityDTO> updateActivity(
            @PathVariable Long activityId,
            @RequestBody ActivityDTO activityDTO) {
        return ResponseEntity.ok(activityService.updateActivity(activityId, activityDTO));
    }

    @DeleteMapping("/delete/{activityId}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long activityId) {
        activityService.deleteActivity(activityId);
        return ResponseEntity.noContent().build();
    }

}
