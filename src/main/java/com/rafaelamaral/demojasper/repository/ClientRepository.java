package com.rafaelamaral.demojasper.repository;

import com.rafaelamaral.demojasper.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
