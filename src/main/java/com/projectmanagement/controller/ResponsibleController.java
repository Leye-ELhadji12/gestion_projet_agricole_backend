package com.projectmanagement.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projectmanagement.dto.ResponsibleDTO;
import com.projectmanagement.service.ResponsibleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/responsible")
@RequiredArgsConstructor
public class ResponsibleController {

      private final ResponsibleService responsibleService;

      @GetMapping
      public ResponseEntity<Page<ResponsibleDTO>> getAllResponsible(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
            return ResponseEntity.ok(responsibleService.getAllResponsibles(page,size));
      }

      @PostMapping("/create")
      public ResponseEntity<ResponsibleDTO> createResponsible(@RequestBody ResponsibleDTO res) {
            return ResponseEntity.status(HttpStatus.CREATED).body(responsibleService.createResponsible(res));
      }
}
