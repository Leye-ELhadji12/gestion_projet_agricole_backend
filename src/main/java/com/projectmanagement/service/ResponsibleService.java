package com.projectmanagement.service;

import com.projectmanagement.exception.ResponsibleNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.projectmanagement.dto.ResponsibleDTO;
import com.projectmanagement.entity.Responsible;
import com.projectmanagement.mapper.ResponsibleMapper;
import com.projectmanagement.repository.ResponsibleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResponsibleService {

      private final ResponsibleMapper responsibleMapper;
      private final ResponsibleRepository responsibleRepository;

      public ResponsibleDTO createResponsible(ResponsibleDTO responsibleDTO) {
            if (responsibleRepository.findByEmail(responsibleDTO.getEmail()) != null) {
                  throw new IllegalArgumentException("Email already exists");
            }
            Responsible responsible = responsibleMapper.toResponsible(responsibleDTO);
            Responsible newResponsible = responsibleRepository.save(responsible);
            return responsibleMapper.toResponsibleDTO(newResponsible);
      }

      public Page<ResponsibleDTO> getAllResponsibles(int page, int size) {
            PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
            return responsibleRepository.findAll(pageRequest)
                  .map(responsibleMapper::toResponsibleDTO);
      }

      public ResponsibleDTO getResponsibleById(Long id) {
            Responsible responsible = responsibleRepository.findById(id)
                  .orElseThrow(() -> new ResponsibleNotFoundException(
                        "Responsible with id " + id + " not found"));
            return responsibleMapper.toResponsibleDTO(responsible);
      }

      public ResponsibleDTO updateResponsible(Long id, ResponsibleDTO responsibleDTO) {
            Responsible existingResponsible = responsibleRepository.findById(id)
                  .orElseThrow(() -> new ResponsibleNotFoundException(
                        "Responsible with id " + id + " not found"));
            if (responsibleRepository.findByEmail(responsibleDTO.getEmail()) != null &&
                  !existingResponsible.getEmail().equals(responsibleDTO.getEmail())) {
                  throw new IllegalArgumentException("Email already exists");
            }
            Responsible updatedResponsible = responsibleMapper.toResponsible(responsibleDTO);
            updatedResponsible.setId(id);
            Responsible savedResponsible = responsibleRepository.save(updatedResponsible);
            return responsibleMapper.toResponsibleDTO(savedResponsible);
      }

      public void deleteResponsible(Long id) {
            Responsible responsible = responsibleRepository.findById(id)
                  .orElseThrow(() -> new ResponsibleNotFoundException("Responsible with id " + id + " not found"));
            responsible.getProjects().forEach(project -> project.getResponsibles().remove(responsible));
            responsibleRepository.save(responsible);
            responsibleRepository.deleteById(id);
      }
}
