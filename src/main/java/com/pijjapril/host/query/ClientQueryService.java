package com.pijjapril.host.query;

import com.pijjapril.host.domain.Client;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public interface ClientQueryService {
    PageImpl<Client> clientList(Pageable pageable);
}
