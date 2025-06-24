package com.projectmanagement.controller;

import com.projectmanagement.dto.IndicatorDTO;
import com.projectmanagement.service.IndicatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/indicators")
@RequiredArgsConstructor
public class IndicatorController {

    private final IndicatorService indicatorService;

    @GetMapping("/{projectId}")
    public ResponseEntity<List<IndicatorDTO>> getAllIndicatorsOnProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(indicatorService.getAllIndicatorsOnProject(projectId));
    }

    @PostMapping("/{projectId}/create")
    public ResponseEntity<IndicatorDTO> createIndicatorOnProject(
            @PathVariable Long projectId,
            @RequestBody IndicatorDTO indicatorDTO)
    {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(indicatorService.saveIndicatorOnProject(projectId,indicatorDTO));
    }

    @PatchMapping("/update")
    public ResponseEntity<IndicatorDTO> updateIndicatorOnProject(@RequestBody IndicatorDTO indicatorDTO){
        return ResponseEntity.ok(indicatorService.updateIndicatorOnProject(indicatorDTO));
    }

    @GetMapping("/{indicatorId}")
    public ResponseEntity<IndicatorDTO> getIndicatorById(@PathVariable Long indicatorId){
        return ResponseEntity.ok(indicatorService.getIndicatorOnProjectById(indicatorId));
    }

    @DeleteMapping("/delete/{indicatorId}")
    public ResponseEntity<Void> deleteIndicatorOnProject(@PathVariable Long indicatorId){
        indicatorService.deleteIndicatorOnProject(indicatorId);
        return ResponseEntity.noContent().build();
    }

}
