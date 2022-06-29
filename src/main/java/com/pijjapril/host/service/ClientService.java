package com.pijjapril.host.service;

import com.pijjapril.host.domain.Client;
import com.pijjapril.host.domain.dto.ClientInput;
import com.pijjapril.host.domain.dto.ClientListDTO;
import com.pijjapril.host.domain.dto.ReadClientDTO;

public interface ClientService {
    void create(String name) throws Exception;
    Client get(Long clientId);
    ClientListDTO getList();
    void update(Long clientId, Client client);
    void remove(Long clientId);
}
