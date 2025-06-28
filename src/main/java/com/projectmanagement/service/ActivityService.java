package com.projectmanagement.service;

import com.projectmanagement.dto.ActivityDTO;
import com.projectmanagement.entity.Activity;
import com.projectmanagement.entity.Project;
import com.projectmanagement.enums.ActivityStatus;
import com.projectmanagement.enums.Priorite;
import com.projectmanagement.exception.ActivityNotFoundException;
import com.projectmanagement.exception.ProjectNotFoundException;
import com.projectmanagement.mapper.ActivityMapper;
import com.projectmanagement.repository.ActivityRepository;
import com.projectmanagement.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final ActivityMapper activityMapper;
    private final ProjectRepository projectRepository;

    @Transactional
    public ActivityDTO createActivity(Long projectID, ActivityDTO activityDTO) {
        Project project = projectRepository.findById(projectID)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found"));
        Activity activity = activityMapper.toActivity(activityDTO);
        activity.setProject(project);
        return activityMapper.toActivityDTO(activityRepository.save(activity));
    }

    @Transactional(readOnly = true)
    public Page<ActivityDTO> getActivities(Long projectID, Pageable pageable) {
        Project proj = projectRepository.findById(projectID)
                .orElseThrow(() -> new ProjectNotFoundException("Project " + projectID + " not found"));
        Page<Activity> activities = activityRepository.findByProject(proj, pageable);
        return activities.map(activityMapper::toActivityDTO);
    }

    @Transactional
    public ActivityDTO updateActivity(Long activityId, ActivityDTO activityDTO) {
        Activity existingActivity = activityRepository.findById(activityId)
                .orElseThrow(() -> new ActivityNotFoundException("Activity not found with id: " + activityId));
        existingActivity.setTitle(activityDTO.getTitle());
        existingActivity.setDescription(activityDTO.getDescription());
        existingActivity.setPlannedStartDate(activityDTO.getPlannedStartDate());
        existingActivity.setPlannedEndDate(activityDTO.getPlannedEndDate());
        existingActivity.setActualStartDate(activityDTO.getActualStartDate());
        if (activityDTO.getStatus() != null) {
            existingActivity.setStatus(ActivityStatus.valueOf(activityDTO.getStatus()));
        }
        if (activityDTO.getPriorite() != null) {
            existingActivity.setPriorite(Priorite.valueOf(activityDTO.getPriorite()));
        }
        Activity updatedActivity = activityRepository.save(existingActivity);
        return activityMapper.toActivityDTO(updatedActivity);
    }

    @Transactional
    public void deleteActivity(Long activityId) {
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new ActivityNotFoundException("Activity not found with id: " + activityId));
        activityRepository.delete(activity);
    }
}
