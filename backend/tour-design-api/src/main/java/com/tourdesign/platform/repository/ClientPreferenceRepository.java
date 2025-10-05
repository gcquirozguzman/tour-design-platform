package com.tourdesign.platform.repository;

import com.tourdesign.platform.entity.ClientPreferenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientPreferenceRepository extends JpaRepository<ClientPreferenceEntity, Long> {

    List<ClientPreferenceEntity> findByClientId(Long clientId);

}
