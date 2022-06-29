package com.pijjapril.host.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ClientDTO {
    private Long id;
    private String name;
    private String ip;
    private Boolean alive;
    private Instant lastAlivedAt;
    private Instant createdAt;
    private Instant updatedAt;
}
