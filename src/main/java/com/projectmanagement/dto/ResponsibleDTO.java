package com.projectmanagement.dto;

import lombok.Data;

@Data
public class ResponsibleDTO {
      private Long id;
      private String email;
      private String firstname;
      private String lastname;
      private String password;
      private String role;
      private String phone;
}
