package com.projectmanagement.service;

import com.projectmanagement.dto.IndicatorDTO;
import com.projectmanagement.entity.Indicator;
import com.projectmanagement.entity.Project;
import com.projectmanagement.exception.IndicatorNotFoundException;
import com.projectmanagement.exception.ProjectNotFoundException;
import com.projectmanagement.mapper.IndicatorMapper;
import com.projectmanagement.repository.IndicatorRepository;
import com.projectmanagement.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IndicatorService {

    private final IndicatorRepository indicatorRepository;
    private final IndicatorMapper indicatorMapper;
    private final ProjectRepository projectRepository;

    @Transactional
    public IndicatorDTO saveIndicatorOnProject(Long projectID, IndicatorDTO indicatorDTO) {
        Project project = projectRepository.findById(projectID)
                .orElseThrow(() -> new ProjectNotFoundException("Project id: "+ projectID+" not found"));
        Indicator indicator = indicatorMapper.toIndicator(indicatorDTO);
        indicator.setProject(project);
        return indicatorMapper.toIndicatorDTO(indicatorRepository.save(indicator));
    }

    @Transactional(readOnly = true)
    public List<IndicatorDTO> getAllIndicatorsOnProject(Long projectID) {
        Project pjt = projectRepository.findById(projectID)
                .orElseThrow(() -> new ProjectNotFoundException("Project id: "+ projectID+" not exist"));
        List<Indicator> indicators = indicatorRepository.findByProject(pjt);
        return indicators.stream().map(indicatorMapper::toIndicatorDTO).toList();
    }

    public IndicatorDTO getIndicatorOnProjectById(Long indicatorID) {
        Indicator indicator = indicatorRepository.findById(indicatorID)
                .orElseThrow(() -> new IndicatorNotFoundException("Indicator id: "+ indicatorID+" not found"));
        return indicatorMapper.toIndicatorDTO(indicator);
    }

    @Transactional
    public IndicatorDTO updateIndicatorOnProject(IndicatorDTO indicatorDTO) {
        Indicator indicator = indicatorRepository.findById(indicatorDTO.getId()).orElseThrow(
                () -> new IndicatorNotFoundException("Not found Indicator id: "+ indicatorDTO.getId()));
        Indicator newIndicator = new Indicator();
        indicator.setUnit(indicatorDTO.getUnit());
        indicator.setName(indicatorDTO.getName());
        indicator.setDescription(indicatorDTO.getDescription());
        indicator.setTargetValue(indicatorDTO.getTargetValue());
        indicator.setActualValue(indicatorDTO.getActualValue());
        return indicatorMapper.toIndicatorDTO(indicatorRepository.save(newIndicator));
    }

    @Transactional
    public void deleteIndicatorOnProject(Long indicatorID) {
        indicatorRepository.deleteById(indicatorID);
    }
}
