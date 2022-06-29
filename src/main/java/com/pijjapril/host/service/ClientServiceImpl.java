package com.pijjapril.host.service;

import com.pijjapril.host.domain.Client;
import com.pijjapril.host.domain.dto.ClientListDTO;
import com.pijjapril.host.repository.ClientRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.net.InetAddress;
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
    public void create(String name) throws Exception {
        try {
            InetAddress host = InetAddress.getByName(name);
            String ipAddress = host.getHostAddress();
            Client client = createInfo(name, ipAddress);
            clientRepository.save(client);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
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
    public ClientListDTO getList() {
        return null;
    }

    @Override
    @Transactional
    public void update(Long clientId, Client client) {
//        try {
//            clientRepository.findById(clientId).orElseThrow(() ->
//                    new NoSuchElementException(clientId + "is not exist"));
//            client = createInfo(inetAddress.getHostName(), inetAddress.getHostAddress());
//            clientRepository.save(client);
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    @Transactional
    public void remove(Long clientId) {

    }
}
