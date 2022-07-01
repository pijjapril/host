package com.pijjapril.host.controller.v1;

import com.pijjapril.host.domain.Client;
import com.pijjapril.host.domain.dto.ClientInput;
import com.pijjapril.host.service.ClientService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public Map<String, Object> insertClient(HttpServletRequest req) {
        Map<String, Object> response = new HashMap<>();
        try {
            String name = req.getRemoteHost();
            String ipAddress = req.getRemoteAddr();
            Client client = clientService.create(name, ipAddress);
            if (client != null) {
                response.put("client", client);
            } else {
                response.put("result", "FAIL");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @PatchMapping("/{clientId}")
    public ResponseEntity<Client> modifyClient(
            @PathVariable("clientId") Long id,
            HttpServletRequest req,
            ClientInput input
    ) {
        try {
            String name = req.getRemoteHost();
            String ipAddress = req.getRemoteAddr();
            input.setName(name);
            input.setIpAddress(ipAddress);
            clientService.update(id, input);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> readClient(
            @PathVariable("clientId") Long id
    ) {
        return ResponseEntity.ok(clientService.get(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Client>> getList(
            @RequestParam(required = false, defaultValue = "1", value = "page") int page,
            @RequestParam(required = false, defaultValue = "100") int size
    ) {
        return ResponseEntity.ok(
                clientService.getList(
                        createPageRequest(page - 1, size)
                )
        );
    }

    @GetMapping("/state-list")
    public ResponseEntity<List<Client>> getListWithConnection(
            @RequestParam(required = false, defaultValue = "1", value = "page") int page,
            @RequestParam(required = false, defaultValue = "100") int size
    ) {
        return ResponseEntity.ok(
                clientService.getListWithConnection(
                        createPageRequest(page - 1, size)
                )
        );
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Client> removeClient(@PathVariable("clientId") Long id) {
        try {
            clientService.remove(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Pageable createPageRequest(int page, int size) {
        return PageRequest.of(page, size);
    }

    @PatchMapping("/state/{clientId}")
    public ResponseEntity<Client> connectState(@PathVariable("clientId") Long id) {
        return ResponseEntity.ok(clientService.checkConnection(id));
    }
}
