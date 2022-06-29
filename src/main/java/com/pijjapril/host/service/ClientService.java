package com.pijjapril.host.service;

import com.pijjapril.host.domain.Client;
import com.pijjapril.host.domain.dto.ClientDTO;
import com.pijjapril.host.domain.dto.ClientInput;

public interface ClientService {
    public Client create(String name, String ipAddress) throws Exception;
    Client get(Long clientId);
    ClientDTO getList();
    int update(Long clientId, ClientInput input);
    void remove(Long clientId);
}
