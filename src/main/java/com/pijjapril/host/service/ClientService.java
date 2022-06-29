package com.pijjapril.host.service;

import com.pijjapril.host.domain.Client;
import com.pijjapril.host.domain.dto.ClientInput;
import com.pijjapril.host.domain.dto.ClientListDTO;
import java.util.Map;

public interface ClientService {
    Client create(String name, String ipAddress);
    Client get(Long clientId);
    ClientListDTO getList();
    int update(Long clientId, ClientInput input);
    void remove(Long clientId);
}
