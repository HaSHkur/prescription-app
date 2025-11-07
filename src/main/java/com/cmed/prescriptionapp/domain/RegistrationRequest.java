package com.cmed.prescriptionapp.domain;

import com.cmed.prescriptionapp.model.Role;
import lombok.Data;

@Data
public class RegistrationRequest {
    private String username;
    private String password;
    private Role role;
}
