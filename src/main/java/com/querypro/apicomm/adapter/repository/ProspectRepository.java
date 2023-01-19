package com.querypro.apicomm.adapter.repository;

import com.querypro.apicomm.domain.model.users.Prospect;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProspectRepository extends JpaRepository<Prospect, Integer> {
    Optional<Prospect> findByEmail(String email);
}
