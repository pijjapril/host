package com.pijjapril.host.service;

import com.pijjapril.host.domain.Client;
import com.pijjapril.host.domain.dto.ClientInput;
import com.pijjapril.host.query.ClientQueryService;
import com.pijjapril.host.repository.ClientRepository;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.net.InetAddress;
import java.time.Instant;
import java.util.List;

import static com.pijjapril.host.domain.Client.createInfo;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientQueryService clientQueryService;

    public ClientServiceImpl(
            ClientRepository clientRepository,
            ClientQueryService clientQueryService
    ) {
        this.clientRepository = clientRepository;
        this.clientQueryService = clientQueryService;
    }

    @Override
    @Transactional
    public Client create(String name, String ipAddress) throws Exception {
        try {
            if (!checkRow()) {
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
    @Transactional
    public Client update(Long clientId, ClientInput input) throws Exception {
        try {
            Client client = clientRepository.findById(clientId)
                    .orElseThrow(IllegalArgumentException::new);
            client.setName(input.getName());
            client.setIp(input.getIpAddress());
            return clientRepository.save(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new Exception("Bad Request");
    }

    @Override
    public Client get(Long clientId) {
        Client client = null;
        try {
            client = clientRepository.findById(clientId)
                    .orElseThrow(IllegalArgumentException::new);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    @Transactional
    public List<Client> getList(Pageable pageable) {
        PageImpl<Client> search = clientQueryService.clientList(pageable);
        return search.getContent();
    }

    @Transactional
    public List<Client> getListWithConnection(Pageable pageable) {
        PageImpl<Client> search = clientQueryService.clientList(pageable);
        List<Client> list = search.getContent();
        for (Client c : list) {
            changeConnection(c.getId());
        }
        return list;
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

    private Boolean checkRow() {
        long rowNum = clientRepository.count();
        return rowNum <= 100;
    }

    @Override
    @Transactional
    public Client checkConnection(Long clientId) {
        Client client = null;
        try {
            changeConnection(clientId);
            client = clientRepository.findById(clientId)
                    .orElseThrow(IllegalArgumentException::new);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return client;
    }

    @Transactional
    private void changeConnection(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(IllegalArgumentException::new);
        String ipAddress = client.getIp();
        try {
            InetAddress pingCheck = InetAddress.getByName(ipAddress);
            boolean isAlive = pingCheck.isReachable(300);
            client.setIsAlive(isAlive);
            client.setLastAlivedAt(Instant.now());

            clientRepository.save(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
