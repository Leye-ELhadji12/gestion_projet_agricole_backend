package com.projectmanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
            Responsible responsible = responsibleMapper.toResponsible(responsibleDTO);
            Responsible newResponsible = responsibleRepository.save(responsible);
            return responsibleMapper.toResponsibleDTO(newResponsible);
      }

      public Page<ResponsibleDTO> getAllResponsibles(int page, int size) {
            return responsibleRepository.findAll(PageRequest.of(page,size))
                    .map(responsibleMapper::toResponsibleDTO);
      }
}
