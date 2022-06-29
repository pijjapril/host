package com.pijjapril.host.service;

import com.pijjapril.host.domain.dto.ClientInput;
import com.pijjapril.host.domain.dto.ClientListDTO;
import com.pijjapril.host.domain.dto.ReadClientDTO;

public interface ClientService {
    void createClient(ClientInput input);
    ReadClientDTO getClient(Long clientId);
    ClientListDTO clientList();
    void updateClient(ClientInput input);
    void removeClient(Long clientId);
}
