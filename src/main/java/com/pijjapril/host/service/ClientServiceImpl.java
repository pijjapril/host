package com.pijjapril.host.service;

import com.pijjapril.host.domain.Client;
import com.pijjapril.host.domain.dto.ClientInput;
import com.pijjapril.host.domain.dto.ClientListDTO;
import com.pijjapril.host.domain.dto.ReadClientDTO;
import com.pijjapril.host.repository.ClientRepository;

import javax.transaction.Transactional;

public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;

    @Override
    @Transactional
    public void createClient(ClientInput input) {
    }

    @Override
    public ReadClientDTO getClient(Long clientId) {
        return null;
    }

    @Override
    public ClientListDTO clientList() {
        return null;
    }

    @Override
    @Transactional
    public void updateClient(ClientInput input) {

    }

    @Override
    @Transactional
    public void removeClient(Long clientId) {

    }
}
