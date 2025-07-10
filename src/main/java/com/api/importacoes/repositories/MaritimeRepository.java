package com.api.importacoes.repositories;

import com.api.importacoes.models.MaritimeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MaritimeRepository extends JpaRepository<MaritimeModel, UUID> {
}
