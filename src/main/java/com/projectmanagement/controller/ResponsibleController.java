package com.projectmanagement.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projectmanagement.dto.ResponsibleDTO;
import com.projectmanagement.service.ResponsibleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/responsibles")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ResponsibleController {

      private final ResponsibleService responsibleService;

      @GetMapping
      public ResponseEntity<Page<ResponsibleDTO>> getAllResponsible(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
            return ResponseEntity.ok(responsibleService.getAllResponsibles(page,size));
      }

      @GetMapping("/{id}")
      public ResponseEntity<ResponsibleDTO> getResponsibleById(@PathVariable Long id) {
            return ResponseEntity.ok(responsibleService.getResponsibleById(id));
      }

      @PostMapping("/create")
      public ResponseEntity<ResponsibleDTO> createResponsible(@RequestBody ResponsibleDTO res) {
            return ResponseEntity.status(HttpStatus.CREATED).body(responsibleService.createResponsible(res));
      }

      @PatchMapping("/update/{id}")
      public ResponseEntity<ResponsibleDTO> updateResponsible(
            @PathVariable Long id,
            @RequestBody ResponsibleDTO res) {
            return ResponseEntity.ok(responsibleService.updateResponsible(id, res));
      }

      @DeleteMapping("/delete/{id}")
      public ResponseEntity<Void> deleteResponsible(@PathVariable Long id) {
            responsibleService.deleteResponsible(id);
            return ResponseEntity.noContent().build();
      }
}
