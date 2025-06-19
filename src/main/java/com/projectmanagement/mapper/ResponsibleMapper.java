package com.projectmanagement.mapper;

import com.projectmanagement.enums.Role;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.projectmanagement.dto.ResponsibleDTO;
import com.projectmanagement.entity.Responsible;

@Service
public class ResponsibleMapper {

      public Responsible toResponsible(ResponsibleDTO responsibleDTO) {
            Responsible responsible = new Responsible();
            BeanUtils.copyProperties(responsibleDTO, responsible);
            responsible.setRole(Role.valueOf(responsibleDTO.getRole()));
            return responsible;
      }

      public ResponsibleDTO toResponsibleDTO(Responsible responsible) {
            ResponsibleDTO responsibleDTO = new ResponsibleDTO();
            BeanUtils.copyProperties(responsible, responsibleDTO);
            responsibleDTO.setRole(responsible.getRole().name());
            return responsibleDTO;
      }
}
