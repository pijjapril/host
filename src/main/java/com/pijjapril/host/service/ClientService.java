package com.pijjapril.host.service;

import com.pijjapril.host.domain.Client;
import com.pijjapril.host.domain.dto.ClientInput;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientService {
    Client create(String name, String ipAddress) throws Exception;

    Client update(Long clientId, ClientInput input) throws Exception;

    Client get(Long clientId);

    List<Client> getList(Pageable pageable);

    List<Client> getListWithConnection(Pageable pageable);

    void remove(Long clientId);

    Client checkConnection(Long clientId);
}
