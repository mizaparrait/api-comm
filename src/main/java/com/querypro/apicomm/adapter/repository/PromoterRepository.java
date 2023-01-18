package com.querypro.apicomm.adapter.repository;

import com.querypro.apicomm.domain.model.users.Promoter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PromoterRepository extends JpaRepository<Promoter, Integer> {

    Optional<Promoter> findByEmail(String email);
}
