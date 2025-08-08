package com.projectmanagement.mapper;

import com.projectmanagement.dto.IndicatorDTO;
import com.projectmanagement.entity.Indicator;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class IndicatorMapper {

    public Indicator toIndicator(IndicatorDTO indicatorDTO) {
        Indicator indicator = new Indicator();
        BeanUtils.copyProperties(indicatorDTO, indicator);
        return indicator;
    }

    public IndicatorDTO toIndicatorDTO(Indicator indicator) {
        IndicatorDTO indicatorDTO = new IndicatorDTO();
        BeanUtils.copyProperties(indicator, indicatorDTO);
        return indicatorDTO;
    }
}
