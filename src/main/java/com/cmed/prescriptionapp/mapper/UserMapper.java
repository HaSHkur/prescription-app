package com.cmed.prescriptionapp.mapper;


import com.cmed.prescriptionapp.domain.UserDomain;
import com.cmed.prescriptionapp.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserDomain entityToDomain(UserEntity user) {
        if (user == null) {
            return null;
        }
        return new UserDomain(
                user.getUsername(),
                user.getRole()
        );
    }

    public UserEntity domainToEntity(UserDomain domain) {
        if (domain == null) {
            return null;
        }
        UserEntity user = new UserEntity();
        user.setUsername(domain.getUsername());
        user.setRole(domain.getRole());
        return user;
    }
}

