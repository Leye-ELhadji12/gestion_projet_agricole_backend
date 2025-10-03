package com.projectmanagement.service;

import com.projectmanagement.dto.DeliverableDTO;
import com.projectmanagement.entity.Activity;
import com.projectmanagement.entity.Deliverable;
import com.projectmanagement.exception.ActivityNotFoundException;
import com.projectmanagement.exception.DocumentNotFoundException;
import com.projectmanagement.mapper.DeliverableMapper;
import com.projectmanagement.repository.ActivityRepository;
import com.projectmanagement.repository.DeliverableRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DeliverableService {

      private final DeliverableRepository deliverableRepository;
      private final DeliverableMapper deliverableMapper;
      private final ActivityRepository activityRepository;

      public DeliverableDTO saveDocument(DeliverableDTO deliverableDTO) throws IOException {
            Deliverable deliverable = deliverableMapper.toDocument(deliverableDTO);
            if (deliverableDTO.getActivityId() != null) {
                  Activity activity = activityRepository.findById(deliverableDTO.getActivityId())
                        .orElseThrow(() -> new ActivityNotFoundException("Activity not found with ID: " + deliverableDTO.getActivityId()));
                  deliverable.setActivity(activity);
            }
            deliverableDTO.setType(deliverableDTO.getType());
            deliverableDTO.setActivityId(deliverableDTO.getActivityId());
            Deliverable savedDeliverable = deliverableRepository.save(deliverable);
            return deliverableMapper.toDocumentDTO(savedDeliverable);
      }

      public DeliverableDTO getDocumentById(Long id) {
            Deliverable document = deliverableRepository.findById(id)
                  .orElseThrow(() -> new DocumentNotFoundException("Document not found with ID: " + id));
            return deliverableMapper.toDocumentDTO(document);
      }

      public List<DeliverableDTO> getAllDocuments() {
            return deliverableRepository.findAll().stream()
                  .map(deliverableMapper::toDocumentDTO)
                  .toList();
      }

      public void deleteDocument(Long id) {
            deliverableRepository.deleteById(id);
      }

      public List<DeliverableDTO> getDocumentsByActivityId(Long activityId) {
            return deliverableRepository.findByActivityId(activityId).stream()
                  .map(deliverableMapper::toDocumentDTO)
                  .toList();
      }
}
