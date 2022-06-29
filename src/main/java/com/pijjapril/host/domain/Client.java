package com.pijjapril.host.domain;

import com.pijjapril.util.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "hosts")
public class Client extends BaseEntity {
    protected Client() {}

    protected Client(String name, String ip) {
        this.name = name;
        this.ip = ip;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "ip", unique = true)
    private String ip;

    public static Client createInfo(String name, String ip) {
        return new Client(name, ip);
    }
}
