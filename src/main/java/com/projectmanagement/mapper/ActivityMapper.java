package com.projectmanagement.mapper;

import com.projectmanagement.dto.ActivityDTO;
import com.projectmanagement.entity.Activity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ActivityMapper {

    public Activity toActivity(ActivityDTO activityDTO) {
        Activity activity = new Activity();
        BeanUtils.copyProperties(activityDTO, activity);
        return activity;
    }

    public ActivityDTO toActivityDTO(Activity activity) {
        ActivityDTO activityDTO = new ActivityDTO();
        BeanUtils.copyProperties(activity, activityDTO);
        return activityDTO;
    }

}
