package com.pijjapril.host.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClientResponse<T> {
    private List<T> result;
    private int totalCount = 0;
}
