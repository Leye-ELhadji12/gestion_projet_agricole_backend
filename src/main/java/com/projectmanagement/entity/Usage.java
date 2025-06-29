package com.projectmanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usage {
      @Id @GeneratedValue(strategy = GenerationType.AUTO)
      private Long id;
      @ManyToOne
      private Activity activity;
      @ManyToOne
      private Resource resource;
}
