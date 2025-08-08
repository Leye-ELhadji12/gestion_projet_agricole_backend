package com.projectmanagement.mapper;

import com.projectmanagement.dto.ActivityDTO;
import com.projectmanagement.entity.Activity;
import com.projectmanagement.enums.Priorite;
import com.projectmanagement.enums.ActivityStatus;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ActivityMapper {

    public Activity toActivity(ActivityDTO activityDTO) {
        Activity activity = new Activity();
        BeanUtils.copyProperties(activityDTO, activity);
        if (activityDTO.getPriorite() != null) {
            activity.setPriorite(Priorite.valueOf(activityDTO.getPriorite()));
        }
        if (activityDTO.getStatus() != null) {
            activity.setStatus(ActivityStatus.valueOf(activityDTO.getStatus()));
        }
        return activity;
    }

    public ActivityDTO toActivityDTO(Activity activity) {
        ActivityDTO activityDTO = new ActivityDTO();
        BeanUtils.copyProperties(activity, activityDTO);
        if (activity.getPriorite() != null) {
            activityDTO.setPriorite(activity.getPriorite().name());
        }
        if (activity.getStatus() != null) {
            activityDTO.setStatus(activity.getStatus().name());
        }
        return activityDTO;
    }

}
