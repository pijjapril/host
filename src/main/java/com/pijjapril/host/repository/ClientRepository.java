package com.pijjapril.host.repository;

import com.pijjapril.host.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
