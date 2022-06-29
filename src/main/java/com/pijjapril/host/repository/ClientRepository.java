package com.pijjapril.host.repository;

import com.pijjapril.host.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("select count(c.id) from client c")
    long count();
}
