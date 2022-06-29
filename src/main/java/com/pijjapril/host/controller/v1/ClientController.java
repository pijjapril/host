package com.pijjapril.host.controller.v1;

import com.pijjapril.host.domain.Client;
import com.pijjapril.host.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/v1/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public void createClient(HttpServletRequest req) throws Exception {
        try {
            String name = req.getRemoteHost();
//            String ipAddress = req.getRemoteAddr();

            clientService.create(name);
        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            e.printStackTrace();
        }
//        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> getClient(
            @PathVariable("clientId") Long id
    ) {
        return ResponseEntity.ok(clientService.get(id));
    }

    @PatchMapping("/{clientId}")
    public ResponseEntity<Client> updateClient(
            @PathVariable("clientId") Long id,
            @RequestBody Client client
    ) {
        try {
            clientService.update(id, client);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
