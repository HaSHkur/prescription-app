package com.cmed.prescriptionapp.repository;

import com.cmed.prescriptionapp.entity.ConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfigurationRepository extends JpaRepository<ConfigurationEntity, String> {
    Optional<ConfigurationEntity> findByKey(String key);
}
