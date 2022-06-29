package com.pijjapril.host.service;

import com.pijjapril.host.domain.Client;
import com.pijjapril.host.domain.dto.ClientDTO;
import com.pijjapril.host.domain.dto.ClientInput;
import com.pijjapril.host.repository.ClientRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.NoSuchElementException;
import java.util.Optional;
import static com.pijjapril.host.domain.Client.createInfo;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional
    public Client create(String name, String ipAddress) throws Exception{
        try {
            if(!checkRow()){
                throw new IllegalAccessException("Already 100 row exist");

            }
            Client client = createInfo(name, ipAddress);
            return clientRepository.save(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new Exception("Bad Request");
    }
    @Override
    public Client get(Long clientId) {
        Client dto = null;
        try {
            Optional<Client> client = clientRepository.findById(clientId);
            dto = client.get();
            dto = clientRepository.findById(clientId).orElseThrow(() ->
                    new NoSuchElementException(clientId + "is not exist"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }
    @Override
    public ClientDTO getList() {
        return null;
    }
    @Override
    @Transactional
    public int update(Long clientId, ClientInput input) {
        Optional<Client> oClient = clientRepository.findById(clientId);
        if(oClient.isPresent()) {
            Client client = oClient.get();
            client.setName(input.getName());
            client.setIp(input.getIpAddress());

            clientRepository.save(client);
            return 1;
        }
        return 0;
    }
    @Override
    @Transactional
    public void remove(Long clientId) {
        try {
            clientRepository.deleteById(clientId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Boolean checkRow(){
        long rowNum = clientRepository.count();
        return rowNum <= 100;
    }
}
