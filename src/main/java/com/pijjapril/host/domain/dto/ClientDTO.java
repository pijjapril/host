package com.pijjapril.host.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ClientDTO {
    public Long id;
    public Boolean isAlive;
    public Instant lastAlivedAt;
}
